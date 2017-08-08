package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ***************************************
 * statement: 硬盘存取工具类  http://blog.csdn.net/guolin_blog/article/details/28863651
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public class DiskUtil {


    /**
     * 获取版本号，当版本升级时，会自动删除之前的低版本的缓存，请求网络获取数据
     *
     * @param context
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    /**
     * 设置获取硬盘缓存的目录
     *
     * @param context    上下文
     * @param uniqueName 为了对不同类型的数据进行区分而设定的一个唯一值，比如说在网易新闻缓存路径下看到的bitmap、object等文件夹。
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        // 判断是否有内存卡,可读写状态、未被移除
        // 前者获取到的就是 /sdcard/Android/data/<application package>/cache 这个路径，
        // 而后者获取到的是 /data/data/<application package>/cache 这个路径。

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        // File.separator 为路径分隔符 / 在Windows下的路径分隔符和Linux下的路径分隔符是不一样的
        return new File(cachePath + File.separator + uniqueName);
    }


    /**
     * 给 key 进行 MD5 编码
     * 图片URL中可能包含一些特殊字符，这些字符有可能在命名文件时是不合法的。
     * 其实最简单的做法就是将图片的URL进行MD5编码，编码后的字符串肯定是唯一的，
     * 并且只会包含0-F这样的字符，完全符合文件的命名规则。
     *
     * @param key
     * @return
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(key.getBytes());
            cacheKey = bytesToHexString(digest.digest());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            cacheKey = key.hashCode() + "";
        }

        return cacheKey;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}