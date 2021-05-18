package com.frostdev.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentpage(new Home());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new Home();
                        break;
                }

                switch (item.getItemId()) {
                    case R.id.add:
                        fragment = new AddFragment();
                        break;
                }
                return getFragmentpage(fragment);
            }
        });
    }


    private boolean getFragmentpage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace
                    (R.id.page_container, fragment).commit();
            return true;
        }
        return false;
    }

}