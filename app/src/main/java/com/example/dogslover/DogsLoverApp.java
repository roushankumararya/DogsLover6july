package com.example.dogslover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DogsLoverApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_lover_app);

        /*setTitle("dev2qa.com - Add Fragment Dynamically Example");

        // Create and set Android Fragment as default.
        Fragment androidFragment = new AndroidFragment();
        this.setDefaultFragment(androidFragment);

        // Click this button to display android fragment.
       // Button androidButton = (Button)findViewById(R.id.dynamic_fragment_android_button);
      //  androidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment androidFragment = new AndroidFragment();
                replaceFragment(androidFragment);
            }
        });

        // Click this button to display windows fragment.
        Button windowsButton = (Button)findViewById(R.id.dynamic_fragment_windows_button);
        windowsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment windowsFragment = new WindowsFragment();
                replaceFragment(windowsFragment);
            }
        });

        // Click this button to display iOS fragment.
        Button iosButton = (Button)findViewById(R.id.dynamic_fragment_ios_button);
        iosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment iosFragment = new IOSFragment();
                replaceFragment(iosFragment);
            }
        });

    }
*//*
    // This method is used to set the default fragment that will be shown.
    private void setDefaultFragment(Fragment defaultFragment)
    {
        this.replaceFragment(defaultFragment);
    }

    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment)
    {
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.dynamic_fragment_frame_layout, destFragment);

        // Commit the Fragment replace action.
        fragmentTransaction.commit();
    }*/

    }
}