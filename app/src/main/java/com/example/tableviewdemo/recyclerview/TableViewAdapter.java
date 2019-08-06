package com.example.tableviewdemo.recyclerview;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tableviewdemo.BookMessagesBean;
import com.example.tableviewdemo.BookPressMessages;
import com.example.tableviewdemo.DevelopProgress;
import com.example.tableviewdemo.R;

import java.util.List;

/**
 * Author    zhengchengbin
 * Describe：
 * Data:      2019/7/31 9:31
 * Modify by:
 * Modification date：
 * Modify content：
 */
public class TableViewAdapter extends RecyclerView.Adapter {

    // 书本开发进度信息
    private List<BookPressMessages> mBookPressMessages;
    private BookMessagesBean mBookMessagesBean;
    private Context mContext;

    private View mHeaderView;
    private View mFooterView;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public TableViewAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new ItemViewHolder(mHeaderView);
        } else if (viewType == TYPE_FOOTER) {
            return new ItemViewHolder(mFooterView);
        } else {
            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!isHeader(position) && !isFooter(position)) {
            if (mHeaderView == null) {
                setTableItem((ItemViewHolder) holder, position);
            } else {
                setTableItem((ItemViewHolder) holder, position - 1);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mBookMessagesBean == null) {
            return 0;
        }
        int size = mBookPressMessages.size();
        if (mHeaderView != null) {
            size ++;
        }
        if (mFooterView != null) {
            size ++;
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        }
        if (isFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL_ITEM;
    }

    private boolean isHeader(int position) {
        return (mHeaderView != null && position == 0);
    }

    private boolean isFooter(int position) {
        if (mFooterView != null && mBookMessagesBean != null) {
            if (mHeaderView == null) {
                return position == mBookPressMessages.size();
            }
            return position == mBookPressMessages.size() + 1;
        }
        return false;
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View HeaderView) {
        this.mHeaderView = HeaderView;
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View FooterView) {
        this.mFooterView = FooterView;
        notifyItemInserted(getItemCount() - 1);
    }

    public void setBookMessagesBean(BookMessagesBean bookMessagesBean) {
        this.mBookMessagesBean = bookMessagesBean;
        this.mBookPressMessages = bookMessagesBean.getBookPressMessages();
        notifyDataSetChanged();
    }

    public BookMessagesBean getBookMessageBean() {
        return mBookMessagesBean;
    }

    private void setTableItem(ItemViewHolder holder, int position) {
        BookPressMessages bookPressMessages = mBookPressMessages.get(position);
        holder.mTvPressName.setText(bookPressMessages.getPressName());

        List<DevelopProgress> developProgresses = bookPressMessages.getDevelopProgressList();
        int size = developProgresses.size();
        for (int index = 0 ; index < size ; ++ index) {
            DevelopProgress developProgress = developProgresses.get(index);
            View itemView = LayoutInflater.from(mContext).
                    inflate(R.layout.layout_item_versions_and_grades_view, holder.mRgVersionsAndGrades, false);

            itemView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, itemView.getMeasuredHeight());
            TextView versionDesc = itemView.findViewById(R.id.tv_version_desc);
            versionDesc.setText(developProgress.getVersion());


            TextView gradeDesc = itemView.findViewById(R.id.tv_grade_desc);
            String gradeStr = developProgress.getDevelopProgress();
            Log.d("TableViewAdapter", "grade: " + gradeStr);
            SpannableString span = new SpannableString(gradeStr);
            int start = gradeStr.indexOf("已上线");
            if (start > 0 && start + 3 < gradeStr.length()) {
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(mContext.getResources().getColor(R.color.color_0AB69D));
                span.setSpan(colorSpan, 0, start + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                gradeDesc.setText(span);
            } else {
                gradeDesc.setText(gradeStr);
            }

            Log.d("TableViewAdapter", "gradeDesc : " + developProgress.getDevelopProgress());
            holder.mRgVersionsAndGrades.addView(itemView, params);

            if (index != size - 1 ) {
                // 下划线
                View underLine = LayoutInflater.from(mContext).
                        inflate(R.layout.layout_underline, holder.mRgVersionsAndGrades, false);
                holder.mRgVersionsAndGrades.addView(underLine);
            }

        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvPressName;
        private LinearLayout mRgVersionsAndGrades;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView == mHeaderView || itemView == mFooterView) {
                return;
            }
            mTvPressName = itemView.findViewById(R.id.tv_press_name);
            mRgVersionsAndGrades = itemView.findViewById(R.id.rg_versions_and_grades);
        }
    }
}
