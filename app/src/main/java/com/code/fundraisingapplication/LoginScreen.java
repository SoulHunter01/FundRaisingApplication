package com.code.fundraisingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText username;
    EditText password;
    Button loginbutton;
    TextView registrationintent;
    TextView forgotpassword;
    Button fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        forgotpassword=findViewById(R.id.forgotpassword);
        mAuth = FirebaseAuth.getInstance();
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginbutton=findViewById(R.id.loginbutton);
        registrationintent=findViewById(R.id.registrationintent);
        registrationintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginScreen.this,SignUpScreen.class);
                startActivity(intent);
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signInWithEmailAndPassword(String.valueOf(username),String.valueOf(password)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent=new Intent(LoginScreen.this,MainScreen.class);
                        Toast.makeText(LoginScreen.this,"Current User  Is Logged In",Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginScreen.this,"Wrong Email or Password",Toast.LENGTH_LONG).show();
                    }
                });



            }
        });


        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginScreen.this,ForgotPassword.class);
                startActivity(intent);
            }
        });



    }
}