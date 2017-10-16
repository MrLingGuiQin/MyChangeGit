package com.linguiqing.mychanage.ui.db.greendao;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.LogUtil;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ***************************************
 * statement: 学习greendao
 * auther: lingguiqin
 * date created : 2017/10/14 0014
 * ***************************************
 */

public class StudyGreenDaoActivity extends BaseActivity {

    @BindView(R.id.tv_book_count_info)
    TextView mBookTotalNum;
    private BookDao mBookDao;
    private List<Book> mBookList;
    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_db;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "学习GreenDAO的使用", "", null);
        return titlebar;
    }

    @Override
    public void initData() {
        mBookDao = getApp().getDaoSession().getBookDao();
        mBookList = mBookDao.queryBuilder().list();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mBookTotalNum.setText("书的数量：" + mBookList.size());
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

    // 插入数据
    public void insert(Book book) {
        mBookDao.insert(book);
    }

    // 插入多条数据
    public void insertInTx(List<Book> bookList) {
        mBookDao.insertInTx(bookList);
    }

    // 插入多条数据
    public void insertOrReplaceInTx(List<Book> bookList) {
        mBookDao.insertOrReplaceInTx(bookList);
    }

    // 删除数据
    public void delete(Book book) {
        mBookDao.delete(book);
        mBookDao.deleteInTx();
    }

    // 删除数据
    public void deleteInTx(List<Book> bookList) {
        mBookDao.deleteInTx(bookList);
    }

    // 修改数据
    public void update(Book book) {
        mBookDao.update(book);
    }

    // 修改多条数据
    public void updateInTx(List<Book> bookList) {
        mBookDao.updateInTx(bookList);
    }

    // 查询所有数据
    public void queryAll() {
        List<Book> books = mBookDao.loadAll();
//      mBookDao.queryBuilder().list();
        logBooks(books, "输出所有书籍 ：\n");
    }

    // 按 equal = 条件查询数据
    // eg:查找符合 作者：胡歌1
    public void queryEq() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Author.eq("胡歌1")).list();
        logBooks(books, "输出作者是胡歌1 的书籍 ：\n");
    }

    // 按 notEqual != 条件查询数据
    // eg:查找不符合 作者：胡歌1
    public void queryNotEq() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Author.notEq("胡歌1")).list();
        logBooks(books, "输出作者不 是胡歌1 的书籍 ：\n");
    }

    // 按模糊条件查询数据
    // eg:查找 作者名字以胡歌开头的书籍
    // % 为占位通配符
    public void queryLike() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Author.like("胡歌%")).list();
        logBooks(books, "输出作者名字以胡歌开头的书籍 ：\n");
    }

    // 按参数区间条件查询数据
    // eg:查找 销量 20 - 100 本 的书籍
    public void queryBetween() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Shell_num.between(20, 100)).list();
        logBooks(books, "输出销量 20 - 100 本的书籍 ：\n");
    }

    // 按参数 > 条件查询数据
    // eg:查找 销量 >20 本 的书籍
    public void queryGt() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Shell_num.gt(20)).list();
        logBooks(books, "输出销量> 20 的书籍 ：\n");
    }

    // 按参数 >= 条件查询数据
    // eg:查找 销量 >=20 本 的书籍
    public void queryGe() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Shell_num.ge(20)).list();
        logBooks(books, "输出销量>= 20 的书籍 ：\n");
    }

    // 按参数 < 条件查询数据
    // eg:查找 销量 < 20 本 的书籍
    public void queryLt() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Shell_num.lt(20)).list();
        logBooks(books, "输出销量< 20 的书籍 ：\n");
    }

    // 按参数 <= 条件查询数据
    // eg:查找 销量 <=20 本 的书籍
    public void queryLe() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Shell_num.le(20)).list();
        logBooks(books, "输出销量<= 20 的书籍 ：\n");
    }

    // 按参数 isNull 条件查询数据
    // eg:查找 销量 = null 本 的书籍
    public void queryIsNull() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Shell_num.isNull()).list();
        logBooks(books, "输出销量= null 的书籍 ：\n");
    }

    // 按参数 isNotNull 条件查询数据
    // eg:查找 销量 <=null 本 的书籍
    public void queryIsNotNull() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Shell_num.isNotNull()).list();
        logBooks(books, "输出销量 != null 的书籍 ：\n");
    }


    // 升序排序
    // eg:所有书籍按销量升序排序
    public void queryAllAsc() {
        List<Book> books = mBookDao.queryBuilder().orderAsc(BookDao.Properties.Shell_num).list();
        logBooks(books, "输出书籍按销量升序排序 ：\n");
    }

    // 升序排序
    // eg:所有书籍按销量升序排序
    public void queryAllDesc() {
        List<Book> books = mBookDao.queryBuilder().orderDesc(BookDao.Properties.Shell_num).list();
        logBooks(books, "输出书籍按销量降序排序 ：\n");
    }

    // 使用sql条件进行查询
    // eg:输出 销量：>= 20 的书籍
    public void querySql() {
        List<Book> books = mBookDao.queryBuilder()
                .where(new WhereCondition.StringCondition("_id IN (SELECT _id FROM BOOK WHERE SHELL_NUM >= 20)")).list();
        logBooks(books, "输出 销量：>= 20 的书籍 ：\n");
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
                goToCustomActivity(BookListActivity.class);
                break;
            case R.id.btn_add:
                // 1、插入单条数据
                insert(addBook());
                List<Book> tempList = new ArrayList<>();

                // 2、插入5条数据
                for (int i = 0; i < 5; i++) {
                    tempList.add(addBook());
                }
                insertOrReplaceInTx(tempList);

                setTextBookNum();
                break;
            case R.id.btn_delete:
                if (!mBookList.isEmpty()) {
                    delete(getBook(mBookList.size() - 1));
                    mBookList.remove(mBookList.size() - 1);
                    setTextBookNum();
                }
                break;

            case R.id.btn_update:
                if (!mBookList.isEmpty()) {
                    Book book = getBook(0);
                    book.setAuthor("凌桂钦");
                    mBookList.set(0, book);
                    update(book);
                    setTextBookNum();
                }
                break;
            case R.id.btn_query:
                queryAll();
                queryEq();
                queryLike();
                queryBetween();
                queryGt();
                queryGe();
                queryLt();
                queryLe();
                queryIsNull();
                queryIsNotNull();
                queryAllAsc();
                queryAllDesc();
                querySql();
                break;

        }
    }
}
