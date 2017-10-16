package com.linguiqing.mychanage.ui.db.natives;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/10/16 0016
 * ***************************************
 */
public class BookOpenHelper extends SQLiteOpenHelper {


    private static BookOpenHelper helper;

    public BookOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static BookOpenHelper getInstance(Context context) {
        if (helper == null) {
            helper = new BookOpenHelper(context, "book.db", null, 1);
        }
        return helper;
    }

    public void onCreate(SQLiteDatabase db) {
        //创建book表  Long id, String name, String price, String author, int shell_num,String image_url
        db.execSQL("create table book(" +
                "id integer primary key autoincrement, " +
                "name varchar, " +
                "author varchar, " +
                "price varchar, " +
                "shell_num integer, " +
                "image_url varchar " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
