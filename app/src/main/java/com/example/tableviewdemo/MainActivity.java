package com.example.tableviewdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import com.example.tableviewdemo.gridlayout.TableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookMessagesBean bookMessagesBean;

    private static final String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        drawTableView();
//        drawGridTableView();
    }

    /**
     * 继承Gridlayout的表格View
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawGridTableView() {
        TableView tableView = findViewById(R.id.table_view);
        tableView.setTableItems(bookMessagesBean);
    }

    /**
     * 继承RecyclerView的表格View
     */
    private void drawRecyclerViewTableView() {
        com.example.tableviewdemo.recyclerview.TableView tableViewByRv = findViewById(R.id.recycler_view);
        tableViewByRv.setNestedScrollingEnabled(false);
        tableViewByRv.setTableItems(bookMessagesBean);
    }

    private void drawTableView() {
        com.example.tableviewdemo.customview.TableView tableView = findViewById(R.id.coustomview_table_view);
        List<String> header = new ArrayList<>();
        header.add("出版社");
        header.add("版本");
        header.add("年级");
        tableView.setHeader(header);
    }

    private void initData() {
        List<BookPressMessages> items = new ArrayList<>();
        List<DevelopProgress> progresses = new ArrayList<>();
        progresses.add(new DevelopProgress("人教版",
                "三年级、四（上）、五（上）已上线\n四（下）、五（上）、六年级开发中"));
        progresses.add(new DevelopProgress("人教新起点",
                "一、二、三年级、六（下）已上线\n四、五年级、六（上）开发中"));
        items.add(new BookPressMessages("人民教育出版社",progresses));
        progresses = new ArrayList<>();
        progresses.add(new DevelopProgress("外研新标准版（一年级起点）",
                "一、二、三年级已上线\n四、五、六年级开发中"));
        progresses.add(new DevelopProgress("外研新标准版（三年级起点）",
                "三年级、四（下）、五（下）已上线\n四（上）、五（上）、六年级开发中"));
        progresses.add(new DevelopProgress("人教新起点",
                "一、二、三年级、六（下）已上线\n四、五年级、六（上）开发中"));
        items.add(new BookPressMessages("外语教学与研究出版社", progresses));

        bookMessagesBean = new BookMessagesBean(items, "说明：后期会陆续制作并上线各个出版社的英语教材，请敬请期待！");
    }
}
