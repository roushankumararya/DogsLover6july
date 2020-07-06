package com.example.dogslover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SelectPage extends AppCompatActivity {


    RadioButton rbuser,rbvetenerydoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);

        rbuser=(RadioButton)findViewById(R.id.userpage);
        rbvetenerydoctor=(RadioButton)findViewById(R.id.vetenerydoctorpage);


        rbuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectPage.this,RegisterActivity.class);
                startActivity(intent);
               // finish();
            }
        });

        rbvetenerydoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectPage.this,VeterinaryDoctor.class);
                startActivity(intent);
              //  finish();
            }
        });

        /*if(rbuser.isChecked()){
            Intent intent=new Intent(SelectPage.this,RegisterActivity.class);
            startActivity(intent);

            // startActivity(new Intent(SelectPage.this,RegisterActivity.class));

        }else if(rbvetenerydoctor.isChecked()){
            Intent intent=new Intent(SelectPage.this,VeterinaryDoctor.class);
            startActivity(intent);
           // startActivity(new Intent(SelectPage.this, VeterinaryDoctor.class));
        }else {

        }*/


    }
}
