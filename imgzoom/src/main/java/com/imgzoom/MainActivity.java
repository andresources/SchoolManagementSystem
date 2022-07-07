package com.imgzoom;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ImageViewZoom imageViewZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewZoom = findViewById(R.id.activity_main_image_view);
        // Return Image's base 64 code
        imageViewZoom.getBase64();

        // ImageViewZoomConfig
        // OnlyDialog Enum work only user when click to save choose
        // Always Enum work when use saveImage() method and user when click to save choose
        ImageViewZoomConfig imageViewZoomConfig = new ImageViewZoomConfig.Builder().saveProperty(true).saveMethod(ImageViewZoomConfig.ImageViewZoomConfigSaveMethod.onlyOnDialog).build();


        imageViewZoom.setConfig(imageViewZoomConfig);


        // Save Image
        imageViewZoom.saveImage(MainActivity.this, "ImageViewZoom", "test", Bitmap.CompressFormat.JPEG, 1, imageViewZoomConfig, new SaveFileListener() {
            @Override
            public void onSuccess(File file) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception exception) {
                Toast.makeText(MainActivity.this, "Error Save", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}