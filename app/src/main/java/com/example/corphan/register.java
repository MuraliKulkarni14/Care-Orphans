package com.example.corphan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText signupEmail, signupPassword,singupCpassword;
    TextView loginRedirectText;
    Button signupButton;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupEmail = findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        singupCpassword=findViewById(R.id.confirmPassword);
        loginRedirectText = findViewById(R.id.sign_in);
        signupButton = findViewById(R.id.sign_up);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                database = FirebaseDatabase.getInstance();
//                reference = database.getReference("users");
//                String email = signupEmail.getText().toString();
//                String password = signupPassword.getText().toString();
//                Toast.makeText(register.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(register.this, login.class);
//                startActivity(intent);
                String email= signupEmail.getText().toString();
                String password= signupPassword.getText().toString();
                String confirmpassword= singupCpassword.getText().toString();

                boolean isValid= validateUser(email, password, confirmpassword);
                if(!isValid)
                    return;

                RegisterToFirebase(email, password);

            }
        });


        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
    }


    void RegisterToFirebase(String email, String password){
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(register.this, "Registration Successful, check email to verify", Toast.LENGTH_LONG).show();
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                }
                else
                    Toast.makeText(register.this,  task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public Boolean validateUser(String email, String password, String confirmpassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signupEmail.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            signupPassword.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmpassword)) {
            singupCpassword.setError("Password doesn't matches confirm password");
            return false;
        }
        return true;
    }
}