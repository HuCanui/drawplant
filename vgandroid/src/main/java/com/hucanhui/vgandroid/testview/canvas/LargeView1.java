//! \file LargeView1.java
//! \brief 测试长幅绘图的滚动视图类
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package com.hucanhui.vgandroid.testview.canvas;

import rhcad.touchvg.core.TestCanvas;
import com.hucanhui.vgandroid.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

//! 测试长幅绘图的滚动视图类
public class LargeView1 extends ScrollView {

    public LargeView1(Context context) {
        super(context);
        this.createContentView(context);
    }

    public LargeView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.createContentView(context);
    }

    public LargeView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.createContentView(context);
    }

    private void createContentView(Context context) {
        final int flags = ((Activity) context).getIntent().getExtras().getInt("flags");
        View view = null;

        if ((flags & TestFlags.LARGE_VIEW) != 0) {
            if ((flags & TestCanvas.kDynCurves) != 0) {
                view = new GraphView2(context);
            } else {
                view = new GraphView1(context);
            }
        } else if ((flags & TestFlags.LARGE_SURFACEVIEW) != 0) {
            if ((flags & TestCanvas.kDynCurves) != 0) {
                view = new SurfaceView3(context);
            } else {
                view = new SurfaceView2(context);
            }
            // OpenGLRenderer(4096x4096): Shape rect too large to be rendered into a texture
        }

        if (view != null) {
            final FrameLayout layout = new FrameLayout(context);
            layout.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, 2048));
            this.addView(layout);
        }
    }
}
