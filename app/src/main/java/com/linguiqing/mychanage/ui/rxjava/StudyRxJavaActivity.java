package com.linguiqing.mychanage.ui.rxjava;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.jakewharton.rxbinding2.view.RxView;
import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * ***************************************
 * statement: 学习Rxjava 的使用  https://github.com/lzyzsd/Awesome-RxJava
 * auther: lingguiqin
 * date created : 2017/7/8 0008
 * ***************************************
 */

public class StudyRxJavaActivity extends BaseActivity {

    @BindView(R.id.btn_study_rxjava_set_drawable)
    Button mBtnSetDrawable;

    @BindView(R.id.btn_study_rxjava_repetition_click)
    Button mBtnRepetitionClick;
    @BindView(R.id.btn_study_rxjava_interval_time)
    Button mBtnIntervalTime;
    private boolean mIsShowDrawable;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_rxjava;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.initTitlebar(true, "RxJava的使用", "", null);
        return titlebar;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setBtnRepetitionClick();
    }


    private void setHelloWord() {

        // 创建被观察者（报纸出版社）
        // 第一种最基本的实现方式

//         Observable<String> mObservableHello = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                LogUtil.e("被观察者 subscribe()执行了");
//                e.onNext("hello,word!");
//                e.onNext("hello,word2!");
//                e.onComplete();
//            }
//        });

        // 第二种快速的实现方式
//        Observable<String> mObservableHello = Observable.just("hello,word!", "hello,word2!");
        // 第三种实现方式
//        Observable<String> mObservableHello = Observable.fromArray("hello,word!", "hello,word2!");
        // 第四种实现方式(只能创建发送一个数据)
        Observable<String> mObservableHello = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello,word!";
            }
        });


        // 创建观察者 (订阅报纸的客户)
        Observer<String> mObserverHello = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                // 这里可以做准备初始化工作
                LogUtil.e("观察者 onSubscribe()执行了");
            }

            @Override
            public void onNext(@NonNull String s) {
                LogUtil.e("观察者 onNext()执行了");
                showToast(s, Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("观察者 onError()执行了");
            }

            @Override
            public void onComplete() {
                LogUtil.e("观察者 onComplete()执行了");
            }
        };

        // 1.实现被观察者与观察者进行订阅关联
        mObservableHello.subscribe(mObserverHello);
        // 2. 快速创建观察者接收onnext() 简介的写法;
//        mObservableHello.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(@NonNull String s) throws Exception {
//                showToast(s, Toast.LENGTH_SHORT);
//            }
//        });


    }

    private void setPrintString() {
        String[] names = new String[]{"冰淇淋1", "冰淇淋2"};
        Observable.fromArray(names).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("观察者 onSubscribe()执行了");
            }

            @Override
            public void onNext(@NonNull String s) {
                LogUtil.e("观察者 onNext()执行了");
                // 依次输出 "冰淇淋1", "冰淇淋2"
                showToast(s, Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("观察者 onError()执行了");
            }

            @Override
            public void onComplete() {
                LogUtil.e("观察者 onComplete()执行了");
            }
        });

    }


    private void setDrawable2View() {
        mIsShowDrawable = !mIsShowDrawable;
        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Drawable> e) throws Exception {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                e.onNext(drawable);
                e.onComplete();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Drawable drawable) {
                if (mIsShowDrawable) {
                    mBtnSetDrawable.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                } else {
                    mBtnSetDrawable.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                showToast("setDrawableError", Toast.LENGTH_SHORT);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 使用线程调度器
     */
    private void setUseScheduler() {
        mIsShowDrawable = !mIsShowDrawable;
        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Drawable> e) throws Exception {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                e.onNext(drawable);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()) // 指定读取图片在io子线程处理
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程 设置图片
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Drawable drawable) {
                        if (mIsShowDrawable) {
                            mBtnSetDrawable.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                        } else {
                            mBtnSetDrawable.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast("setDrawableError", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 使用map将参数 double 6.00  --> string "6.00"
     * map一对一的转换
     */
    private void setUseMapChangeParam() {
        Observable.just(6.00).map(new Function<Double, String>() {
            @Override
            public String apply(@NonNull Double aDouble) throws Exception {
                return aDouble + "";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                showToast(s, Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 使用 FlatMap 可以实现一对多的实现
     * 输出每个Student 参加的课程信息
     */
    private void setUseFlatMapChangeParam() {
        Student xiaoWang = new Student("小王", "01", 18, new String[]{"语文", "数学", "英语", "理综"});
        Student xiaoLing = new Student("小凌", "02", 22, new String[]{"语文", "数学", "英语", "文综", "体育"});
        Student[] students = new Student[]{xiaoWang, xiaoLing};
        Observable.fromArray(students).flatMap(new Function<Student, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Student student) throws Exception {
                return Observable.fromArray(student.courses);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String strings) {
                showToast(strings, Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 使用定时器 2秒后弹出toast
     */
    private void setUseTimer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        showToast(aLong + "", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 使用轮播定时输出toast
     */
    private void setUseInterval() {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(Observable.interval(0, 2, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long aLong) {
                        if (aLong >= 10) {
                            disposable.clear();
                        }
                        showToast(aLong + "", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));


    }

    /**
     * 自定义实现简单的观察者模式
     */
    private void setCustomSimple() {
        // 新建两个女票（观察者）
        GFObserver observer = new GFObserver("范冰冰");
        GFObserver observer2 = new GFObserver("杨幂");
        // 新建一个男票 （被观察者）
        BFSubject subject = new BFSubject();
        subject.attach(observer);
        subject.attach(observer2);
        // 向女票汇报行程状态
        subject.change("我在大保健");
    }


    /**
     * 处理按钮的重复点击  5秒只做一次处理
     */
    private void setBtnRepetitionClick() {
        RxView.clicks(mBtnRepetitionClick).throttleFirst(5, TimeUnit.SECONDS).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                LogUtil.d("我在暴力点击，但是我不怕拉拉---");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 合并两份数据
     */
    private void setMergeTwoData() {
        Observable.merge(getLocationData(), getNetData()).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(@NonNull List<String> strings) throws Exception {
                LogUtil.e("输出合并后的数据 =  " + strings.toString());
                // 输出结果调用2次：
                // 输出合并后的数据 =  [我是小明, 我是小王]
                // 输出合并后的数据 =  [我是小凌, 我是小杨]
            }
        });
    }

    // 获取本地数据
    private Observable<List<String>> getLocationData() {
        List<String> locationList = new ArrayList();
        locationList.add("我是小明");
        locationList.add("我是小王");
        return Observable.just(locationList);
    }

    //模拟获取网络数据
    private Observable<List<String>> getNetData() {
        List<String> netList = new ArrayList();
        netList.add("我是小凌");
        netList.add("我是小杨");
        return Observable.just(netList);
    }

    /**
     * 开始倒计时
     */
    private void setStartIntervalTime() {
        int count = 10;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count+1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mBtnIntervalTime.setEnabled(false);
                        LogUtil.e("doOnSubscribe 执行了");
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        LogUtil.e("onNext 执行了");
                        mBtnIntervalTime.setText("剩余: " + aLong + " s");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("onComplete 执行了");
                        mBtnIntervalTime.setEnabled(true);
                        mBtnIntervalTime.setText("开始倒计时");
                    }
                });
    }

    @OnClick({R.id.btn_study_rxjava_hello_word, R.id.btn_study_rxjava_print_string, R.id.btn_study_rxjava_set_drawable, R.id.btn_study_rxjava_used_scheduler,
            R.id.btn_study_rxjava_used_map, R.id.btn_study_rxjava_used_flatMap, R.id.btn_study_rxjava_used_timer, R.id.btn_study_rxjava_used_interval,
            R.id.btn_study_rxjava_used_simple, R.id.btn_study_rxjava_merge_data, R.id.btn_study_rxjava_interval_time})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_study_rxjava_hello_word:
                setHelloWord();
                break;
            case R.id.btn_study_rxjava_print_string:
                setPrintString();
                break;
            case R.id.btn_study_rxjava_set_drawable:
                setDrawable2View();
                break;
            case R.id.btn_study_rxjava_used_scheduler:
                setUseScheduler();
                break;
            case R.id.btn_study_rxjava_used_map:
                setUseMapChangeParam();
                break;
            case R.id.btn_study_rxjava_used_flatMap:
                setUseFlatMapChangeParam();
                break;
            case R.id.btn_study_rxjava_used_timer:
                setUseTimer();
                break;
            case R.id.btn_study_rxjava_used_interval:
                setUseInterval();
                break;
            case R.id.btn_study_rxjava_used_simple:
                setCustomSimple();
                break;

            case R.id.btn_study_rxjava_merge_data: // 合并数据
                setMergeTwoData();
                break;
            case R.id.btn_study_rxjava_interval_time: // 开始倒计时
                setStartIntervalTime();
                break;
        }

    }


}
