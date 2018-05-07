package com.jowney.jowney.jcamera.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

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
    private SurfaceHolder holder;
    private Canvas canvas;
    private Matrix matrix;

    public PreviewSurfaceView(Context context) {
        super(context);

    }

    public PreviewSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = this.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.setFixedSize(640*3,480*3);
        holder.addCallback(this);
        matrix = new Matrix();

        float a =2560/640;
        float b = 1440/480;

        matrix.postScale(3,3);

       /* matrix.postScale(this.getWidth()/,2);
        matrix.postRotate(90);
        matrix.postTranslate(1400,0);*/
        new Thread(this).start();
        CameraHelper.getInstance().createCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {


    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        CameraHelper.getInstance().releaseCamera();
    }


    @Override
    public void run() {
        while (true) {
            draw();
        }


    }

    private synchronized void draw() {
        try {
            Bitmap videoBitmap = VideoFrameModel.getInstance().getVideoFrameBitmap(640, 480);
            canvas = holder.lockCanvas();

            canvas.drawColor(getResources().getColor(R.color.colorPrimaryDark));

            canvas.drawBitmap(videoBitmap, matrix, new Paint());


            // TODO: 2018/4/26  绘画

        } catch (Exception e) {

        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
