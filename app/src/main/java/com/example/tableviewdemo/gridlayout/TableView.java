package com.example.tableviewdemo.gridlayout;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.tableviewdemo.BookMessagesBean;
import com.example.tableviewdemo.BookPressMessages;
import com.example.tableviewdemo.DevelopProgress;
import com.example.tableviewdemo.R;

import java.util.List;

/**
 * Author    zhengchengbin
 * Describe：
 * Data:      2019/7/29 14:26
 * Modify by:
 * Modification date：
 * Modify content：
 */
public class TableView extends GridLayout {

    private BookMessagesBean mBookMessagesBean;
    private int mRowCount;
    private int mColumnCount;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_RIGHT = 1;
    private static final int TYPE_BOTTOM = 2;
    private static final int TYPE_RIGHT_BOTTOM = 3;
    private static final int TYPE_TITLE_NORMAL = 4;
    private static final int TYPE_TITLE_RIGHT = 5;
    private static final int TYPE_DESCRIPTION = 6;

    private static final String TAG = "TableViewGL";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TableView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRowCount = calculateRowCount();
        mColumnCount = 3;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initTable() {
        setRowCount(mRowCount);
        setColumnCount(mColumnCount);
        setTitle();
        List<BookPressMessages> mTableItems = mBookMessagesBean.getBookPressMessages();
        int pressRow = 0;
        int size = mBookMessagesBean.getDescription() == null ? mRowCount : mRowCount - 1;
        for (int row = 1 ; row < size ;) {
            // 出版社
            BookPressMessages item = mTableItems.get(pressRow ++);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    GridLayout.spec(row,item.getDevelopProgressList().size(), 1.0f),
                    GridLayout.spec(0, 1, 3.0f));
            if (mBookMessagesBean.getDescription() == null && pressRow == mTableItems.size()) {
                setItemParams(params, item.getPressName(), TYPE_BOTTOM);
            } else {
                setItemParams(params, item.getPressName(), TYPE_NORMAL);
            }

            for (DevelopProgress progress : item.getDevelopProgressList()) {
                //版本
                params = new GridLayout.LayoutParams(
                        GridLayout.spec(row,  1, 1.0f),
                        GridLayout.spec(1, 1, 4.0f));
                if (mBookMessagesBean.getDescription() == null && row == size - 1) {
                    setItemParams(params, progress.getVersion(), TYPE_BOTTOM);
                } else {
                    setItemParams(params, progress.getVersion(), TYPE_NORMAL);
                }

                //年级
                params = new GridLayout.LayoutParams(
                        GridLayout.spec(row++, 1, 1.0f),
                        GridLayout.spec(2, 1, 7.0f));
                if (mBookMessagesBean.getDescription() == null && row == size) {
                    setItemParams(params, progress.getDevelopProgress(), TYPE_RIGHT_BOTTOM);
                } else {
                    setItemParams(params, progress.getDevelopProgress(), TYPE_RIGHT);
                }
            }
        }
        setDescription();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setTableItems(BookMessagesBean bookMessagesBean) {
        this.mBookMessagesBean = bookMessagesBean;
        mRowCount = calculateRowCount();
        if (mBookMessagesBean != null) {
            initTable();
        }
    }

    private int calculateRowCount() {
        int rowCount = 0;
        if (mBookMessagesBean == null || mBookMessagesBean.getBookPressMessages().size() == 0) {
            return rowCount;
        }
        for (BookPressMessages item : mBookMessagesBean.getBookPressMessages()) {
            rowCount += item.getDevelopProgressList().size();
        }
        return mBookMessagesBean.getDescription() == null ? rowCount + 1 : rowCount + 2;
    }

    private void setItemParams(GridLayout.LayoutParams params, String text, int type){
        TextView press = new TextView(getContext());
        int start = text.indexOf("已上线");
        Log.d(TAG, "text : " + text + "; start : " + start);
        if (start > 0 && start + 3 < text.length()) {
            Log.d(TAG, "end: '" + text.charAt(start + 3) + "'");
//            StringBuilder textBuilder = new StringBuilder(text.substring(0, start + 3));
//            textBuilder.append("\n");
//            textBuilder.append(text.substring(start + 3));
            SpannableString span = new SpannableString(text);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.color_0AB69D));
            span.setSpan(colorSpan, 0, start + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            press.setText(span);
        } else {
            press.setText(text);
        }
        press.setPadding(0,40,0,40);
        press.setGravity(Gravity.CENTER);
        switch (type) {
            case TYPE_NORMAL:
                press.setBackgroundResource(R.drawable.shape_table_item);
                break;
            case TYPE_RIGHT:
                press.setBackgroundResource(R.drawable.shape_table_item_right);
                break;
            case TYPE_BOTTOM:
                press.setBackgroundResource(R.drawable.shape_table_item_bottom);
                break;
            case TYPE_RIGHT_BOTTOM:
                press.setBackgroundResource(R.drawable.shape_table_item_bottom_right);
                break;
            case TYPE_TITLE_NORMAL:
                press.setTextColor(0xFF0AB69D);
                press.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                press.setBackgroundResource(R.drawable.shape_table_item_title);
                break;
            case TYPE_TITLE_RIGHT:
                press.setTextColor(0xFF0AB69D);
                press.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                press.setBackgroundResource(R.drawable.shape_table_item_title_right);
                break;
            case TYPE_DESCRIPTION:
                press.setPadding(10,10,0,10);
                press.setGravity(Gravity.START);
                press.setBackgroundResource(R.drawable.shape_table_item_bottom_right);
                break;
        }
        params.width = 0;
        addView(press, params);
        Log.d(TAG, "getText: " + press.getText());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTitle() {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                GridLayout.spec(0,1, 1.0f),
                GridLayout.spec(0,1,3.0f));
        setItemParams(params,"出版社", TYPE_TITLE_NORMAL);

        params = new GridLayout.LayoutParams(
                GridLayout.spec(0,1, 1.0f),
                GridLayout.spec(1,1, 4.0f));
        setItemParams(params,"版本", TYPE_TITLE_NORMAL);

        params = new GridLayout.LayoutParams(
                GridLayout.spec(0,1, 1.0f),
                GridLayout.spec(2,1, 7.0f));
        setItemParams(params,"年级", TYPE_TITLE_RIGHT);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setDescription() {
        if (mBookMessagesBean.getDescription() != null) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    GridLayout.spec(mRowCount - 1, 1, 1.0f),
                    GridLayout.spec(0, 3, 1.0f));
            setItemParams(params, mBookMessagesBean.getDescription(), TYPE_DESCRIPTION);
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }
}
