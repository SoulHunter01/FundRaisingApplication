package com.code.fundraisingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainScreen_Tabs extends AppCompatActivity {
    Button creategoalbutton;
    Button ChangeActiveStatus;
    Button btn_scan;
    Button show_ad;
    private AdView mAdView1;
    private AdView mAdView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_tabs);
        creategoalbutton=findViewById(R.id.creategoalbutton);
        ChangeActiveStatus=findViewById(R.id.ChangeActiveStatus);
        btn_scan = (Button) findViewById(R.id.btn_scan);

        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView1 = findViewById(R.id.ad1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);



        creategoalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen_Tabs.this,CreateGoal.class);
                startActivity(intent);
            }
        });

        ChangeActiveStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen_Tabs.this,RecyclerViewList.class);
                startActivity(intent);
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen_Tabs.this,Qr_Scan_Code.class);
                startActivity(intent);
            }
        });



    }
}