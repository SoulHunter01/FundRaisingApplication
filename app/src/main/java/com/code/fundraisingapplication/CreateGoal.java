package com.code.fundraisingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateGoal extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    String category;
    EditText title;
    EditText description;
    EditText targetamount;
    Button creategoal;

    private static final String[] paths = {"Education","Climate Change","Medical Support"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);
         title=findViewById(R.id.title);
         description=findViewById(R.id.description);
         targetamount=findViewById(R.id.targetamount);
         creategoal=findViewById(R.id.creategoalbutton);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(CreateGoal.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        creategoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateGoal.this, RecyclerViewList.class);
                intent.putExtra("Title",title.getText().toString());
                intent.putExtra("Description",description.getText().toString());
                intent.putExtra("TargetAmount",targetamount.getText().toString());
                intent.putExtra("Category",category);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                category=paths[0];
                break;
            case 1:
                category=paths[1];
                break;
            case 2:
                category=paths[2];
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }




}

