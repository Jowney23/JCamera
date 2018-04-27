package com.jowney.jowney.jcamera.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by Jowney on 2018/4/27.
 */

public abstract class CameraBase {


    public interface FrameCallBack {
        void callBack(byte[] bytes);
    }

    private static final int TEXTURE_NAME = 10;
    private final static int SUCCESS = 1;
    private final static int FAILURE = 0;
    private final static String TAG = "CameraBase";
    private Camera mCamera;
    private Camera.Parameters mParameters;
    private SurfaceTexture mSurfaceTexture;
    private int mCameraId = -1;

    /**
     * 打开摄像头摄像头
     *
     * @param cameraID
     * @return
     */
    public void startCamera(int cameraID) {

        if (mCamera != null) {
            stopCamera();
        }

        mSurfaceTexture = new SurfaceTexture(TEXTURE_NAME);
        mCamera = Camera.open(cameraID);
        mCameraId = cameraID;
        if (startPreview(mSurfaceTexture) != SUCCESS) {
            Log.i(TAG, "startCamera:  开启预览失败！！！！");
        }

    }

    /**
     * 摄像头打开后，可以切换摄像头
     */
    public void switchCamera(SurfaceTexture surfaceTexture) {

        if (mCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        }

        if (mCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            startCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
        }
    }

    /**
     * 开启预览后才可以从Camera中获取到视频的帧数据
     *
     * @param surfaceTexture
     * @return 开启预览成功或失败
     */
    private int startPreview(SurfaceTexture surfaceTexture) {
        if (mCamera == null) {
            return FAILURE;
        }
        try {
            mCamera.setPreviewTexture(surfaceTexture);
            mCamera.startPreview();
            return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            stopCamera();
            return FAILURE;
        }
    }

    public void updateCameraConfig() {
        // TODO: 2018/4/27  功能：相机配置更新
    }


    public void frameCallBack(final FrameCallBack frameCallBack) {
        mCamera.setPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] bytes, Camera camera) {
                frameCallBack.callBack(bytes);
            }
        });
    }


    /**
     * @return 是否关闭成功
     */
    public int stopCamera() {
        if (mCamera == null)
            return FAILURE;
        try {
            mCamera.setPreviewDisplay(null);
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return FAILURE;
        } finally {
            mCamera.release();
            if (mSurfaceTexture != null) {
                mSurfaceTexture.release();
                mSurfaceTexture = null;
            }
            mCamera = null;
            mCameraId = -1;
        }
    }


}
