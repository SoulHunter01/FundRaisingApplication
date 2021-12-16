package com.code.fundraisingapplication;

import static com.google.android.gms.common.util.CollectionUtils.mapOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class RecyclerViewSpecificItem extends AppCompatActivity {

    TextView title_of_goal;
    TextView description_of_goal;
    TextView category_of_goal;
    TextView targetamount_of_goal;
    TextView status_of_goal;
    Button changegoalstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_specific_item);
        title_of_goal = findViewById(R.id.title_of_goal);
        description_of_goal = findViewById(R.id.description_of_goal);
        category_of_goal = findViewById(R.id.category_of_goal);
        targetamount_of_goal = findViewById(R.id.targetamount_of_goal);
        changegoalstatus = findViewById(R.id.changegoalstatus);
        status_of_goal = findViewById(R.id.status_of_goal);


        Intent intent = getIntent();


        String title_get = null;
        String description_get = null;
        String category_get = null;
        String targetamount_get = null;
        String status_get = null;

        title_get = intent.getStringExtra("Title");
        description_get = intent.getStringExtra("Description");
        category_get = intent.getStringExtra("Category");
        targetamount_get = intent.getStringExtra("TargetAmount");
        status_get = intent.getStringExtra("Status");

        title_of_goal.setText(title_get);
        description_of_goal.setText(description_get);
        category_of_goal.setText(category_get);
        targetamount_of_goal.setText(targetamount_get);
        status_of_goal.setText(status_get);

        changegoalstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("GoalInformation")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String getTitle = (String) document.getData().get("Title");
                                String getCategory = (String) document.getData().get("Category");
                                String getDescription = (String) document.getData().get("Description");
                                String getTargetAmount = (String) document.getData().get("TargetAmount");
                                String getStatus = (String) document.getData().get("Status");

                                if (getTitle.equals(title_of_goal.getText().toString()) &&
                                        getCategory.equals(category_of_goal.getText().toString()) &&
                                        getDescription.equals(description_of_goal.getText().toString()) &&
                                        getTargetAmount.equals(targetamount_of_goal.getText().toString()) &&
                                        getStatus.equals(status_of_goal.getText().toString())
                                ) {
                                    if (getStatus.equals("Active")) {
                                        db.collection("GoalInformation").document(document.getId())
                                                .update("Status", "Not Active");
                                        status_of_goal.setText("Not Active");
                                    } else {
                                        db.collection("GoalInformation").document(document.getId())
                                                .update("Status", "Active");
                                        status_of_goal.setText("Active");
                                    }

                                }
                            }
                        } else {
                            Log.w("TAGXAXA", "Error getting documents.", task.getException());
                        }


                    }
                });



            }
        });
    }}
