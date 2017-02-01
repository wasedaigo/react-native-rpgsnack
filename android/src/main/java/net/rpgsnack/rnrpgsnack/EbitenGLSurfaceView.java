package com.rpgsnack.rnrpgsnack;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.rpgsnack.runtime.mobile.Mobile;

public class EbitenGLSurfaceView extends GLSurfaceView {

    private class EbitenRenderer implements Renderer {

        private boolean mErrored;

        @Override
        public void onDrawFrame(GL10 gl) {
            if (mErrored) {
                return;
            }
            try {
                if (Mobile.isRunning()) {
                    Mobile.update();
                }
            } catch (Exception e) {
                Log.e("Go Error", e.toString());
                mErrored = true;
            }
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
        }
    }

    private double mDeviceScale = 0.0;
    private int mWidth;
    private int mHeight;
    private Callback mOnLoaded;

    public interface Callback {
        void invoke();
    }

    public void setOnLoaded(final Callback callback) {
        mOnLoaded = callback;
    }

    public void SetWidth(int width) {
        mWidth = width;
    }

    public void SetHeight(int height) {
        mHeight = height;
    }

    public EbitenGLSurfaceView(Context context) {
        super(context);
        initialize();
    }

    public EbitenGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void setData(byte[] gamedata) {
        Mobile.setData(gamedata);
    }

    private void initialize() {
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        setRenderer(new EbitenRenderer());
    }

    public double getScaleInPx() {
        View parent = (View)getParent();
        return Math.min(
            mWidth / (double)Mobile.screenWidth(),
            mHeight / (double)Mobile.screenHeight()
        );
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, left + mWidth, top + mHeight);
        runGame();
    }

    private void runGame() {
        if (!Mobile.isRunning()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                    try {
                        if (!Mobile.isRunning()) {
                            Mobile.start(getScaleInPx());
                            if (mOnLoaded != null) {
                                mOnLoaded.invoke();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Go Error", e.toString());
                    }
                }
            });
        }
    }

    // pxToDp converts an value in pixels to dp.
    // Note that Ebiten's mobile.Start accepts size value in dp.
    private double pxToDp(double x) {
        if (mDeviceScale == 0.0) {
            mDeviceScale = getResources().getDisplayMetrics().density;
        }
        return x / mDeviceScale;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        for (int i = 0; i < e.getPointerCount(); i++) {
            int id = e.getPointerId(i);
            int x = (int)e.getX(i);
            int y = (int)e.getY(i);
            Mobile.updateTouchesOnAndroid(e.getActionMasked(), id, (int)pxToDp(x), (int)pxToDp(y));
        }
        return true;
    }
}
