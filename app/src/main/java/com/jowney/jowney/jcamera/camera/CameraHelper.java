package com.jowney.jowney.jcamera.camera;

import android.graphics.SurfaceTexture;
import android.view.Surface;

/**
 * Created by mc on 2018/4/3.
 */

public class CameraHelper extends CameraBase {
    private static CameraHelper instance;


    public static CameraHelper getInstance() {
        if (instance == null) {
            synchronized (CameraHelper.class){
                if (instance == null){
                    instance = new CameraHelper();

                }
            }
        }
        return instance;
    }


}
