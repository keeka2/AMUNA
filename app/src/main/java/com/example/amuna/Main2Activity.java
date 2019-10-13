package com.example.amuna;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.List;

import static com.example.amuna.LoginSplash.Email;
import static com.example.amuna.LoginSplash.userList;


public class Main2Activity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private ChatFragment fragmentChat = new ChatFragment();
    private FavFragment fragmentFav = new FavFragment();
    private AccFragment fragmentAcc = new AccFragment();


    private ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());





    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.navigation_account:
                    transaction.replace(R.id.frameLayout,fragmentAcc).commitAllowingStateLoss();
                    break;

                case R.id.navigation_home:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.navigation_chat:
                    transaction.replace(R.id.frameLayout, fragmentChat).commitAllowingStateLoss();
                    break;
                case R.id.navigation_favorites:
                    transaction.replace(R.id.frameLayout, fragmentFav).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}