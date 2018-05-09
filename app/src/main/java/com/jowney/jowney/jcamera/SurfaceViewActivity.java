package com.jowney.jowney.jcamera;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jowney.jowney.jcamera.camera.CameraHelper;
import com.jowney.jowney.jcamera.view.PreviewSurfaceView;

public class SurfaceViewActivity extends Activity {
    PreviewSurfaceView previewSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfaceview);

        previewSurfaceView = findViewById(R.id.id_main_PreviewSurfaceView);
        previewSurfaceView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CameraHelper.getInstance().switchCamera();
                return false;
            }
        });
       /* setContentView(R.layout.fragment_setting);
        SettingFragmen settingFragmen = new SettingFragmen();
        getFragmentManager().beginTransaction().replace(R.id.line1,settingFragmen).commit();*/
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
     //   previewSurfaceView.surfaceDestroyed(previewSurfaceView.holder);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
