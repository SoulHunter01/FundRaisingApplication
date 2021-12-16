package com.code.fundraisingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecyclerViewList extends AppCompatActivity {
    private static final String TAG = "XA1212";
    RecyclerView rv;
    ArrayList<ImageClass> ls;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);
        rv=findViewById(R.id.rv);
        ls=new ArrayList<>();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("GoalInformation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String getTitle= (String) document.getData().get("Title");
                                String getCategory=(String) document.getData().get("Category");
                                String getDescription=(String)document.getData().get("Description");
                                String getTargetAmount=(String)document.getData().get("TargetAmount");
                                String getStatus=(String)document.getData().get("Status");
                                ls.add(new ImageClass(getResources().getDrawable(R.drawable.goallogo),getTitle,getDescription,getTargetAmount,getCategory,getStatus));
                                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(RecyclerViewList.this, 1);
                                rv.setLayoutManager(mLayoutManager);
                                adapter = new RecyclerViewAdapter(ls,RecyclerViewList.this);
                                rv.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });












    }
}