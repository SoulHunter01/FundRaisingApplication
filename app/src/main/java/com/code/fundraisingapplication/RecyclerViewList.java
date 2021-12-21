package com.code.fundraisingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText sh;
    public static String goalname;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);
        rv=findViewById(R.id.rv);
        ls=new ArrayList<>();

        sh = (EditText) findViewById(R.id.sh);

        sh.setText(goalname);

        sh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    filter(editable.toString());
            }
        });


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
                                ls.add(new ImageClass(getResources().getDrawable(R.drawable.logo),getTitle,getDescription,getTargetAmount,getCategory,getStatus));
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

    private void filter(String text) {
        ArrayList<ImageClass> filteredList = new ArrayList<>();

        for (ImageClass item : ls) {
            if (item.getGoal_title().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
}