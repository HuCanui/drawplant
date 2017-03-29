// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package com.hucanhui.vgandroid.testview.view;

import rhcad.touchvg.Const;
import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import rhcad.touchvg.core.MgMotion;
import rhcad.touchvg.view.SFGraphView;
import com.hucanhui.vgandroid.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

public class SFGraphView1 extends SFGraphView {
    protected static final String PATH = "mnt/sdcard/TouchVG/";
    protected int mFlags;

    static {
        System.loadLibrary("democmds");
    }

    public SFGraphView1(Context context) {
        this(context, null);
    }

    public SFGraphView1(Context context, Bundle savedInstanceState) {
        super(context, savedInstanceState);
        mFlags = ((Activity) context).getIntent().getExtras().getInt("flags");

        final IViewHelper helper = ViewFactory.createHelper(this);

        if (savedInstanceState == null) {
            registerFirstRegen(helper);
        }
        registerGestureListener(helper);
        startTestCommand(helper);

        setOnShapeDblClickedListener(new IGraphView.OnShapeDblClickedListener() {
            public boolean onShapeDblClicked(IGraphView view, int type, int sid, int tag) {
                Log.d(TAG, "SF onShapeDblClicked " + type);
                return false;
            }
        });
        setOnShapeClickedListener(new IGraphView.OnShapeClickedListener() {
            public boolean onShapeClicked(IGraphView view, int type, int sid, int tag, float x, float y) {
                Log.d(TAG, "SF onShapeClicked " + type);
                return false;
            }
        });
        setOnContextActionListener(new IGraphView.OnContextActionListener() {
            public boolean onContextAction(IGraphView view, MgMotion sender, int action) {
                Log.d(TAG, "SF onContextAction " + action);
                return false;
            }
        });
    }

    private void registerFirstRegen(final IViewHelper helper) {
        if ((mFlags & (TestFlags.RECORD | TestFlags.RAND_SHAPES)) != 0) {
            setOnFirstRegenListener(new IGraphView.OnFirstRegenListener() {

                public void onFirstRegen(IGraphView view) {
                    if ((mFlags & TestFlags.RAND_SHAPES) != 0) {
                        helper.addShapesForTest();
                    }
                    if ((mFlags & TestFlags.RECORD) != 0) {
                        helper.startRecord(PATH + "record");
                    }
                }
            });
        }
    }

    private void registerGestureListener(final IViewHelper helper) {
        if ((mFlags & TestFlags.SWITCH_CMD) != 0) {
            setOnGestureListener(new IGraphView.OnDrawGestureListener() {

                public boolean onPreGesture(int gestureType, float x, float y) {
                    if (gestureType == Const.GESTURE_DBLTAP) {
                        helper.switchCommand();
                        Toast.makeText(getContext(), helper.getCommand(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }

                public void onPostGesture(int gestureType, float x, float y) {
                    // Do nothing
                }
            });
        }
    }

    private void startTestCommand(final IViewHelper helper) {
        switch (mFlags & TestFlags.CMD_MASK) {
        case TestFlags.SELECT_CMD:
            helper.setCommand("select");
            break;
        case TestFlags.SPLINES_CMD:
            helper.setCommand("splines");
            break;
        case TestFlags.LINE_CMD:
            helper.setCommand("line");
            break;
        case TestFlags.LINES_CMD:
            helper.setCommand("lines");
            break;
        case TestFlags.HITTEST_CMD:
//            int n = DemoCmdsGate.registerCmds(helper.cmdViewHandle());
            helper.setCommand("dim_example"); // or "hittest"
//            Log.d("Test", "DemoCmdsGate.registerCmds = " + n + ", " + helper.getCommand());
            break;
        default:
            break;
        }
        if ((mFlags & TestFlags.CMD_PARAMETER) != 0) {
            helper.setStrokeWidth(3);
            helper.setCommand("triangle", "{'points':[-10,20, 30,-20, -30,-20]}");
            helper.setStrokeWidth(2);
            helper.setLineStyle(Const.DASH_LINE);
            helper.setCommand("line", "{'points':[-10,20,0,-20, 30,-20,-20,0, -30,-20,10,0]}");
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if ((mFlags & TestFlags.HAS_BACKDRAWABLE) != 0) {
            ViewGroup layout = (ViewGroup) getParent();
            this.setBackgroundDrawable(layout.getBackground());
        }
    }
}
