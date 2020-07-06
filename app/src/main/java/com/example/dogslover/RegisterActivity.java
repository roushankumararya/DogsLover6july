package com.example.dogslover;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    String URL="http://project26.aspdotnetstorefront.shoppingmegamart.com/StreetDogLovers/api/account/Register";

    private RelativeLayout rlayout;
    private Animation animation;
    CardView cardView;
    EditText rcname,rcaddress,rccity,rcstate,rcphone,rcstreetcare,rcemail,rcpassword,rcroll;
    String strrcname,strrcaddress,strrccity,strrcstate,strrcphone,strrcstreecare,strrcmail,strrcpassword,strrcroll;
    Button rcbtnsubmituser,rcsubmitdoctor,btbpasswordsh;
    CheckBox checkbox,checkBoxvd;

    String URLDoctor="http://project26.aspdotnetstorefront.shoppingmegamart.com/StreetDogLovers/api/account/Register";
    EditText vdname,vdaddress,vdcity,vdstate,vdphone,vdqualification,vdconsultationfee,vddogsconsultationfee,vdemail,vdpassword,vdroll;
    String strvdname,strvdaddress,strvdcity,strvdstate,strvdphone,strvdqualification,
            strvdconsultationfee,strvddogsfee,strvdemail,strvdpassword,strvdroll;
    Button btnveterinarydoctor;

    public static final String Sp_Status = "Status";
    public static final String MyPref = "MyPref";
    SharedPreferences sharedPreferences;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        final LinearLayout view1=(LinearLayout)findViewById(R.id.userprofile);
        final LinearLayout view2=(LinearLayout)findViewById(R.id.doctorprofile);

        checkbox=(CheckBox) findViewById(R.id.clickcheckbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    rcpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    rcpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        checkBoxvd=(CheckBox)findViewById(R.id.clickcheckboxvd);
        checkBoxvd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    vdpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    vdpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.rdiopr);
        final RadioButton rbone = (RadioButton) findViewById(R.id.userpagepr);
        final RadioButton rbtwo = (RadioButton) findViewById(R.id.vetenerydoctorpagepr);

        rcbtnsubmituser=(Button)findViewById(R.id.btnrcsubmitregistrationpr);
        rcsubmitdoctor=(Button)findViewById(R.id.btnrcsubmitregistrationdoctorpr);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.userpagepr:
                        view1.setVisibility(View.VISIBLE);
                        view2.setVisibility(View.GONE);
                        rcbtnsubmituser.setVisibility(View.VISIBLE);
                        rcsubmitdoctor.setVisibility(View.GONE);
                        // do operations specific to this selection
                        break;
                    case R.id.vetenerydoctorpagepr:

                        view2.setVisibility(View.VISIBLE);
                        view1.setVisibility(View.GONE);
                        rcsubmitdoctor.setVisibility(View.VISIBLE);
                        rcbtnsubmituser.setVisibility(View.GONE);
                        // do operations specific to this selection
                        break;
                       /* default:
                            view1.setVisibility(View.VISIBLE);
                            view2.setVisibility(View.GONE);
                            rcbtnsubmituser.setVisibility(View.VISIBLE);
                            rcsubmitdoctor.setVisibility(View.GONE);
                            break;*/
                }
               /* if (rbone.isChecked() == true) {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                    rcbtnsubmituser.setVisibility(View.VISIBLE);
                    rcsubmitdoctor.setVisibility(View.GONE);
                }else {
                    view2.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);
                    rcsubmitdoctor.setVisibility(View.VISIBLE);
                    rcbtnsubmituser.setVisibility(View.GONE);
                }*/

            }
        });




        vdname=(EditText)findViewById(R.id.txtrcnamedoctorpr);
        vdaddress=(EditText)findViewById(R.id.txtrcaddressdoctorpr);
        vdcity=(EditText)findViewById(R.id.txtrccitydoctorpr);
        vdstate=(EditText)findViewById(R.id.txtrcstatedoctorpr);
        vdphone=(EditText)findViewById(R.id.txtrcphonedoctorpr);
        vdqualification=(EditText)findViewById(R.id.txtQualificationdoctorpr);
        vdconsultationfee=(EditText)findViewById(R.id.txtConsultationFeedoctorpr);
        vddogsconsultationfee=(EditText)findViewById(R.id.txtConsultationFee_ForStreetDogsdoctorpr);
        vdemail=(EditText)findViewById(R.id.txtrcmailiddoctorpr);
        vdpassword=(EditText)findViewById(R.id.txtrcpassworddoctorpr);
        btnveterinarydoctor=(Button)findViewById(R.id.btnrcsubmitregistrationdoctorpr);

        rcname=(EditText)findViewById(R.id.txtrcname);
        rcaddress=(EditText)findViewById(R.id.txtrcaddress);
        rccity=(EditText)findViewById(R.id.txtrccity);
        rcstate=(EditText)findViewById(R.id.txtrcstate);
        rcphone=(EditText)findViewById(R.id.txtrcphone);
        rcstreetcare=(EditText)findViewById(R.id.txtrcstreetcare);
        rcemail=(EditText)findViewById(R.id.txtrcmailid);
        rcpassword=(EditText)findViewById(R.id.txtrcpassword);
       // rcroll=(EditText)findViewById(R.id.txtrcroll);

        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        clicklistneruser();
        clicklistnerdoctor();

       /* if (sharedPreferences.getString(RegisterActivity.Sp_Status, "").matches("LoggedIn")) {
            startActivity(new Intent(RegisterActivity.this, DogsCatcherDetail.class));
        }*/

    }

    private void clicklistnerdoctor() {

        btnveterinarydoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strvdname=vdname.getText().toString();
                strvdaddress=vdaddress.getText().toString();
                strvdcity=vdcity.getText().toString();
                strvdstate=vdstate.getText().toString();
                strvdphone=vdphone.getText().toString();
                strvdqualification=vdqualification.getText().toString();
                strvdconsultationfee=vdconsultationfee.getText().toString();
                strvddogsfee=vddogsconsultationfee.getText().toString();
                strvdemail=vdemail.getText().toString();
                strvdpassword=vdpassword.getText().toString();
              //  strvdroll=vdroll.getText().toString();
                if(strvdname.isEmpty()){
                   // vdname.setError("Please enter name");
                    vdname.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter name ",Toast.LENGTH_SHORT).show();
                }else  if (strvdaddress.isEmpty()){
                   // vdaddress.setError("Please enter address");
                    vdaddress.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter address ",Toast.LENGTH_SHORT).show();

                }else if(strvdcity.isEmpty()){
                   // vdcity.setError("Please enter city");
                    vdcity.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter city ",Toast.LENGTH_SHORT).show();

                }else if(strvdstate.isEmpty()){
                   // vdstate.setError("Please enter state name");
                    vdstate.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter state name ",Toast.LENGTH_SHORT).show();
                }else if(strvdphone.isEmpty()){
                  //  vdphone.setError("Please enter phone number");
                    vdphone.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter phone number ",Toast.LENGTH_SHORT).show();
                }else if(strvdqualification.isEmpty()){
                 //   vdqualification.setError("Please enter qualification");
                    vdqualification.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter qualification ",Toast.LENGTH_SHORT).show();
                }else if(strvdconsultationfee.isEmpty()){
                  //  vdconsultationfee.setError("Please enter consultancy fee");
                    vdconsultationfee.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter consultancy fee ",Toast.LENGTH_SHORT).show();
                }else if(strvddogsfee.isEmpty()){
                   // vddogsconsultationfee.setError("Please enter dogs consultancy fee");
                    vddogsconsultationfee.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter dogs consultancy fee ",Toast.LENGTH_SHORT).show();
                }else if(strvdemail.isEmpty()){
                 //   vdemail.setError("Please enter mail id");
                    vdemail.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter mail id ",Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(strvdemail).matches()){
                   // vdemail.setError("Please enter correct mail id");
                    vdemail.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter correct mail id ",Toast.LENGTH_SHORT).show();
                }else if(strvdpassword.isEmpty()){
                   // vdpassword.setError("Please enter password");
                    vdpassword.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter password ",Toast.LENGTH_SHORT).show();
                }else if(!PASSWORD_PATTERN.matcher(strvdpassword).matches()){
                    vdpassword.requestFocus();
                    Toast.makeText(getApplicationContext(),"Password must be at least one upper case,lower case,Specila symbol,one digits without any space",Toast.LENGTH_SHORT).show();
                }else if(strvdpassword.length()<6){
                    vdpassword.requestFocus();
                    Toast.makeText(getApplicationContext(),"Password length must be 6",Toast.LENGTH_SHORT).show();
                }
                else {
                    VeterinaryDoctorRegistration();
                }
            }

        });
    }

    private void VeterinaryDoctorRegistration() {
        final ProgressDialog progressDialog=new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        try {

            RequestQueue requestQueue= Volley.newRequestQueue(this);
            final JSONObject jsonObject=new JSONObject();
            jsonObject.put("Qualification",strvdqualification);
            jsonObject.put("ConsultationFee",strvdconsultationfee);
            jsonObject.put("ConsultationFee_ForStreetDogs",strvddogsfee);
            jsonObject.put("Role","1");
            jsonObject.put("Email",strvdemail);
            jsonObject.put("PhoneNumber",strvdphone);
            jsonObject.put("Password",strvdpassword);
            jsonObject.put("Address",strvdaddress);
            jsonObject.put("City",strvdcity);
            jsonObject.put("State",strvdstate);
            jsonObject.put("Name",strvdname);
            final String requestbody=jsonObject.toString();

            StringRequest stringRequest=new StringRequest(Request.Method.POST, URLDoctor, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    VeterinarysuccessPojo veterinarysuccessPojo=(VeterinarysuccessPojo)new Gson().fromJson(response,VeterinarysuccessPojo.class);
                    if(veterinarysuccessPojo!=null){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("doctorroll",veterinarysuccessPojo.getRoles().toString());
                        editor.commit();
                        editor.apply();
                    }

                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Log.e("Volley", response);
                    progressDialog.dismiss();
                    vdname.setText(" ");
                    vdaddress.setText(" ");
                    vdcity.setText(" ");
                    vdstate.setText(" ");
                    vdphone.setText(" ");
                    vdqualification.setText(" ");
                    vdconsultationfee.setText(" ");
                    vddogsconsultationfee.setText(" ");
                    vdemail.setText(" ");
                    vdpassword.setText("");
                    // vdroll.setText(" ");

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    StringBuilder builder=new StringBuilder();
                    try {

                        Gson gson=new Gson();
                        String responseString=new String(error.networkResponse.data,HttpHeaderParser.parseCharset(error.networkResponse.headers));
                        VeterinaryPojo veterinarydoctoreresponse=(VeterinaryPojo) gson.fromJson(responseString,VeterinaryPojo.class);
                        String[] vtd=veterinarydoctoreresponse.getModelState().getData();
                        for (int i=0;i<vtd.length;i++){
                            System.out.println("Array data" + vtd[i]);
                            builder.append(" "+vtd[i]+" ");
                        }
                        Toast.makeText(getApplicationContext(),builder.toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Volley", error.getLocalizedMessage()+"\n"+error.getCause()+"\n"+error.getStackTrace()+"\ndataa"+responseString);
                        progressDialog.dismiss();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }){

                @Override
                public String getBodyContentType(){
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestbody==null ? null:requestbody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        //  e.printStackTrace();
                        VolleyLog.wtf("Unsupporting encoding while trying to get the bytes of %s using %s",requestbody,"utf-8");
                        return null;
                    }
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                protected Response<String>parseNetworkResponse(NetworkResponse networkResponse){
                    String responseString="";
                    if(networkResponse!=null){
                        try {
                            responseString=new String(networkResponse.data,HttpHeaderParser.parseCharset(networkResponse.headers));

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    return Response.success(responseString,HttpHeaderParser.parseCacheHeaders(networkResponse));
                }

            };
            requestQueue.add(stringRequest);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clicklistneruser() {


        rcbtnsubmituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strrcname=rcname.getText().toString();
                strrcaddress=rcaddress.getText().toString();
                strrccity=rccity.getText().toString();
                strrcstate=rcstate.getText().toString();
                strrcphone=rcphone.getText().toString();
                strrcstreecare=rcstreetcare.getText().toString();
                strrcmail=rcemail.getText().toString();
                strrcpassword=rcpassword.getText().toString();
              //  strrcroll=rcroll.getText().toString();
                if(strrcname.isEmpty()){
                  //  rcname.setError("Please enter name");
                    rcname.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter name ",Toast.LENGTH_SHORT).show();
                }else if(strrcaddress.isEmpty()){
                  //  rcaddress.setError("Please enter address");
                    rcaddress.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter address ",Toast.LENGTH_SHORT).show();
                }else if(strrccity.isEmpty()){
                  //  rccity.setError("Please enter city");
                    rccity.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter city ",Toast.LENGTH_SHORT).show();
                }else if(strrcstate.isEmpty()){
                  //  rcstate.setError("Please enter state");
                    rcstate.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter state ",Toast.LENGTH_SHORT).show();
                }else if(strrcphone.isEmpty()){
                //    rcphone.setError("Please enter phone number");
                    rcphone.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter phone number ",Toast.LENGTH_SHORT).show();
                }else if(strrcstreecare.isEmpty()){
                  //  rcstreetcare.setError("Please enter street cate name");
                    rcstreetcare.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter street cate name ",Toast.LENGTH_SHORT).show();
                }else if(strrcmail.isEmpty()){
                  //  rcemail.setError("Please enter mail id");
                    rcemail.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter mail id ",Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(strrcmail).matches()){
                   // rcemail.setError("Please enter mail id");
                    rcemail.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter mail id ",Toast.LENGTH_SHORT).show();
                }else if(strrcpassword.isEmpty()){
                   // rcpassword.setError("Please enter password");
                    rcpassword.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter password ",Toast.LENGTH_SHORT).show();
                }else if (!PASSWORD_PATTERN.matcher(strrcpassword).matches()){
                    rcpassword.requestFocus();
                    Toast.makeText(getApplicationContext(),"Password must be at least one upper case,lower case,Specila symbol,one digits without any space",Toast.LENGTH_SHORT).show();
                }else if(strrcpassword.length()<6){
                    rcpassword.requestFocus();
                    Toast.makeText(getApplicationContext(),"Password length must be 6",Toast.LENGTH_SHORT).show();
                }

                else {
                    registration();
                }
            }
        });


    }

    private void registration() {

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("Email", strrcmail);
            jsonObject.put("Password", strrcpassword);
            jsonObject.put("Name", strrcname);
            jsonObject.put("Address", strrcaddress);
            jsonObject.put("City", strrccity);
            jsonObject.put("State", strrcstate);
            jsonObject.put("Street_you_takecareof", strrcstreecare);
            jsonObject.put("Role", "0");
            jsonObject.put("PhoneNumber", strrcphone);
            final String requestbody = jsonObject.toString();
           // Toast.makeText(RegisterActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    RegistersuccessPojo registersuccessPojo=(RegistersuccessPojo)new Gson().fromJson(response,RegistersuccessPojo.class);
                    if(registersuccessPojo!=null){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userroll",registersuccessPojo.getRoles().toString());
                        editor.commit();
                        editor.apply();
                    }
                  //  sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("shname", strrcname);
                    editor.putString("shaddress", strrcaddress);
                    editor.putString("shcity", strrccity);
                    editor.putString("shatate",strrcstate);
                    editor.putString("shphone",strrcphone);
                    editor.putString("shstreetcare",strrcstreecare);
                    editor.putString("shemail",strrcmail);
                    editor.putString("shpassword",strrcpassword);
                  //  editor.putString("shroll","0");
                    editor.commit();
                    editor.apply();
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Log.e("Volley", response);
                    progressDialog.dismiss();
                    rcname.setText(" ");
                    rcaddress.setText(" ");
                    rccity.setText(" ");
                    rcstate.setText(" ");
                    rcphone.setText(" ");
                    rcstreetcare.setText(" ");
                    rcemail.setText(" ");
                    rcpassword.setText("");
                //    rcroll.setText(" ");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    StringBuilder builder = new StringBuilder();
                    try {
                        Gson gson=new Gson();
                        String responseString = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                        MyPojo myPojo= (MyPojo) gson.fromJson(responseString,MyPojo.class);
                        String[] aaa=myPojo.getModelState().getData();
                        for(int i=0;i<aaa.length;i++){
                            System.out.println("array data " + aaa[i]);
                             builder.append(""+aaa[i]+"");
                        }
                        Toast.makeText(getApplicationContext(), builder.toString() , Toast.LENGTH_SHORT).show();
                        Log.e("Volley", error.getLocalizedMessage()+"\n"+error.getCause()+"\n"+error.getStackTrace()+"\ndataa"+responseString);
                        progressDialog.dismiss();

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestbody == null ? null : requestbody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException usee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestbody, "utf-8");
                        return null;
                    }
                }
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        try {
                            responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                          //  startActivities(new Intent(RegisterActivity.this, DesignLoginRegisterPage.class));
                          //  finish();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
