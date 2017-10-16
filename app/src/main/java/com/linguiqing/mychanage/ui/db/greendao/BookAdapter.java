package com.linguiqing.mychanage.ui.db.greendao;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseRecyclerViewHolder;

import java.util.List;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/10/14 0014
 * ***************************************
 */
public class BookAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    private List<Book> mBookStoreList;

    public BookAdapter(List<Book> bookStoreList) {
        mBookStoreList = bookStoreList;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        Book bookStore = mBookStoreList.get(position);
        holder.setText(R.id.tv_item_book_name, "书名：" + bookStore.getName());
        holder.setText(R.id.tv_item_book_author, "作者：" + bookStore.getAuthor());
        holder.setText(R.id.tv_item_book_shell, "销量：" + bookStore.getShell_num());
        holder.setText(R.id.tv_item_book_price, "￥" + bookStore.getPrice());
//        holder.setImageByUrl(R.id.img_item_book, bookStore.getImage_url());
    }


    @Override
    public int getItemCount() {
        return mBookStoreList.isEmpty() ? 0 : mBookStoreList.size();
    }
}
