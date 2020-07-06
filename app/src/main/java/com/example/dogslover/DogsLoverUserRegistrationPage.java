package com.example.dogslover;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DogsLoverUserRegistrationPage extends AppCompatActivity {

    EditText nname,naddress,ncity,nstate,nstreetname,nemailid,nphone,npassword;
    Button newbtn;
    String nstrname,nstraddress,nstrcity,nstrstate,nstrstreetname,nstrmailid,nstrphone,nstrpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_lover_user_registration_page);
        nname=(EditText)findViewById(R.id.newusername);
        naddress=(EditText)findViewById(R.id.newaddress);
        ncity=(EditText)findViewById(R.id.newcity);
        nstate=(EditText)findViewById(R.id.newstate);
        nstreetname=(EditText)findViewById(R.id.newstreetname);
        nemailid=(EditText)findViewById(R.id.newemailid);
        nphone=(EditText)findViewById(R.id.newphonenumber);
        npassword=(EditText)findViewById(R.id.newpassword);
        newbtn=(Button)findViewById(R.id.newbtnsubmit);
        validateForm();
    }

    private void validateForm() {
        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nstrname=nname.getText().toString().trim();
                nstraddress=naddress.getText().toString().trim();
                nstrcity=ncity.getText().toString().trim();
                nstrstate=nstate.getText().toString().trim();
                nstrstreetname=nstreetname.getText().toString().trim();
                nstrmailid=nemailid.getText().toString().trim();
                nstrphone=nphone.getText().toString().trim();
                nstrpassword=npassword.getText().toString().trim();
                if(nstrname.isEmpty()){
                    nname.setError("Please enter name");
                    nname.requestFocus();
                }else if(nstraddress.isEmpty()){
                    naddress.setError("Please enter address");
                    naddress.requestFocus();
                }else if(nstrcity.isEmpty()){
                    ncity.setError("Please enter city");
                    ncity.requestFocus();
                }else if(nstrstate.isEmpty()){
                    nstate.setError("Please enter state name");
                    nstate.requestFocus();
                }else if(nstrstreetname.isEmpty()){
                    nstreetname.setError("Please enter street name");
                    nstreetname.requestFocus();
                }else if(nstrmailid.isEmpty()){
                    nemailid.setError("Please enter mail id");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(nstrmailid).matches()){
                    nemailid.setError("Enter email id is not valid");
                    nemailid.requestFocus();
                }else if(nstrphone.isEmpty()){
                    nphone.setError("Please enter phone number");
                    nphone.requestFocus();
                }else if(nstrphone.length()!=10){
                    nphone.setError("Please enter 10 digits phone number");
                    nphone.requestFocus();
                }else if(nstrpassword.isEmpty()){
                    npassword.setError("Please enter password");
                    npassword.requestFocus();
                }else if(nstrpassword.length()!=6){
                    npassword.setError("Please, Entered length of the password must be 6");
                    npassword.requestFocus();
                }else {
                    dogsloveruserloginmeyhod();
                }

            }
        });

    }

    private void dogsloveruserloginmeyhod() {

    }
}
