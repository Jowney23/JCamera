package com.jowney.jowney.jcamera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jowney.jowney.jcamera.view.PreviewSurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreviewSurfaceView previewSurfaceView = new PreviewSurfaceView(this);
        setContentView(previewSurfaceView);
    }
}
