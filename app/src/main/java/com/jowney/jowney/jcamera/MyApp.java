package com.jowney.jowney.jcamera;

import android.Manifest;
import android.app.Application;

import com.yanzhenjie.permission.AndPermission;

/**
 * Creator: Jowney  (~._.~)
 * Date: 2018/5/7/21:50
 * Description:
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(!AndPermission.hasPermission(this, Manifest.permission.CAMERA)){
           AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.CAMERA)
                    .send();
        }

    }

}
