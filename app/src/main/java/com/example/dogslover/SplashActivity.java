package com.example.dogslover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashActivity extends AppCompatActivity {
    Intent intent;
    String strname,strpassword;
    public static final String MyPrefone = "MyPref";
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs =  getSharedPreferences(MyPrefone, Context.MODE_PRIVATE);
        strname=prefs.getString("usernamesplash","");
        strpassword=prefs.getString("userpasswordsplash","");
        if (!strname.equalsIgnoreCase("") || !strpassword.equalsIgnoreCase("")) {
            intent  = new Intent(this, DogsCatcherDetail.class);
            startActivity(intent);
            finish();

        }else {
            intent  = new Intent(this, DesignLoginRegisterPage.class);
            startActivity(intent);
            finish();
        }

       /* Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 3000);*/
    }
}
