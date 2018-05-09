package com.jowney.jowney.jcamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.jowney.jowney.jcamera.view.PreviewTextureView;

/**
 * Created by Jowney on 2018/5/8.
 */

public class TextureViewActivity extends Activity {
    private PreviewTextureView previewTextureView;
    private final static String TAG = "TextureViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textureview);
        previewTextureView = findViewById(R.id.id_main_PreviewTextureView);
        previewTextureView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i(TAG, "onLongClick: ");
                Intent intent = new Intent(TextureViewActivity.this, TestActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
