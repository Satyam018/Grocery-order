package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity2 extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottommavigation);
        frameLayout = (FrameLayout) findViewById(R.id.frame1layout1);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame1layout1,new HomeFragemnt()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.item1:
                        fragment=new HomeFragemnt();
                        break;
                    case R.id.item2:
                        fragment=new ProfileFragment();
                        break;
                    case R.id.item3:
                        fragment=new CategoryFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame1layout1,fragment).commit();


                return true;
            }
        });
        
    }
}