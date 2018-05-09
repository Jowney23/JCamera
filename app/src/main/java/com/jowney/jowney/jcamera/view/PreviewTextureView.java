package com.jowney.jowney.jcamera.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView;

import com.jowney.jowney.jcamera.camera.CameraHelper;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2018/5/9/23:23
 * Description:
 */

public class PreviewTextureView extends TextureView implements TextureView.SurfaceTextureListener {

    private final static String TAG = "PreviewTextureView";

    public PreviewTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        CameraHelper.getInstance().createCamera(Camera.CameraInfo.CAMERA_FACING_FRONT,surfaceTexture);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        CameraHelper.getInstance().releaseCamera();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Log.i(TAG, "onSurfaceTextureUpdated: ");
    }
}
