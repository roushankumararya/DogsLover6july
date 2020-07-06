package com.example.dogslover;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserInfoClass extends Fragment {
    EditText dcname,dcaddress,dccity,dcstate,dcphone,dcstreetcare,dcemail,dcpassword,dcroll;
    String shgetname,shgetaddress,shgetcity,shgetstate,shgetphone,shgetstreetcate,shgetmail,shgetpassword,shgetroll;
    public static final String MyPref="MyPref";
    SharedPreferences sharedPreferences;
    Button btndogspage;
    Button btndogs,btnvetnarydoctor,btndogslist,btnlistuserinfo;

    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userinfofragment, container, false);
       /* textView = view.findViewById(R.id.textfragment);
        textView.setText("Third");*/
          dcname=(EditText)view.findViewById(R.id.dceditname);
        dcaddress=(EditText)view.findViewById(R.id.dceditaddress);
        dccity=(EditText)view.findViewById(R.id.dceditcity);
        dcstate=(EditText)view.findViewById(R.id.dceditstate);
        dcphone=(EditText)view.findViewById(R.id.dceditphone);
        dcstreetcare=(EditText)view.findViewById(R.id.dceditstreetcare);
        dcemail=(EditText)view.findViewById(R.id.dceditmailid);
        dcpassword=(EditText)view.findViewById(R.id.dceditpassword);
        dcroll=(EditText)view.findViewById(R.id.dceditroll);
       // btndogspage=(Button)view.findViewById(R.id.dcbtndogsregistratiopage);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MyPref, Context.MODE_PRIVATE);

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

      /* btndogspage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),DogsRegistrationPage.class));
               finish();

           }
       });*/
        return view;
    }
}
