package com.example.enotice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    public Button reg_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        reg_button=findViewById(R.id.btn_start_login);
        reg_button.setOnClickListener(v->{
            startActivity(new Intent(StartActivity.this, LoginActivity.class));
        });
    }

}