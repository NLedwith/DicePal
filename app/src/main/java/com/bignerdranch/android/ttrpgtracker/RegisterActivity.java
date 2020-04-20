package com.bignerdranch.android.ttrpgtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText username;
    private EditText password;
    private EditText confirm_password;
    private static final String TAG = "TTRPGInitiativeTracker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.register_email_textfield);
        password = findViewById(R.id.register_password_textfield);
        confirm_password = findViewById(R.id.register_password_confirm_textfield);

        Button register = findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    public void registerUser(){
        if(username.getText().toString().trim().length() == 0 || password.getText().toString().trim().length() == 0 || confirm_password.getText().toString().trim().length() == 0)
        {
            Toast.makeText(RegisterActivity.this, "Please fill in all fields.",
                    Toast.LENGTH_SHORT).show();
        }
        else if(!password.getText().toString().equals(confirm_password.getText().toString()))
        {
            Toast.makeText(RegisterActivity.this, "Passwords do not match.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            auth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                loggedOn(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void loggedOn(FirebaseUser user){
        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        mainIntent.putExtra("userID", user.getUid());
        startActivityForResult(mainIntent, 1);
    }
}
