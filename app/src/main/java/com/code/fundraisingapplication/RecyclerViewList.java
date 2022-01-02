package com.code.fundraisingapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class RecyclerViewList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "XA1212";
    RecyclerView rv;
    ArrayList<ImageClass> ls;
    EditText sh;
    Spinner spinner;
    public static String[] paths = {"All","Education","Climate Change","Medical Support"};

    public static String goalname;
    RecyclerViewAdapter adapter;
    RecyclerViewAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);
        rv=findViewById(R.id.rv);
        ls=new ArrayList<>();
        spinner = (Spinner)findViewById(R.id.spinner);
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

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paths));



        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("GoalInformation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String getImage=(String) document.getData().get("Image");
                                String getTitle= (String) document.getData().get("Title");
                                String getCategory=(String) document.getData().get("Category");
                                String getDescription=(String)document.getData().get("Description");
                                String getTargetAmount=(String)document.getData().get("TargetAmount");
                                String getStatus=(String)document.getData().get("Status");

                                StorageReference filePath = FirebaseStorage.getInstance().getReference().child(getImage);
                                filePath.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                                    @Override
                                    public void onSuccess(StorageMetadata storageMetadata) {
                                        // Metadata now contains the metadata for 'images/forest.jpg'

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                    }
                                });


                                            ls.add(new ImageClass(null,getTitle,getDescription,getTargetAmount,getCategory,getStatus));
                                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(RecyclerViewList.this, 2);
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

    private void filter2(String text) {
        ArrayList<ImageClass> filteredList = new ArrayList<>();

        for (ImageClass item : ls) {
            if (item.getGoal_category().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter1 = new RecyclerViewAdapter(ls,RecyclerViewList.this);
        rv.setAdapter(adapter1);
        adapter1.filterList(filteredList);
        adapter1.notifyDataSetChanged();
    }

    private void filter3(String text) {
        ArrayList<ImageClass> filteredList = new ArrayList<>();

        for (ImageClass item : ls) {
                filteredList.add(item);
        }

        adapter1 = new RecyclerViewAdapter(ls,RecyclerViewList.this);
        rv.setAdapter(adapter1);
        adapter1.filterList(filteredList);
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(RecyclerViewList.this,paths[i],Toast.LENGTH_LONG).show();
//                if (i == 0) {
//                    filter2("Education");
//                } else if (i == 1) {
//                    filter2("Climate Change");
//                } else if (i == 3) {
//                    filter2("Medical Support");
//                }
        if (paths[i] != "All") {
            filter2(paths[i]);
        } else {
            filter3(paths[i]);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}