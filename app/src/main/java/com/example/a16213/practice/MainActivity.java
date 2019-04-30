package com.example.a16213.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a16213.practice.Image.ImageActivity;
import com.example.a16213.practice.Layout.LayoutActivity;
import com.example.a16213.practice.Poem.PoemActivity;

public class MainActivity extends AppCompatActivity {

    private TextView poem;
    private TextView layout;
    private TextView image;
    long lasttime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis-lasttime>2){
            Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
            lasttime = currentTimeMillis;
        }else {
            super.onBackPressed();
        }

    }

    private void initView() {
        poem = (TextView) findViewById(R.id.poem);
        layout = (TextView) findViewById(R.id.layout);
        image = (TextView) findViewById(R.id.image);
        poem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PoemActivity.class);
                startActivity(intent);
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LayoutActivity.class);
                startActivity(intent);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
            }
        });
    }
}
