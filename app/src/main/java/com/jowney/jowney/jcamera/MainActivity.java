package com.jowney.jowney.jcamera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jowney.jowney.jcamera.camera.CameraHelper;
import com.jowney.jowney.jcamera.fragment.SettingFragmen;
import com.jowney.jowney.jcamera.view.PreviewSurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreviewSurfaceView previewSurfaceView = new PreviewSurfaceView(this);
        setContentView(previewSurfaceView);
       /* setContentView(R.layout.activity_main);
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
}
