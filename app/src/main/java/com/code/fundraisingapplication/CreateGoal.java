package com.code.fundraisingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateGoal extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "X1212";
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


                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("Title",title.getText().toString());
                user.put("Description",description.getText().toString());
                user.put("TargetAmount",targetamount.getText().toString());
                user.put("Category",category);


// Add a new document with a generated ID
                db.collection("GoalInformation")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });






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

