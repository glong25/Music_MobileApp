package com.ck4u.spotify.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ck4u.spotify.R;

public class LoginActivity extends AppCompatActivity {

    TextView regisPage, mainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mainPage = findViewById(R.id.tvgetting);
        regisPage = findViewById(R.id.create_account);
        regisPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startPage);
            }
        });
    }
}