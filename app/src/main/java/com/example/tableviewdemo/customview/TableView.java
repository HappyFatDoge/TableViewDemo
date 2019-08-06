package com.example.tableviewdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.List;

/**
 * Author    zhengchengbin
 * Describe：
 * Data:      2019/8/1 14:15
 * Modify by:
 * Modification date：
 * Modify content：
 */
public class TableView extends View {

    private Paint mPaint;
    private int mBorderWith = DEFAULT_BORDER_WIDTH;
    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mTextSize = DEFAULT_TEXT_SIZE;
    private int mTextColor = DEFAULT_TEXT_COLOR;

    private List<String> mHeaderList;
    private List<String> mItemList;
    private String mFooterStr;

    private static final String TAG = "TableView";
    private static final int DEFAULT_BORDER_WIDTH = 10;
    private static final int DEFAULT_BORDER_COLOR = 0xffff0000;
    private static final int DEFAULT_TEXT_SIZE = 30;
    private static final int DEFAULT_TEXT_COLOR = 0xffff0000;
    private static final int TEXT_HORIZONTAL_PADDING = 20;
    private static final int TEXT_VERTICAL_PADDING = 50;

    public TableView(Context context) {
        this(context, null);
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.d(TAG, "calculateRowCount: " + calculateRowCount());
        int height = MeasureSpec.makeMeasureSpec((2 * TEXT_VERTICAL_PADDING + mTextSize) * calculateRowCount(), heightMode);
        setMeasuredDimension(widthMeasureSpec, height);
    }

    private int calculateRowCount() {
        int count = 0;
        if (mHeaderList != null) {
            count ++;
        }
        if (!TextUtils.isEmpty(mFooterStr)) {
            count ++;
        }
        if (mItemList != null) {
            count += mItemList.size();
        }
        return count;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBorder(canvas);

        int startX = 0;
        for (int index = 0; index < mHeaderList.size(); ++ index) {
            String header = mHeaderList.get(index);
            int x = startX + TEXT_HORIZONTAL_PADDING;
            int y = mTextSize + TEXT_VERTICAL_PADDING;
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);
            canvas.drawText(header, x, y, mPaint);

            startX += (int) mPaint.measureText(header) + 2 * TEXT_HORIZONTAL_PADDING;
//            drawUnderLine(canvas, 0, y + TEXT_VERTICAL_PADDING, width );
        }
    }

    /**
     * 画边框
     * @param canvas
     */
    private void drawBorder(Canvas canvas) {
        mPaint.setTextSize(mBorderWith);
        mPaint.setColor(mBorderColor);
        int rightX = getMeasuredWidth() - mBorderWith;
        int bottomY = getMeasuredHeight() - mBorderWith;
        // 上边框
        canvas.drawLine(0,0, rightX, 0, mPaint);
        // 左边框
        canvas.drawLine(0, 0, 0, bottomY, mPaint);
        // 下边框
        canvas.drawLine(0, bottomY, rightX, bottomY, mPaint);
        // 右边框
        canvas.drawLine(rightX, 0, rightX, bottomY, mPaint);
    }

    private void drawUnderLine(Canvas canvas, int startX, int startY, int width) {
        mPaint.setTextSize(mBorderWith);
        mPaint.setColor(mBorderColor);
        canvas.drawLine(startX,startY, startX + width, startY, mPaint);
    }

    public void setHeader(List<String> header) {
        this.mHeaderList = header;
        requestLayout();
    }
}
