package com.linguiqing.mychanage.ui.db.natives;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.db.greendao.Book;
import com.linguiqing.mychanage.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ***************************************
 * statement: 书店(创建数据库的表名与字段)
 * auther: lingguiqin
 * date created : 2017/10/14 0014
 * ***************************************
 */
public class StudyNativeDbActivity extends BaseActivity {
    @BindView(R.id.tv_book_count_info)
    TextView mBookTotalNum;
    private List<Book> mBookList = new ArrayList<>();
    private StringBuilder mStringBuilder = new StringBuilder();
    private SQLiteDatabase mBookDao;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_db;
    }

    @Override
    public void initData() {
        super.initData();
        mBookDao = getApp().getBookDao();
        queryAll();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setTextBookNum();
    }

    // 插入
    public void insert(Book book) {
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("price", book.getPrice());
        values.put("shell_num", book.getShell_num());
        values.put("image_url", book.getImage_url());
        values.put("author", book.getAuthor());
        mBookDao.insert("book", null, values);
    }

    // 删除
    public void delete(Long id) {
        mBookDao.delete("book", "id=?", new String[]{id + ""});
    }

    // 查询
    public void queryAll() {
        Cursor cursor = mBookDao.query("book", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            mBookList.clear();
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Long id = cursor.getLong(cursor.getColumnIndex("id"));
                int shell_num = cursor.getInt(cursor.getColumnIndex("shell_num"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String image_url = cursor.getString(cursor.getColumnIndex("image_url"));
                mBookList.add(new Book(id, name, price, author, shell_num, image_url));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    // 修改
    public void update(Book book) {
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("price", book.getPrice());
        values.put("shell_num", book.getShell_num());
        values.put("image_url", book.getImage_url());
        values.put("author", book.getAuthor());
        //第一个参数是要更新的表名
        //第二个参数是一个ContentValeus对象
        //第三个参数是where子句
        mBookDao.update("book", values, "id=?", new String[]{"1"});
    }

    // 添加一条数据
    private Book addBook() {
        int index = mBookList.size();
        Book book = new Book((long) index, "幸福的拾荒者" + index,
                index + ".00", "胡歌" + index, index * 10, "https://baike.baidu.com/pic/%E5%B9%B8%E7%A6%8F%E7%9A%84%E6%8B%BE%E8%8D%92%E8%80%85/5845498/0/ca1349540923dd54f1eb6b50d309b3de9c82486a?fr=lemma&ct=single#aid=0&pic=d56b3634c86340285ab5f529");
        mBookList.add(book);
        return book;
    }

    // 根据索引值取书
    private Book getBook(int index) {
        if (mBookList.size() > index) {
            return mBookList.get(index);
        }
        return null;
    }

    /**
     * 打印 books 的书籍
     *
     * @param books     书单
     * @param titleText 标题
     */
    private void logBooks(List<Book> books, String titleText) {
        mStringBuilder.delete(0, mStringBuilder.length());
        mStringBuilder.append(titleText);
        for (int i = 0; i < books.size(); i++) {
            mStringBuilder.append((i + 1) + "  ")
                    .append(books.get(i))
                    .append("\n");
        }
        LogUtil.d(mStringBuilder.toString());
    }

    private void setTextBookNum() {
        mBookTotalNum.setText("书的数量：" + mBookList.size());
    }

    @OnClick({R.id.btn_checkData, R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_checkData:
//                goToCustomActivity(BookListActivity.class);
                break;

            case R.id.btn_add:
                // 1、插入单条数据
                insert(addBook());
                setTextBookNum();
                logBooks(mBookList, "insert\n");
                break;

            case R.id.btn_delete:
                if (!mBookList.isEmpty()) {
                    delete(Long.parseLong(mBookList.size() + ""));
                    mBookList.remove(mBookList.size() - 1);
                    setTextBookNum();
                    logBooks(mBookList, "delete\n");
                }
                break;

            case R.id.btn_update:
                if (!mBookList.isEmpty()) {
                    Book book = getBook(0);
                    book.setAuthor("凌桂钦");
                    mBookList.set(0, book);
                    update(book);
                    setTextBookNum();
                    logBooks(mBookList, "update\n");
                }
                break;
            case R.id.btn_query:
                queryAll();
                logBooks(mBookList, "queryAll\n");
                break;

        }
    }
}
