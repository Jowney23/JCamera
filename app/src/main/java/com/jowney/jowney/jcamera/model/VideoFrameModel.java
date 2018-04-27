package com.jowney.jowney.jcamera.model;

import android.graphics.Bitmap;

import com.jowney.jowney.jcamera.utile.BitMapUtils;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2018/4/27/23:35
 * Description:
 */

public class VideoFrameModel {
    private static VideoFrameModel videoFrameModel;
    private byte[] videoFrameData;
    public static VideoFrameModel getInstance() {
        if (videoFrameModel == null) {
            synchronized (VideoFrameModel.class) {
                if (videoFrameModel == null) {
                    videoFrameModel = new VideoFrameModel();
                }
            }
        }
        return videoFrameModel;
    }

    public synchronized byte[] getVideoFramebytes() {
        return videoFrameData;
    }
    public synchronized Bitmap getVideoFrameBitmap(int w,int h){

        return  BitMapUtils.rawByteArray2RGBABitmap2(videoFrameData,w,h);
    }

    public synchronized void setVideoFramebytes(byte[] videoFrameData) {
        this.videoFrameData = videoFrameData;
    }
}
