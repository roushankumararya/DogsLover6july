package com.example.dogslover;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VeterinaryDoctor extends AppCompatActivity{

    private RelativeLayout rlayout;
    private Animation animation;

    public static final String Myveterinary = "Myroll";
    SharedPreferences sharedPreferences;

    String URL="http://project26.aspdotnetstorefront.shoppingmegamart.com/StreetDogLovers/api/account/Register";
    EditText vdname,vdaddress,vdcity,vdstate,vdphone,vdqualification,vdconsultationfee,vddogsconsultationfee,vdemail,vdpassword,vdroll;
    String strvdname,strvdaddress,strvdcity,strvdstate,strvdphone,strvdqualification,
            strvdconsultationfee,strvddogsfee,strvdemail,strvdpassword,strvdroll;
    Button btnveterinarydoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_doctor);

        /*Toolbar toolbar = findViewById(R.id.bgHeaderdoctor);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rlayout = findViewById(R.id.rlayoutdoctor);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        vdname=(EditText)findViewById(R.id.txtrcnamedoctor);
        vdaddress=(EditText)findViewById(R.id.txtrcaddressdoctor);
        vdcity=(EditText)findViewById(R.id.txtrccitydoctor);
        vdstate=(EditText)findViewById(R.id.txtrcstatedoctor);
        vdphone=(EditText)findViewById(R.id.txtrcphonedoctor);
        vdqualification=(EditText)findViewById(R.id.txtQualificationdoctor);
        vdconsultationfee=(EditText)findViewById(R.id.txtConsultationFeedoctor);
        vddogsconsultationfee=(EditText)findViewById(R.id.txtConsultationFee_ForStreetDogsdoctor);
        vdemail=(EditText)findViewById(R.id.txtrcmailiddoctor);
        vdpassword=(EditText)findViewById(R.id.txtrcpassworddoctor);
        sharedPreferences=getSharedPreferences(Myveterinary, Context.MODE_PRIVATE);
      //  vdroll=(EditText)findViewById(R.id.txtrcrolldoctor);
        btnveterinarydoctor=(Button)findViewById(R.id.btnrcsubmitregistrationdoctor);
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
               // strvdroll=vdroll.getText().toString();
                if(strvdname.isEmpty()){
                    vdname.setError("Please enter name");
                    vdname.requestFocus();
                }else  if (strvdaddress.isEmpty()){
                    vdaddress.setError("Please enter address");
                    vdaddress.requestFocus();
                }else if(strvdcity.isEmpty()){
                    vdcity.setError("Please enter city");
                    vdcity.requestFocus();
                }else if(strvdstate.isEmpty()){
                    vdstate.setError("Please enter state name");
                    vdstate.requestFocus();
                }else if(strvdphone.isEmpty()){
                    vdphone.setError("Please enter phone number");
                    vdphone.requestFocus();
                }else if(strvdqualification.isEmpty()){
                    vdqualification.setError("Please enter qualification");
                    vdqualification.requestFocus();
                }else if(strvdconsultationfee.isEmpty()){
                    vdconsultationfee.setError("Please enter consultancy fee");
                    vdconsultationfee.requestFocus();
                }else if(strvddogsfee.isEmpty()){
                    vddogsconsultationfee.setError("Please enter dogs consultancy fee");
                    vddogsconsultationfee.requestFocus();
                }else if(strvdemail.isEmpty()){
                    vdemail.setError("Please enter mail id");
                    vdemail.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(strvdemail).matches()){
                    vdemail.setError("Please enter correct mail id");
                    vdemail.requestFocus();
                }else if(strvdpassword.isEmpty()){
                    vdpassword.setError("Please enter password");
                    vdpassword.requestFocus();
                }else {
                    VeterinaryDoctorRegistration();
                }
            }

        });
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



    private void VeterinaryDoctorRegistration() {
        final ProgressDialog progressDialog=new ProgressDialog(VeterinaryDoctor.this);
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

            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    VeterinarysuccessPojo veterinarysuccessPojo=(VeterinarysuccessPojo)new Gson().fromJson(response,VeterinarysuccessPojo.class);
                    if(veterinarysuccessPojo!=null){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("doctorroll",veterinarysuccessPojo.getRoles().toString());
                        editor.commit();
                        editor.apply();
                    }

                    Toast.makeText(VeterinaryDoctor.this, "Registration successful", Toast.LENGTH_SHORT).show();
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
}
