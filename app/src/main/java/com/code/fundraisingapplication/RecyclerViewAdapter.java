package com.code.fundraisingapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>  {


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

        final String[] get_title_from_recycler = {null};
        final String[] get_description_from_recycler = {null};
        final String[] get_category_from_recyler = {null};
        final String[] get_target_amount_from_recycler = {null};
        final String[] get_status_from_recycler={null};



        holder.image.setImageDrawable(ls.get(position).getImage());
        holder.Goal_title.setText(ls.get(position).getGoal_title());
        holder.Goal_targetamount.setText(ls.get(position).getGoal_targetamount());
        holder.Goal_category.setText(ls.get(position).getGoal_category());
        holder.Goal_status.setText(ls.get(position).getGoal_status());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos!=RecyclerView.NO_POSITION){
                    get_title_from_recycler[0] = ls.get(pos).getGoal_title();
                    get_description_from_recycler[0] =ls.get(pos).getGoal_short_description();
                    get_category_from_recyler[0] =ls.get(pos).getGoal_category();
                    get_target_amount_from_recycler[0] =ls.get(pos).getGoal_targetamount();
                    get_status_from_recycler[0]=ls.get(pos).getGoal_status();

                    Intent intent=new Intent(c,RecyclerViewSpecificItem.class);
                    intent.putExtra("Title",get_title_from_recycler[0]);
                    intent.putExtra("Description",get_description_from_recycler[0]);
                    intent.putExtra("Category",get_category_from_recyler[0]);
                    intent.putExtra("TargetAmount",get_target_amount_from_recycler[0]);
                    intent.putExtra("Status",get_status_from_recycler[0]);

                    v.getContext().startActivity(intent);
                }



            }
        });


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
        TextView Goal_status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_to_show);
            Goal_title=itemView.findViewById(R.id.title);
            Goal_targetamount=itemView.findViewById(R.id.targetamount);
            Goal_category=itemView.findViewById(R.id.category);
            Goal_status=itemView.findViewById(R.id.status);

        }



    }
    }

