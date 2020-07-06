package com.example.dogslover;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public  class DogsRegistrationForm extends Fragment {

    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public static final String MyPrefone = "MyPref";
    EditText dganme, dgaddress, dgage;
    String strdgname, strdgaddress, strdgage, strdgimage, strtoken;
    ImageView dgimage;
    Button btnsubmit;
    Bitmap bitmap;
    String imageName;
    private String TAG=DogsRegistrationPage.class.getSimpleName();
    String URL = "http://project26.aspdotnetstorefront.shoppingmegamart.com/StreetDogLovers/api/Dog/Register";
 //   private static final int MAX_BITMAP_SIZE = 100 * 1024 * 1024; // 100 MB
  private static final int MAX_IMAGE_SIZE = 700 * 1024; // 100 MB
    int btnmapwidht=720,btnmapheight=576;
    int btntotalsize=btnmapwidht*btnmapheight;
    TextView textView;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dogsregistrationfragment, container, false);
        dganme = (EditText) view.findViewById(R.id.txtvgdname);
        //  MultipartEntity entity = new MultipartEntity();
        dgaddress = (EditText)view.findViewById(R.id.txtvgdaddress);
        dgage = (EditText) view.findViewById(R.id.txtvgdage);
        dgimage = (ImageView) view.findViewById(R.id.dogsimage);
        btnsubmit = (Button) view.findViewById(R.id.btnrcsubmitdogsregistration);
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences(MyPrefone, Context.MODE_PRIVATE);
        strtoken = sharedPreferences.getString("Access_token", "");
        dgimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if everything is ok we will open image chooser
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strdgname = dganme.getText().toString().trim();
                strdgaddress = dgaddress.getText().toString().trim();
                strdgage = dgage.getText().toString().trim();

                if (strdgname.isEmpty()) {
                    dganme.setError("Please enter dog'name");
                    dganme.requestFocus();
                } else if (strdgaddress.isEmpty()) {
                    dgaddress.setError("Please enter found dog'address");
                    dgaddress.requestFocus();
                } else if (strdgage.isEmpty()) {
                    dgage.setError("Please enter dog's age");
                    dgage.requestFocus();
                } else {
                    savefounddogsdata();
                }
            }
        });
        /*textView = view.findViewById(R.id.textfragment);
        textView.setText("Third");*/
        return view;
    }
    /*-----------------------------------------------------------------------*/

   /* // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            HashMap<String,String> user = new HashMap<>();
            user.put("name",strdgname);
            user.put("address",strdgaddress);
            user.put("age",strdgage);
            userList.add(user);
        return  userList;
    }*/

    /*-----------------------------------------------------------------------*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            imageName=getFileName(imageUri);
            // bitmap = BitmapFactory.decodeFile(imageName);
            try {
               // bitmap = BitmapFactory.decodeFile(imageName);
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

                int origWidth = bitmap.getWidth();
                int origHeight = bitmap.getHeight();
                final int destWidth = 720;//or the width you need
                //  final int destHeight = 576;
                if(origWidth > destWidth) {
                    // picture is wider than we want it, we calculate its target height
                    int destHeight = origHeight / (origWidth / destWidth);
                    // we create an scaled bitmap so it reduces the image, not just trim it
                     bitmap = Bitmap.createScaledBitmap(bitmap, destWidth, destHeight, false);
                    dgimage.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                  }

    }



    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = this.getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void savefounddogsdata() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL, new
                Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Toast.makeText(getActivity(), "Registration success", Toast.LENGTH_LONG).show();
                        try {
                            Log.e("response", " ff" + new String(response.data)+"\n "+response);
                            String responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            DogRegisterResponseModel dogRegisterResponseModel=(DogRegisterResponseModel)new Gson().fromJson(responseString,DogRegisterResponseModel.class);
                            Toast.makeText(getActivity(),"Registration has been Sucessfull.", Toast.LENGTH_SHORT).show();
                        } catch ( UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                        dganme.setText(" ");
                        dgaddress.setText(" ");
                        dgage.setText(" ");
                        dgimage.setImageBitmap(null);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (error.networkResponse.data!=null) {
                                String responseString = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.e("Volley error",
                                        "\n " + responseString);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
//                params.put("Name", strdgname);
//                params.put("Age", strdgage);
                //   params.put("Address", strdgaddress);
                params.put("Content-Type", getBodyContentType());
                //String tonken = "vl8qS-lid96BYcbPmJ9SOJw5V8PCB41GRaRQyNrE_JMvnDNYwRj0t5WSDgYrxt4mKEVbQpgfDfQoC4SwKP_Z391VCg2DhSmd1bqwbB7-dJDGsXMsFBcj7R8rIVGw5elfg_9t2E0_Ve6MTyUUYW0YdTOvmc1gCq_chknIAJzVTf-O4ZZ-PuVF9ZJs0DDYxF5clRhP2EGmrTiMVixbmv1q707qyJ0XejwhWHQ2ulpwK3TLDC_MX4SeNTQxsXirEWgHwzvjGhPtWCkzikklUw_5xa_hjqV-e4D_fSXcctKnKvwYvsreIxQz9OluK3veqxbSiCdQMoaM5L_ZQZ5xLk6oFYNUjWXlnPkiLQWtSTTPAMP_n5ysQsZ51dgtxTJIn1yERO6M_KXWLfMql4Jdi4DT55BLHbhREFAkZJsmRYl7LgvkLLkPT-WMxcJKkuI6qtpgYMSYs1voSlHkicT2ZOB5Xs4tx9e2ZazwhKt3D1T5NDRf7piXOKIAhUi-gSkdmM8TTwyEOiWjaL29C3jFXOlSBg";

                String creds = String.format("%s %s", "", strtoken);
                // String tokent="Bearer JMCbVDZDRIQV_KkbiDN2sHTXoD7yyEp5xfnDfaX60ruQQ73CkvahEtM4D8X8UrHZjY6oLrk3_BlBF3BlQWcY3tB_3hcnc4dbDZmkgMSy6ucXwd9l3IWg3D4G0pf33vABSZMD1kushPDP5v5xDnTccJG9NPnQwPVRXBHqs3MfYTlSQZIbdY9xhcE9QJ-aCOWQ7xrOtwsQ7eTJkdBmHTLvQOHcndu0FGfQsN83_1EeXkTNWVpn4alSz0twZU2anTB19JrB_6hhQ1BE_dNeVPPdSxbsAlCzDHCpeMYaAjjYqYep_2-ZJUY6T5hcu3ttLWVl2Vxt_eNjULooyFf3tkZl4YBgro5ljqU1mmJc8YoLmub1zjkEfE1CZqKSZtZRI4tpxYJovf-MKAi33jMZZPJds0oSIDMbX6hhNtBWIZQPomQwqwoUThgyQr_w6O8l4oyhaU5vXJtkCz-9NILWK22iE_EC2qcyD-Kuk2Q0WTGVaKJyPUjsvdFN6Va5L_fTvBgKnCE3YwzzuVNT82S3UQ7nhg";
                // String auth = "Bearer " + Base64.encodeToString(tokent.getBytes(), Base64.NO_WRAP);
                Log.e("tokern", "" + "Bearer " + creds.trim() + "\n" + creds);
                params.put("Authorization", "Bearer " + creds.trim());
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parent = new HashMap<>();
                Map<String, String> params = new HashMap<>();
                params.put("Name", strdgname);
                params.put("Age", strdgage);
                params.put("Address", strdgaddress);
                Log.e("paramas", new Gson().toJson(params));
                parent.put("json", new Gson().toJson(params));
                return parent;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                //  long imagename = System.currentTimeMillis();
                //  Log.e(TAG,"dagtt "+bitmap+"\n"+imagename);
                params.put("Picture", new DataPart(imageName,getFileDataFromDrawable(bitmap),"image/jpeg"));
                /*try {
                //    params.put("Picture", new DataPart(imageName,getFileDataFromDrawable(bitmap,1024,1024),"image/jpeg"));

                    params.put("Picture", new DataPart(imageName,,"image/jpeg"));

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
    }
}
