package com.code.fundraisingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerViewList extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<ImageClass> ls;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);
        Intent intent = getIntent();
        String getTitle=intent.getStringExtra("Title");
        String getDescription=intent.getStringExtra("Description");
        String getTargetamount=intent.getStringExtra("TargetAmount");
        String getCategory=intent.getStringExtra("Category");

        rv=findViewById(R.id.rv);
        ls=new ArrayList<>();
        ls.add(new ImageClass(getResources().getDrawable(R.drawable.goallogo),getTitle,null,getTargetamount,getCategory));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(mLayoutManager);
        adapter = new RecyclerViewAdapter(ls,this);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
}