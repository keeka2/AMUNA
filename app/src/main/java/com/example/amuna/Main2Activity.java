package com.example.amuna;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;

import android.widget.TextView;


public class Main2Activity extends AppCompatActivity implements OnFragmentInteractionListener {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    switchToHomeFragment();
                    return true;
                case R.id.navigation_favorites:
                    mTextMessage.setText("Favorites");
                    switchToFavFragment();
                    return true;
                case R.id.navigation_chat:
                    mTextMessage.setText("Chatting");
                    switchToChatFragment();
                    return true;
                case R.id.navigation_account:
                    mTextMessage.setText("Account");
                    switchToAccFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void switchToHomeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.navigation_home, new HomeFragment()).commit();
    }
    public void switchToFavFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.navigation_favorites, new FavFragment()).commit();
    }
    public void switchToChatFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.navigation_chat, new ChatFragment()).commit();
    }
    public void switchToAccFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.navigation_account, new AccFragment()).commit();
    }
}
