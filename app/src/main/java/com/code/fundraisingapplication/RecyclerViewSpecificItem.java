package com.code.fundraisingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RecyclerViewSpecificItem extends AppCompatActivity {

    TextView title_of_goal;
    TextView description_of_goal;
    TextView category_of_goal;
    TextView targetamount_of_goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_specific_item);
        title_of_goal=findViewById(R.id.title_of_goal);
        description_of_goal=findViewById(R.id.description_of_goal);
        category_of_goal=findViewById(R.id.category_of_goal);
        targetamount_of_goal=findViewById(R.id.targetamount_of_goal);



        Intent intent=getIntent();


        String title_get=null;
        String description_get=null;
        String category_get=null;
        String targetamount_get=null;

        title_get=intent.getStringExtra("Title");
        description_get=intent.getStringExtra("Description");
        category_get=intent.getStringExtra("Category");
        targetamount_get=intent.getStringExtra("TargetAmount");

        title_of_goal.setText(title_get);
        description_of_goal.setText(description_get);
        category_of_goal.setText(category_get);
        targetamount_of_goal.setText(targetamount_get);




    }
}