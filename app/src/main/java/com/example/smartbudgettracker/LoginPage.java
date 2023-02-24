package com.example.smartbudgettracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {



    private FirebaseAuth auth;
    private EditText login_username,login_password;
    private Button loginbutton;
    private TextView Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
       auth=FirebaseAuth.getInstance();
       login_username=findViewById(R.id.e1);
       login_password=findViewById(R.id.e2);
       loginbutton=findViewById(R.id.login_button);
       Signup=findViewById(R.id.signup_text);

       loginbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(LoginPage.this,MainActivity.class));

//               String usernamee = login_username.getText().toString();
//               String passwordd=login_password.getText().toString();
//
//               if(!usernamee.isEmpty()){
//                   if(!passwordd.isEmpty()){
//                       auth.signInWithEmailAndPassword(usernamee,passwordd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                           @Override
//                           public void onSuccess(AuthResult authResult) {
//                               Toast.makeText(LoginPage.this,"LOG-IN SUCCESSFUL",Toast.LENGTH_SHORT).show();
//                               startActivity(new Intent(LoginPage.this,MainActivity.class));
//                               finish();
//                           }
//                       }).addOnFailureListener(new OnFailureListener() {
//                           @Override
//                           public void onFailure(@NonNull Exception e) {
//                               Toast.makeText(LoginPage.this,"LOG-IN FAILED",Toast.LENGTH_SHORT).show();
//                           }
//                       });
//                   }else{
//                       login_password.setError("Password cannot be Empty");
//                   }
//
//               } else if (usernamee.isEmpty()) {
//                   login_username.setError("Username cannot be Empty");
//
//               }else {
//                   login_username.setError("Enter valid username");
//               }
//           }
//       });
//
//          Signup.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  startActivity(new Intent(LoginPage.this,SignupPage.class));
//              }
//          });
           }});
}}
