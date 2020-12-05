package com.example.enotice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logout;
    private BottomNavigationView main_bottom_nav;
    private Toolbar main_toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.item_notices:
                    selectedFragment = new NoticeFragment();
                    break;
                case R.id.item_news:
                    selectedFragment = new NewsFragment();
                    break;
                case R.id.item_myprofile:
                    selectedFragment = new ProfileFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);
        getSupportActionBar().setTitle("E-Notice");
        main_bottom_nav = findViewById(R.id.bottom_navigation);
        main_bottom_nav.setOnNavigationItemSelectedListener(navListner);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new NoticeFragment()).commit();// By default it shows Notice fragment
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendToStart();
        }
    }

    private void sendToStart() {
        Intent start_intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(start_intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_logout:
                FirebaseAuth.getInstance().signOut();
                sendToStart();
                break;
            case R.id.item_settings:
                break;
        }
        return true;
    }
}