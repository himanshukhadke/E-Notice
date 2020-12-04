package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        logout=findViewById(R.id.btn_main_logout);
        logout.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            Intent start_intent=new Intent(MainActivity.this,StartActivity.class);
            startActivity(start_intent);
            finish();
        });
    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Intent start_intent=new Intent(MainActivity.this,StartActivity.class);
            startActivity(start_intent);
            finish();
        }
    }
}