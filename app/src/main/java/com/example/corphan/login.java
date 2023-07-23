package com.example.corphan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class    login extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;

    TextView signupRedirectText;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.email);
        loginPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.sign_in);
        signupRedirectText = findViewById(R.id.sign_up);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= loginEmail.getText().toString();
                String password= loginPassword.getText().toString();
                boolean isValid= validateUser(email, password);
                if(!isValid) {
                    return;
                }
                loginToFirebase(email, password);
            }
        });


        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }


    void loginToFirebase(String email, String password){
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(login.this, userData.class));
                        finish();
                    }
                    else{
                        Toast.makeText(login.this, "First verify your email", Toast.LENGTH_LONG).show();
                    }

                }
                else
                    Toast.makeText(login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    boolean validateUser(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            loginPassword.setError("Password length is invalid");
            return false;
        }
        return true;
    }
}