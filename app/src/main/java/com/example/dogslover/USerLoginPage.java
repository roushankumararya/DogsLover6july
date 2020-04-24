package com.example.dogslover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class USerLoginPage extends AppCompatActivity {

    EditText etname,etpass;
    Button btnlogin;
    String lgnname,lgnpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_page);
       /* etname=(EditText)findViewById(R.id.etloginname);
        etpass=(EditText)findViewById(R.id.etloginpass);
        btnlogin=(Button)findViewById(R.id.loginbtnuserlogin);*/

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lgnname=etname.getText().toString();
                lgnpass=etpass.getText().toString();
                if(lgnname.isEmpty()){
                    etname.setError("Please enter name");
                    etname.requestFocus();
                }else if(lgnpass.isEmpty()){
                    etpass.setError("Please Enter password");
                }else {
                    startActivity(new Intent(USerLoginPage.this,RegistrationDogPAge.class));
                    finish();
                }
            }
        });


    }
}
