package com.android.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText edt_email, edt_pass;
    MaterialButton btnSignIn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_pass = findViewById(R.id.edt_password1);
        edt_email = findViewById(R.id.edt_email1);
        btnSignIn = findViewById(R.id.btn_sign_in);
        mAuth = FirebaseAuth.getInstance();


        btnSignIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login(edt_email.getText().toString(), edt_pass.getText().toString());
                    }
                }
        );

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void login(String email,String pass){
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Login.this, "Login Successfully" , Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Login.this, "Login Fail" , Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
    }
}