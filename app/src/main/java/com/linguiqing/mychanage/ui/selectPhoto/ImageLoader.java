package com.linguiqing.mychanage.ui.selectPhoto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * ***************************************
 * statement: 图片加载类
 * auther: lingguiqin
 * date created : 2017/3/8 0008
 * ***************************************
 */

public class ImageLoader {
    private static ImageLoader mInstance;

    // 图片缓存核心类
    private LruCache<String, Bitmap> mLruCache;

    // 线程池
    private ExecutorService mThreadPool;

    // 线程池的线程数量，默认为1
    private int mThreadCount = 1;

    // 队列调度方式
    private Type mType = Type.LIFO;

    // 任务队列
    private LinkedList<Runnable> mTasks;

    // 轮询的线程
    private Thread mPoolThread;
    private Handler mPoolThreadHandle;

    // 运行在ui线程的handle，用于给ImageView设置图片
    private Handler mUIHandle;

    // 引入一个值为1的信号量，防止mPoolThreadHandle 未初始化完成
    private Semaphore mSemaphore = new Semaphore(1);
    // 引入一个值为1的信号量，由于线程池内部也有一个阻塞线程，防止加入任务的速度过快，使LIFO效果不明显
    private Semaphore mPoolSemaphore;


    public enum Type {
        FIFO, LIFO
    }

    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    private void init(int threadCount, Type type) {

        // 初始化轮询线程
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandle = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {// 线程池取出一个任务进行执行
                        mThreadPool.execute(getTask());
                        try {
                            mPoolSemaphore.acquire();
                        } catch (InterruptedException e) {
                        }
                    }
                };
                mSemaphore.release();
                Looper.loop();
            }
        };
        mPoolThread.start();

        // 初始化 LruCache 内存为 maxMemory / 8
        // 获取应用程序最大可用的内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 测量每一张bitmap 所占的内存 = 一行所占的字节数 * 高度
                return value.getRowBytes() * value.getHeight();
            }
        };

        // 初始化线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mPoolSemaphore = new Semaphore(threadCount);
        mTasks = new LinkedList<Runnable>();
        mType = type;

    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(1, Type.LIFO);
                }
            }
        }
        return mInstance;
    }

    public static ImageLoader getInstance(int threadCount, Type type) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(threadCount, type);
                }
            }
        }
        return mInstance;
    }


    public void loadImage(final String path, final ImageView imageView) {
        imageView.setTag(path);
        mUIHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 获取得到图片，为 imageview回调设置图片
                ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                ImageView imgView = holder.imageView;
                String imgPath = holder.path;
                Bitmap bitmap = holder.bitmap;
                if (imgView.getTag().toString().equals(imgPath)) {
                    imgView.setImageBitmap(bitmap);
                }
            }
        };

        //从内存中获取图片
        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null) {
            Message msg = Message.obtain();
            ImageBeanHolder holder = new ImageBeanHolder();
            holder.bitmap = bm;
            holder.imageView = imageView;
            holder.path = path;
            msg.obj = holder;
            mUIHandle.sendMessage(msg);
        } else {

            addTask(new Runnable() {
                @Override
                public void run() {
                    // 获取imageView控件的大小
                    ImageSize imageSize = getImageViewSize(imageView);
                    int reqWidth = imageSize.width;
                    int reqHeight = imageSize.height;
                    Bitmap bitmap = decodeSampleBitmapFromResource(path, reqWidth, reqHeight);
                    addBitmapToLruCache(path, bitmap);
                    ImageBeanHolder holder = new ImageBeanHolder();
                    holder.imageView = imageView;
                    holder.path = path;
                    holder.bitmap = getBitmapFromLruCache(path);
                    Message msg = Message.obtain();
                    msg.obj = holder;
                    mUIHandle.sendMessage(msg);
                    mPoolSemaphore.release();
                }
            });

        }

    }

    private void addBitmapToLruCache(String path, Bitmap bitmap) {
        if (mLruCache.get(path) == null && bitmap != null) {
            mLruCache.put(path, bitmap);
        }
    }

    private Bitmap getBitmapFromLruCache(String path) {
        return mLruCache.get(path);
    }

    class ImageBeanHolder {

        ImageView imageView;
        String path;
        Bitmap bitmap;
    }


    /**
     * 添加一个任务加载图片
     *
     * @param runnable
     */

    private void addTask(Runnable runnable) {

        if (mPoolThreadHandle == null) {
            // 请求信号量，防止mPoolThreadHander为null
            try {
                mSemaphore.acquire();
                mPoolSemaphore.acquire();
            } catch (InterruptedException e) {
            }
        }

        mTasks.add(runnable);
        mPoolThreadHandle.sendEmptyMessage(0x110);
    }

    /**
     * 取出一个任务
     *
     * @return
     */
    private synchronized Runnable getTask() {

        if (mType == Type.FIFO) {
            return mTasks.removeFirst();
        } else if (mType == Type.LIFO) {
            return mTasks.removeLast();
        }
        return null;
    }

    /**
     * 获取图片控件显示的大小
     *
     * @param imageView
     * @return
     */
    public ImageSize getImageViewSize(ImageView imageView) {

        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext()
                .getResources().getDisplayMetrics();
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        int width = params.width == ViewGroup.LayoutParams.WRAP_CONTENT
                ? 0 : imageView.getWidth();
        if (width <= 0) {
            width = params.width;
        }
        if (width <= 0) {
            width = getImageViewFieldValue(imageView, "mMaxWidth"); // Check// maxWidth// parameter
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }

        int height = params.height == ViewGroup.LayoutParams.WRAP_CONTENT
                ? 0 : imageView.getHeight();
        if (height <= 0) {
            height = params.height;
        }
        if (height <= 0) {
            height = getImageViewFieldValue(imageView, "mMaxHeight");
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        imageSize.height = height;
        imageSize.width = width;
        return imageSize;
    }

    private class ImageSize {
        int width;
        int height;
    }


    /**
     * 反射获得ImageView设置的最大宽度和高度
     *
     * @param object
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = (Integer) field.get(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;

                Log.e("TAG", value + "");
            }
        } catch (Exception e) {
        }
        return value;
    }

    private Bitmap decodeSampleBitmapFromResource(String pathName,
                                                  int reqWidth,
                                                  int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // true 表示不加载图片，只是获取图片的大小
        BitmapFactory.decodeFile(pathName, options);
        // 根据图片大小和 imageView 的大小 进行按比例压缩
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 设置好参数后，进行解析 加载图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(pathName, options);
        return bitmap;
    }

    /**
     * 计算inSampleSize，用于压缩图片
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {
        // 源图片的宽度
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth && height > reqHeight) {
            // 计算出实际宽度和目标宽度的比率
            int widthRatio = Math.round((float) width / (float) reqWidth);
            int heightRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = Math.max(widthRatio, heightRatio);
        }
        return inSampleSize;
    }

}
