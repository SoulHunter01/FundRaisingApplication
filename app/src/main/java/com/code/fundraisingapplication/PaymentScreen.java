package com.code.fundraisingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class PaymentScreen extends AppCompatActivity {

    private static final String TAG = "XAXAXAXAXA";
    TextView title_of_goal;
    TextView target_of_goal;
    EditText YourContribution;
    Button paynow;

    private void animatelogo(){

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(10000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        ImageView image= (ImageView) findViewById(R.id.imageView);
        image.startAnimation(rotate);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen2);
        Intent intent=getIntent();
        animatelogo();
        String title=intent.getStringExtra("Title");
        String target=intent.getStringExtra("Target");
        title_of_goal=findViewById(R.id.title_of_goal);
        title_of_goal.setText(title);
        target_of_goal=findViewById(R.id.targetamount_of_goal);
        target_of_goal.setText(target);
        YourContribution=findViewById(R.id.YourContribution);
        paynow=findViewById(R.id.paynow);


        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PaymentScreen.this);
                String username = preferences.getString("Username", "");

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("GoalInformation")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String getTitle= (String) document.getData().get("Title");

                                        if(getTitle.equals(title) ) {
                                            int target_int = Integer.parseInt(target_of_goal.getText().toString());
                                            int contribution = Integer.parseInt(YourContribution.getText().toString());
                                            int amount = target_int - contribution;
                                            db.collection("GoalInformation").document(document.getId())
                                                    .update("TargetAmount", String.valueOf(amount));
                                            target_of_goal.setText(String.valueOf(amount));


                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            // Create a new user with a first and last name
                                            Map<String, Object> user = new HashMap<>();
                                            user.put("Title", title_of_goal.getText().toString());
                                            user.put("Contribution", YourContribution.getText().toString());
                                            user.put("Username", username);


// Add a new document with a generated ID
                                            db.collection("UserContribution")
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



                                            Intent intent1=new Intent(PaymentScreen.this,RecyclerViewSpecificItem.class);
                                            setResult(RESULT_OK,intent1);
                                            finish();

                                        }


                                    }
                                } else {
                                    Log.w("TAGXAXAXAXA", "Error getting documents.", task.getException());
                                }
                            }
                        });
            }
        });




    }
}