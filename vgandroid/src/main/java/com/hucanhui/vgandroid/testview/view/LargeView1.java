//! \file LargeView1.java
//! \brief 测试长幅绘图的滚动视图类
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package com.hucanhui.vgandroid.testview.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ScrollView;

//! 测试长幅绘图的滚动视图类
public class LargeView1 extends ScrollView {

    public LargeView1(Context context) {
        super(context);
        this.createContentView(context);
    }

    private void createContentView(Context context) {
        final StdGraphView1 view = new StdGraphView1(context);
        final FrameLayout layout = new FrameLayout(context);
        layout.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, 2048));
        this.addView(layout);
    }
}
