package com.dazhi.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dazhi.viewratingbar.ViewRatingBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ViewRatingBar vrbTest=findViewById(R.id.vrbTest);
        vrbTest.setOnRatingListener(new ViewRatingBar.OnRatingListener() {
            @Override
            public void onRating(View currView, int intCurrStarCount) {
                Toast.makeText(MainActivity.this, "当前评分："+intCurrStarCount, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
