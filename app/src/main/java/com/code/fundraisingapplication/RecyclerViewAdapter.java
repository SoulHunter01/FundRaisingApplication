package com.code.fundraisingapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.internal.BaselineLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<ImageClass> ls;
    Context c;


    public RecyclerViewAdapter(List<ImageClass> ls, Context c) {
        this.ls = ls;
        this.c = c;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.image.setImageDrawable(ls.get(position).getImage());
        holder.Goal_title.setText(ls.get(position).getGoal_title());
        holder.Goal_targetamount.setText(ls.get(position).getGoal_targetamount());
        holder.Goal_category.setText(ls.get(position).getGoal_category());

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public void filterList(ArrayList<ImageClass> filteredList) {
        ls = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder<V> extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView Goal_title;
        TextView Goal_targetamount;
        TextView Goal_category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_to_show);
            Goal_title=itemView.findViewById(R.id.title);
            Goal_targetamount=itemView.findViewById(R.id.targetamount);
            Goal_category=itemView.findViewById(R.id.category);

        }


        }
    }

