package com.jowney.jowney.jcamera;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.jowney.jowney.jcamera.camera.CameraBase;
import com.jowney.jowney.jcamera.camera.CameraHelper;
import com.jowney.jowney.jcamera.fragment.SettingFragmen;
import com.jowney.jowney.jcamera.model.VideoFrameModel;
import com.jowney.jowney.jcamera.view.PreviewSurfaceView;

public class MainActivity extends Activity {
    PreviewSurfaceView previewSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
