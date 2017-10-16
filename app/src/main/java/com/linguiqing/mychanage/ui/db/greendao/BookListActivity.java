package com.linguiqing.mychanage.ui.db.greendao;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;


/**
 * ***************************************
 * statement: 书列表
 * auther: lingguiqin
 * date created : 2017/10/14 0014
 * ***************************************
 */
public class BookListActivity extends BaseActivity {
    @BindView(R.id.rcy_book_list)
    RecyclerView mRecylerView;
    List<Book> mBookStoreList;
    private BookDao mBookDao;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_book_list;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "冰淇淋书店", "", null);
        return titlebar;
    }

    @Override
    public void initData() {
        // 查询数据库获取数据
        mBookDao = getApp().getDaoSession().getBookDao();
//        mBookStoreList = mBookDao.queryBuilder().list();
        mBookStoreList = mBookDao.loadAll();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        BookAdapter adapter = new BookAdapter(mBookStoreList);
        mRecylerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecylerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRecylerView.setAdapter(adapter);
    }
}
