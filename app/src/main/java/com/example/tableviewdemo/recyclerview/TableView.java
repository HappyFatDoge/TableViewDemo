package com.example.tableviewdemo.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tableviewdemo.BookMessagesBean;
import com.example.tableviewdemo.R;

/**
 * Author    zhengchengbin
 * Describe：
 * Data:      2019/7/31 16:50
 * Modify by:
 * Modification date：
 * Modify content：
 */
public class TableView extends RecyclerView {

    private TableViewAdapter mAdapter;

    public TableView(@NonNull Context context) {
        this(context, null);
    }

    public TableView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new TableViewAdapter(getContext());
        setAdapter(mAdapter);
    }

    public void setTableItems(BookMessagesBean bookMessagesBean) {
        if (mAdapter != null) {
            mAdapter.setBookMessagesBean(bookMessagesBean);
            setTableHeader();
            setTableFooter();
        }
    }

    private void setTableHeader() {
        if (mAdapter != null) {
            View headerView = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_item_title_view, this, false);
            ((TextView)headerView.findViewById(R.id.tv_press)).setText("出版社");
            ((TextView)headerView.findViewById(R.id.tv_version)).setText("版本");
            ((TextView)headerView.findViewById(R.id.tv_grade)).setText("年级");
            mAdapter.setHeaderView(headerView);
        }
    }

    private void setTableFooter() {
        if (mAdapter != null) {
            View footerView = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_item_description_view, this, false);
            TextView textView = footerView.findViewById(R.id.tv_description);
            textView.setText(mAdapter.getBookMessageBean().getDescription());
            mAdapter.setFooterView(footerView);
        }
    }
}
