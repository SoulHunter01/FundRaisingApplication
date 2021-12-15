package com.code.fundraisingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.core.view.Change;

public class MainScreen_Tabs extends AppCompatActivity {
    Button creategoalbutton;
    Button ChangeActiveStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_tabs);
        creategoalbutton=findViewById(R.id.creategoalbutton);
        ChangeActiveStatus=findViewById(R.id.ChangeActiveStatus);
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



    }
}