package com.example.ritwik.wallify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SetWallpaperActivity extends AppCompatActivity {

    private Bundle extras;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        imageView = findViewById(R.id.image_preview);

        extras = getIntent().getExtras();

        if (extras!=null){

            Glide.with(this)
                    .load(extras.getString("url"))
                    .into(imageView);


        }
    }
}
