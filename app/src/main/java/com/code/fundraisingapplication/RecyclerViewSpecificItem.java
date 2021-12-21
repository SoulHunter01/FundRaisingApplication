package com.code.fundraisingapplication;

import static com.google.android.gms.common.util.CollectionUtils.mapOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewSpecificItem extends AppCompatActivity {

    TextView title_of_goal;
    TextView description_of_goal;
    TextView category_of_goal;
    TextView targetamount_of_goal;
    TextView status_of_goal;
    Button changegoalstatus;
    Button applypayment;
    Button generate_QR;
    final String[] Status_Value = {null};

    String title_get = null;
    public static String qr_title;
    String description_get = null;
    String category_get = null;
    String targetamount_get = null;
    String status_get = null;
    String CHANNEL_ID="Channel 1";

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
        applypayment = findViewById(R.id.applypayment);
        generate_QR = findViewById(R.id.generate_QR);

        Intent intent = getIntent();



        title_get = intent.getStringExtra("Title");
        qr_title = title_get;
        description_get = intent.getStringExtra("Description");
        category_get = intent.getStringExtra("Category");
        targetamount_get = intent.getStringExtra("TargetAmount");
        status_get = intent.getStringExtra("Status");

        title_of_goal.setText(title_get);
        description_of_goal.setText(description_get);
        category_of_goal.setText(category_get);
        targetamount_of_goal.setText(targetamount_get);
        status_of_goal.setText(status_get);

        generate_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecyclerViewSpecificItem.this,generate_QR_Code.class);
                startActivity(intent);
            }
        });


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
                                        Status_Value[0] = "Active";


                                    } else {
                                        db.collection("GoalInformation").document(document.getId())
                                                .update("Status", "Active");
                                        status_of_goal.setText("Active");
                                        Status_Value[0] = "Not Active";

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


        applypayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status_of_goal.getText().toString().startsWith("A")) {
                    Intent intent1 = new Intent(RecyclerViewSpecificItem.this, PaymentScreen.class);
                    intent1.putExtra("Title", title_of_goal.getText().toString());
                    intent1.putExtra("Target", targetamount_of_goal.getText().toString());
                    startActivityForResult(intent1,1);
                } else {
                    Toast.makeText(RecyclerViewSpecificItem.this, "Goal Not Active", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        title_of_goal = findViewById(R.id.title_of_goal);
        description_of_goal = findViewById(R.id.description_of_goal);
        category_of_goal = findViewById(R.id.category_of_goal);
        targetamount_of_goal = findViewById(R.id.targetamount_of_goal);
        changegoalstatus = findViewById(R.id.changegoalstatus);
        status_of_goal = findViewById(R.id.status_of_goal);
        applypayment = findViewById(R.id.applypayment);


        title_of_goal.setText(title_get);
        description_of_goal.setText(description_get);
        category_of_goal.setText(category_get);
        status_of_goal.setText(status_get);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("GoalInformation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String getTitle = (String) document.getData().get("Title");
                                if (getTitle.equals(title_of_goal.getText().toString())) {
                                    String targetAmount = (String) document.getData().get("TargetAmount");
                                    targetamount_of_goal.setText(targetAmount);

                                    Intent newintent=new Intent(getApplicationContext(),RecyclerViewSpecificItem.class);
                                    PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,newintent,PendingIntent.FLAG_UPDATE_CURRENT);

                                    NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                                            .setContentIntent(pendingIntent)
                                            .setContentTitle(title_of_goal.getText().toString())
                                            .setContentText("Target Of "+title_of_goal.getText().toString()+" Has Been Updated To "+targetamount_of_goal.getText().toString())
                                            .setSmallIcon(R.drawable.notification_icon);

                                    NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"This Is My First Notification",NotificationManager.IMPORTANCE_HIGH);
                                    notificationManager.createNotificationChannel(notificationChannel);
                                    notificationManager.notify(0,builder.build());




                                }


                            }
                        } else {
                            Log.w("TAGXAXAXAXA", "Error getting documents.", task.getException());
                        }
                    }
                });

    }



}

