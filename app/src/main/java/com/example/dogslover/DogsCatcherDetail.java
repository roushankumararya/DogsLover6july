package com.example.dogslover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DogsCatcherDetail extends AppCompatActivity {
    EditText dcname,dcaddress,dccity,dcstate,dcphone,dcstreetcare,dcemail,dcpassword,dcroll;
    String shgetname,shgetaddress,shgetcity,shgetstate,shgetphone,shgetstreetcate,shgetmail,shgetpassword,shgetroll;
    public static final String MyPref="MyPref";
    SharedPreferences sharedPreferences;
    Button btndogspage;
    Button btndogs,btnvetnarydoctor,btndogslist,btnlistuserinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_catcher_detail);
        btndogspage=(Button)findViewById(R.id.dogsform);
        btnvetnarydoctor=(Button)findViewById(R.id.vetnarydoctor);
        btndogslist=(Button)findViewById(R.id.dogslist);
        btnlistuserinfo=(Button)findViewById(R.id.userinfo);

        final Fragment first = new DogsRegistrationForm();
        final Fragment second = new VetanaryDoctorsForm();
        final Fragment third = new DogsLIist();
        final Fragment fourth=new UserInfoClass();
        findViewById(R.id.dogsform).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.layout, first);
                fragmentTransaction.commit();
            }
        });
        findViewById(R.id.vetnarydoctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.layout, second);
                fragmentTransaction.commit();
            }
        });

        findViewById(R.id.dogslist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.layout, third);
                fragmentTransaction.commit();
            }
        });

        findViewById(R.id.userinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.layout, fourth);
                fragmentTransaction.commit();
            }
        });
       /* dcname=(EditText)findViewById(R.id.dceditname);
        dcaddress=(EditText)findViewById(R.id.dceditaddress);
        dccity=(EditText)findViewById(R.id.dceditcity);
        dcstate=(EditText)findViewById(R.id.dceditstate);
        dcphone=(EditText)findViewById(R.id.dceditphone);
        dcstreetcare=(EditText)findViewById(R.id.dceditstreetcare);
        dcemail=(EditText)findViewById(R.id.dceditmailid);
        dcpassword=(EditText)findViewById(R.id.dceditpassword);
        dcroll=(EditText)findViewById(R.id.dceditroll);
        btndogspage=(Button)findViewById(R.id.dcbtndogsregistratiopage);
        SharedPreferences sharedPreferences = getSharedPreferences(MyPref,Context.MODE_PRIVATE);

        shgetname=sharedPreferences.getString("shname","");
        shgetaddress=sharedPreferences.getString("shaddress","");
        shgetcity=sharedPreferences.getString("shcity","");
        shgetstate=sharedPreferences.getString("shatate","");
        shgetphone=sharedPreferences.getString("shphone","");
        shgetstreetcate=sharedPreferences.getString("shstreetcare","");
        shgetmail=sharedPreferences.getString("shemail","");
        shgetpassword=sharedPreferences.getString("shpassword","");
        shgetroll=sharedPreferences.getString("shroll","");
        dcname.setText(shgetname);
        dcaddress.setText(shgetaddress);
        dccity.setText(shgetcity);
        dcstate.setText(shgetstate);
        dcphone.setText(shgetphone);
        dcstreetcare.setText(shgetstreetcate);
        dcemail.setText(shgetmail);
        dcpassword.setText(shgetpassword);
        dcroll.setText(shgetroll);

       btndogspage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),DogsRegistrationPage.class));
               finish();

           }
       });*/
    }


}
