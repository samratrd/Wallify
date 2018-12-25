package com.example.ritwik.wallify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class SetWallpaperActivity extends AppCompatActivity {

    private Bundle extras;
    private ImageView imageView;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton button_setas, button_share, button_download;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        imageView = findViewById(R.id.image_preview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Wallify");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extras = getIntent().getExtras();

        if (extras!=null){

            Glide.with(this)
                    .load(extras.getString("url"))
                    .into(imageView);


        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getSupportActionBar().isShowing()){
                    getSupportActionBar().hide();
                }
                else
                {
                    getSupportActionBar().show();
                }

            }
        });

        floatingActionMenu = findViewById(R.id.floating_action_menu);
        button_download = findViewById(R.id.download);
        button_setas = findViewById(R.id.set_as);
        button_share = findViewById(R.id.share);

        button_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle download
            }
        });


        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle share
            }
        });


        button_setas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set wallpaper
            }
        });
    }
}
