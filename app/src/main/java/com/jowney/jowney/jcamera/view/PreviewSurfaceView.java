package com.jowney.jowney.jcamera.view;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jowney.jowney.jcamera.camera.CameraBase;
import com.jowney.jowney.jcamera.camera.CameraConfig;
import com.jowney.jowney.jcamera.camera.CameraHelper;

/**
 * Created by Jowney on 2018/4/26.
 */

public class PreviewSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private final static String TAG = "PreviewSurfaceView";
    private SurfaceHolder holder;
    private Canvas canvas;


    public PreviewSurfaceView(Context context) {
        super(context);
        holder = this.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);
    }

    public PreviewSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        CameraHelper.getInstance().startCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {

        CameraHelper.getInstance().frameCallBack(new CameraBase.FrameCallBack() {
            @Override
            public void callBack(byte[] bytes) {
                draw();
                Log.i(TAG, "callBack: "+Thread.currentThread().getName()+": "+Thread.currentThread().getId());
            }
        });

    }

    private synchronized void draw(){
        try {
            canvas = holder.lockCanvas();



            // TODO: 2018/4/26  绘画

        } catch (Exception e) {

        } finally {
            if (canvas != null){
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
