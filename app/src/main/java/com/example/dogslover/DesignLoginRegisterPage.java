package com.example.dogslover;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DesignLoginRegisterPage extends AppCompatActivity implements View.OnClickListener {

    String strrcname, strrcaddress, strrccity, strrcstate, strrcphone, strrcstreecare, strrcmail, strrcpassword, strrcroll;

    private ImageButton btRegister;
    private TextView tvLogin;
    CardView cardView;
    Toolbar toolbar;
    HttpHeaderParser headerParser;
    EditText txtusername, txtuserpassword;
    EditText sdname, sdaddress, sdcity, sdstate, sdphone, sdstreetcare, sdmail, sdpassword, sdroll;
    Button btnsubmit;
    String strusername, strpassword;
    String strrolldoctor, struserroll;
    String URL = "http://project26.aspdotnetstorefront.shoppingmegamart.com/StreetDogLovers/token";

    CheckBox checkBox;
    public static final String MyPrefone = "MyPref";
    public static final String Myveterinary = "Myroll";
    public static final String MyPref = "MyPref";
    SharedPreferences sharedPreferences,sharedPreferencesdoctor,sharedPreferencesuser;

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
        setContentView(R.layout.activity_design_login_register_page);
        btRegister = findViewById(R.id.btRegister);
        tvLogin = findViewById(R.id.tvLogin);
        btRegister.setOnClickListener(this);
        txtusername = (EditText) findViewById(R.id.loginusername);
        txtuserpassword = (EditText) findViewById(R.id.loginpassword);
        btnsubmit = (Button) findViewById(R.id.logibtn);
        sharedPreferences = getSharedPreferences(MyPrefone, Context.MODE_PRIVATE);
          sharedPreferencesdoctor =getSharedPreferences(Myveterinary, Context.MODE_PRIVATE);
         sharedPreferencesuser= getSharedPreferences(MyPref, Context.MODE_PRIVATE);

         checkBox=(CheckBox)findViewById(R.id.clickcheckboxlg);
         checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(!isChecked){
                     txtuserpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                 }else {
                     txtuserpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                 }
             }
         });
        // sharedPreferences=getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        validateform();

    }

    private void validateform() {

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strusername = txtusername.getText().toString().trim();
                strpassword = txtuserpassword.getText().toString().trim();
                if (strusername.isEmpty()) {
                    txtusername.setError("Please enter userid");
                    txtusername.requestFocus();
                } else if (strpassword.isEmpty()) {
                    txtuserpassword.setError("Please enter password");
                    txtuserpassword.requestFocus();
                }else if(!PASSWORD_PATTERN.matcher(strpassword).matches()) {
                    Toast.makeText(getApplicationContext(),"Password must be at least one upper case,lower case,Specila symbol,one digits without any space",Toast.LENGTH_SHORT).show();
                    txtuserpassword.requestFocus();
                }else if(strpassword.length()<6){
                    txtuserpassword.setError(" at least Password length must be 6");
                    txtuserpassword.requestFocus();
                }
                else {
                      loginwithapi();
                    // rolllogin();
                }
            }
        });

    }

    private void rolllogin() {


    }


    private void loginwithapi() {
        final ProgressDialog progressDialog = new ProgressDialog(DesignLoginRegisterPage.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", strusername);
            jsonObject.put("Password", strpassword);
            jsonObject.put("grant_type", "password");
            // URL+"useName="+strusername+"&Password="+strpassword+"&grant_type="+strpassword+""
            final String requestBody = jsonObject.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    LoginResponseModel loginResponseModel = (LoginResponseModel) new Gson().fromJson(response, LoginResponseModel.class);
                    Log.e("Volley", "hhhh" + loginResponseModel);
                    if (loginResponseModel != null) {
                        Log.e("response", loginResponseModel.getAccess_token());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Access_token", loginResponseModel.getAccess_token().toString());
                        editor.putString("usernamesplash", strusername);
                        editor.putString("userpasswordsplash", strpassword);
                        strrolldoctor = sharedPreferencesdoctor.getString("userroll", "");
                        struserroll = sharedPreferencesuser.getString("doctorroll", "");
                        editor.commit();
                        editor.apply();

                        //  userroll();
                        Toast.makeText(DesignLoginRegisterPage.this, "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(DesignLoginRegisterPage.this, DogsCatcherDetail.class);
                        startActivity(intent);
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley", error.toString());
                   /* try {
                        Gson gson=new Gson();

                      *//*  String responseString = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                        MyPojone myPojone=(MyPojone) gson.fromJson(responseString,MyPojone.class);
                      //  String[] res=myPojone.getModelState().getData();
                        Log.e("Volley", error.getLocalizedMessage()+"\n"+error.getCause()+"\n"+error.getStackTrace()+
                                "\n "+responseString);*//*
                      //  Toast.makeText(getApplicationContext()," "+myPojone.getError_description(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }*/
                }
            }) {

                @Override
                public String getBodyContentType() {
                    // return "MediaType.APPLICATION_FORM_URLENCODED;charset=utf-8";
                    return "application/x-www-form-urlencoded";

                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> Params = new HashMap<String, String>();
                    Params.put("userName", strusername);
                    Params.put("Password", strpassword);
                    Params.put("grant_type", "password");
                    return Params;

                }

                /*  @Override
                  public byte[] getBody() throws AuthFailureError{

                      try {
                          return  requestBody==null ? null:requestBody.getBytes("utf-8");
                      }catch (UnsupportedEncodingException yeye){
                          VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                          return null;
                      }
                  }*/
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = " ";
                    if (response != null) {
                        try {
                            responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("Content-Type", "application/x-www-form-urlencoded");

                    params.put("Accept", "application/json");

                    return params;

                }
            };
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v == btRegister) {
            Intent intent = new Intent(DesignLoginRegisterPage.this, RegisterActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvLogin, "tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(DesignLoginRegisterPage.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }
    }
}

