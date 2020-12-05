package com.example.enotice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText id;
    private EditText pass;
    private Button login_btn;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        id = findViewById(R.id.etxt_login_collegeID);
        pass = findViewById(R.id.etxt_login_password);
        mLoginProgress = new ProgressDialog(this);
        login_btn = findViewById(R.id.btn_login_login);
        login_btn.setOnClickListener(v -> {
            String college_id = id.getText().toString();
            String password = pass.getText().toString();
            if (college_id.equals("") || password.equals("")) {
                Toast.makeText(LoginActivity.this, "Please enter login details...", Toast.LENGTH_SHORT).show();
            } else {
                mLoginProgress.setTitle("Logging in...");
                mLoginProgress.setMessage("Please wait while we check your credentials");
                mLoginProgress.setCanceledOnTouchOutside(false);
                mLoginProgress.show();
                startLoginProcess(college_id, password);

            }
        });
    }

    private void startLoginProcess(String college_id, String password) {
        mAuth.signInWithEmailAndPassword(college_id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mLoginProgress.dismiss();
                            Intent newintent = new Intent(LoginActivity.this, MainActivity.class);
                            newintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //user  should not go back to startpage on pressing backspace
                            finish();
                        } else {
                            mLoginProgress.hide();
                            Toast.makeText(LoginActivity.this, "Incorrect Login Details...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}