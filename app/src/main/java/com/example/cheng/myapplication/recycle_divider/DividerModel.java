package com.example.cheng.myapplication.recycle_divider;

/**
 * Created by chengbiao on 2017/12/20.
 */

public class DividerModel {

    public DividerLineModel leftLine;
    public DividerLineModel rightLine;
    public DividerLineModel topLine;
    public DividerLineModel bottomLine;

    public DividerModel create() {
        //提供一个默认不显示的sideline，防止空指针
        DividerLineModel defaultSideLine = new DividerLineModel(false, 0xff666666, 0, 0, 0);

        leftLine = (leftLine != null ? leftLine : defaultSideLine);
        topLine = (topLine != null ? topLine : defaultSideLine);
        rightLine = (rightLine != null ? rightLine : defaultSideLine);
        bottomLine = (bottomLine != null ? bottomLine : defaultSideLine);

        return new DividerModel(leftLine, rightLine, topLine, bottomLine);
    }

    public DividerModel() {
    }

    public DividerModel(DividerLineModel leftLine, DividerLineModel rightLine, DividerLineModel topLine, DividerLineModel bottomLine) {
        this.leftLine = leftLine;
        this.rightLine = rightLine;
        this.topLine = topLine;
        this.bottomLine = bottomLine;
    }
}
