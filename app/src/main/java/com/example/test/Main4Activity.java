package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {
    Button btnFinish;
    public static int yyy = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setText(yyy+"");
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        yyy++;
        btnFinish.setText(yyy+"");
    }
}
