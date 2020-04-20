package com.example.dogslover;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.dogslover.R.array;
import static com.example.dogslover.R.drawable;
import static com.example.dogslover.R.id;
import static com.example.dogslover.R.layout;
import static com.example.dogslover.R.string;



public class MainActivity extends AppCompatActivity implements MainActivityinterface {




   // EditText etditentertags;
    Spinner spinner;
    EditText etditentertags,etname,etaddress,etstate,etcity,etstreet;
    ImageView imguploadimage;
    Button btnsave;
    String nm,add,cit,sta,str,ettag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
       /* mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(id.drawer_layout);
        mDrawerList = (ListView) findViewById(id.left_drawer);
*/
       // setupToolbar();

       /* DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(drawable.connectimg, "Connect");
        drawerItem[1] = new DataModel(drawable.fixturesimg, "Fixtures");
        drawerItem[2] = new DataModel(drawable.tableimg, "Table");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/


       /* DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(id.drawer_layout);
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        setupDrawerToggle();*/


        spinner=(Spinner)findViewById(id.dogname);
        addListenerOnSpinnerItemSelection();
        etname=(EditText)findViewById(id.etregname);
        etaddress=(EditText)findViewById(id.etregaddress);
        etcity=(EditText)findViewById(id.etregcity);
        etstate=(EditText)findViewById(id.etregstate);
        etstreet=(EditText)findViewById(id.etregstate);
        imguploadimage=(ImageView)findViewById(id.imageView);
        btnsave=(Button)findViewById(id.btnregsave);

       btnsave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               nm=etname.getText().toString().trim();
               add=etaddress.getText().toString().trim();
               cit=etcity.getText().toString().trim();
               sta=etstate.getText().toString().trim();
               str=etstreet.getText().toString().trim();

               if(nm.isEmpty()){
                   etname.setError("Please enter name");
                   etname.requestFocus();
               }else if(add.isEmpty()){
                   etaddress.setError("Please enter address");
                   etaddress.requestFocus();
               }else if(cit.isEmpty()){
                   etcity.setError("Please enter city name ");
                   etcity.requestFocus();
               }else if(sta.isEmpty()){
                   etstate.setError("Please enter State name");
                   etstate.requestFocus();
               }else {
                 startActivity(new Intent(MainActivity.this,RegistrationDogPAge.class));
                 finish();
               }

               /*else if(str.isEmpty()){
                   etstreet.setError("Please enter street name");
                   etstreet.requestFocus();
               }*/
           }
       });


        //adding click listener to button
        findViewById(id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*etditentertags=(EditText)findViewById(R.id.entertags);
                //if the tags edittext is empty
                //we will throw input error
                if (etditentertags.getText().toString().trim().isEmpty()) {
                    etditentertags.setError("Enter tags first");
                    etditentertags.requestFocus();
                    return;
                }*/

                //if everything is ok we will open image chooser
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });
    }

    /*private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }*/
/*
    private void selectItem(int position) {

        FragmentTable fragment = null;

        switch (position) {
            case 0:
                fragment = new FragmentTable();
                break;
            case 1:
                fragment = new FragmentTable();
                break;
            case 2:
                fragment = new FragmentTable();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    void setupToolbar(){
       // toolbar = (Toolbar) findViewById(id.toolbar);
        //setSupportActionBar(toolbar);
         toolbar=(Toolbar)findViewById(id.toolbar);
         setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
       // actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar, string.app_name, string.app_name);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,string.app_name,string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        actionBarDrawerToggle.syncState();
    }
*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            //getting the image Uri
            Uri imageUri = data.getData();
            try {
                //getting bitmap object from uri
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                //displaying selected image to imageview
                imguploadimage.setImageBitmap(bitmap);

                //calling the method uploadBitmap to upload image
                uploadBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBitmap(final Bitmap bitmap) {

        //getting the tag from the edittext
      //  final String tags = etditentertags.getText().toString().trim();

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.UPLOAD_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                          //  Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tags"," ");
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
    private void addListenerOnSpinnerItemSelection() {
       // spinner = (Spinner) findViewById(R.id.dogname);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
