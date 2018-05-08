package com.jowney.jowney.jcamera.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.jowney.jowney.jcamera.R;
import com.jowney.jowney.jcamera.camera.CameraBase;
import com.jowney.jowney.jcamera.camera.CameraConfig;
import com.jowney.jowney.jcamera.camera.CameraHelper;
import com.jowney.jowney.jcamera.model.VideoFrameModel;

/**
 * Created by Jowney on 2018/4/26.
 */

public class PreviewSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private final static String TAG = "PreviewSurfaceView";
    public SurfaceHolder holder;
    private Canvas canvas;
    private Matrix matrix;
    private float suitableScale;//找一个合适的比例对宽高
    private Thread thread;
    private Boolean stopThread = false;

    public PreviewSurfaceView(Context context) {
        super(context);

    }

    public PreviewSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //根据屏幕宽高像素对预览视频进行适当的缩放
        suitableScale = getSuitableScale();
        holder = this.getHolder();

        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //这个可以设置SurfaceVew的大小（设置SurfaceView与预览视频的大小一样）
        holder.setFixedSize(640 * (int) suitableScale, 480 * (int) suitableScale);
        holder.addCallback(this);
        matrix = new Matrix();
        matrix.postScale(suitableScale, suitableScale);
        matrix.postScale(-1, 1);
        matrix.postTranslate(640 * suitableScale, 0);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surfaceCreated: ");
        stopThread = false;
        thread = new Thread(this);
        thread.start();
        CameraHelper.getInstance().createCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);


    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.i(TAG, "surfaceChanged: ");


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        CameraHelper.getInstance().releaseCamera();

    }


    @Override
    public void run() {
        while (true) {
            try {
                Bitmap videoBitmap = VideoFrameModel.getInstance().getVideoFrameBitmap(640, 480);
                if (videoBitmap == null) continue;
                canvas = holder.lockCanvas();

                canvas.drawColor(getResources().getColor(R.color.colorPrimaryDark));

                canvas.drawBitmap(videoBitmap, matrix, new Paint());

                Thread.sleep(1);//用于线程中断
            } catch (Exception e) {
                e.printStackTrace();

                Log.i(TAG, "run: " + "我被中断了888888888888888888");
                canvas.drawColor(getResources().getColor(R.color.colorBlack));

                Log.i(TAG, "run: " + "我被中断了888888888888888888" + thread.isInterrupted());
                return;
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    public float getSuitableScale() {
        int screenWidthPixels;
        int screenHeightPixels;
        float wScale;//实际宽度占屏幕宽度比例
        float hScale;//实际高度占屏幕高度比例
        //获取屏幕实际宽高像素
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        screenWidthPixels = dm.widthPixels;
        screenHeightPixels = dm.heightPixels;
        //计算预览视频与屏幕的宽高比例
        wScale = screenWidthPixels / 640;
        hScale = screenHeightPixels / 480;
        //从两个中间选一个合适的

        if (wScale == hScale) {
            return wScale;
        } else if (wScale * 480 < screenHeightPixels) {
            return wScale;
        } else if (hScale * 640 < screenWidthPixels) {
            return hScale;
        } else {
            return 0;
        }
    }
}
