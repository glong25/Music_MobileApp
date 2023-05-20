package com.ck4u.spotify.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ck4u.spotify.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    ImageView imgSearch, imgDanhsach, imgSetting, imgVuanghe;
    FloatingActionButton playMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();

    }

    private void initView() {
        imgSearch = findViewById(R.id.search);
        imgDanhsach = findViewById(R.id.listview);
        imgSetting = findViewById(R.id.home_setting);
        playMusic = findViewById(R.id.floatingbtn);

        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MusicDetailActivity.class);
                startActivity(intent);
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        imgDanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listPage = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(listPage);
            }
        });
    }
}