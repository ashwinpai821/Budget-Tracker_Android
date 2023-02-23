package com.example.smartbudgettracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPage extends AppCompatActivity {
    private Button signup_button;
    private FirebaseAuth auth;
    private EditText username , password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        auth = FirebaseAuth.getInstance();
        username=findViewById(R.id.e1);
        password=findViewById(R.id.e2);
        signup_button=findViewById(R.id.signup_button);
       signup_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String user = username.getText().toString().trim();
               String pass= password.getText().toString().trim();


               if(user.isEmpty()){
                   username.setError("Username cannot be empty");
                }
               if(pass.isEmpty()){
                   password.setError("Password cannot be empty");
                }
               else{
                   auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(SignupPage.this,"SIGN-UP SUCCESSFUL",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(SignupPage.this,LoginPage.class));
                           }else{
                               Toast.makeText(SignupPage.this,"SIGN-UP FAILED"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                           }
                       }
                   });
               }
       }});
    }}
