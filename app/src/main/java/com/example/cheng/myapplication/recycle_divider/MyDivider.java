package com.example.cheng.myapplication.recycle_divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by chengbiao on 2017/12/20.
 */

public abstract class MyDivider extends RecyclerView.ItemDecoration {

    private DividerModel mDividerModel;

    private Paint mPaint;


    public MyDivider() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public DividerModel getmDividerModel() {
        return mDividerModel;
    }

    public void setmDividerModel(DividerModel mDividerModel) {
        this.mDividerModel = mDividerModel;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Context context = parent.getContext();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            int itemPosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();
            DividerModel divider = getDivider(itemPosition);

            if (divider.leftLine.isShow) {
                int lineWidthPx = dp2px(context, divider.leftLine.width);
                int startPaddingPx = dp2px(context, divider.leftLine.startPadding);
                int endPaddingPx = dp2px(context, divider.leftLine.endPadding);
                drawChildLeftVertical(child, c, parent, divider.leftLine.color, lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.topLine.isShow) {
                int lineWidthPx = dp2px(context, divider.topLine.width);
                int startPaddingPx = dp2px(context, divider.topLine.startPadding);
                int endPaddingPx = dp2px(context, divider.topLine.endPadding);
                drawChildTopHorizontal(child, c, parent, divider.topLine.color, lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.rightLine.isShow) {
                int lineWidthPx = dp2px(context, divider.rightLine.width);
                int startPaddingPx = dp2px(context, divider.rightLine.startPadding);
                int endPaddingPx = dp2px(context, divider.rightLine.endPadding);
                drawChildRightVertical(child, c, parent, divider.rightLine.color, lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.bottomLine.isShow) {
                int lineWidthPx = dp2px(context, divider.bottomLine.width);
                int startPaddingPx = dp2px(context, divider.bottomLine.startPadding);
                int endPaddingPx = dp2px(context, divider.bottomLine.endPadding);
                drawChildBottomHorizontal(child, c, parent, divider.bottomLine.color, lineWidthPx, startPaddingPx, endPaddingPx);
            }
        }
    }

    private void drawChildBottomHorizontal(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int leftPadding = 0;
        int rightPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }

        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int top = child.getBottom() + params.bottomMargin;
        int bottom = top + lineWidthPx;
        mPaint.setColor(color);
        c.drawRect(left, top, right, bottom, mPaint);

    }


    private void drawChildRightVertical(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int left = child.getRight() + params.rightMargin;
        int right = left + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }


    private void drawChildTopHorizontal(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int leftPadding = 0;
        int rightPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int bottom = child.getTop() - params.topMargin;
        int top = bottom - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    private void drawChildLeftVertical(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int right = child.getLeft() - params.leftMargin;
        int left = right - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }

    public abstract DividerModel getDivider(int itemPosition);


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int itemPosition = layoutParams.getViewLayoutPosition();
        DividerModel divider = getDivider(itemPosition);

        if (divider == null) {
            divider = new DividerModel().create();
        }

        int left = divider.leftLine.isShow ? dp2px(view.getContext(), divider.leftLine.width) : 0;
        int top = divider.topLine.isShow ? dp2px(view.getContext(), divider.topLine.width) : 0;
        int right = divider.rightLine.isShow ? dp2px(view.getContext(), divider.rightLine.width) : 0;
        int bottom = divider.bottomLine.isShow ? dp2px(view.getContext(), divider.bottomLine.width) : 0;

        outRect.set(left, top, right, bottom);

    }

    private int dp2px(Context context, int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
