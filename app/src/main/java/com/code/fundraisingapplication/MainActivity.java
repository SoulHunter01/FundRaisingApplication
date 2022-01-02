package com.code.fundraisingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DELAY=2500;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setBackgroundDrawable(null);
        initializeview();
        animatelogo();
        gotoMainActivity();



    }


    private void animatelogo(){

        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(30000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        ImageView image= (ImageView) findViewById(R.id.logo_image);
        image.startAnimation(rotate);


    }

    private void gotoMainActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent newi=new Intent(MainActivity.this,LoginScreen.class);
                startActivity(newi);
                finish();
            }
        },SPLASH_DELAY);

    }

    private void initializeview(){
        imageView=findViewById(R.id.logo_image);

    }


}