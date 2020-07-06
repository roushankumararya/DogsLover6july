package com.example.dogslover;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogsLIist extends Fragment {


    public static final String MyPrefone = "MyPref";
    String strtoken,strname,strpassword;
    ArrayList<String> addArrayT = new ArrayList<>();
    TextView textView;
    ListView listView;
    List<Dogsfetchlist> heroList;
    String URL=" http://project26.aspdotnetstorefront.shoppingmegamart.com/StreetDogLovers/api/Dog/Get";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        listView = (ListView)view.findViewById(R.id.dogslistone);
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences(MyPrefone, Context.MODE_PRIVATE);
        strtoken = sharedPreferences.getString("Access_token", "");

        strname=sharedPreferences.getString("usernamesplash","");
        strpassword=sharedPreferences.getString("userpasswordsplash","");

        heroList = new ArrayList<>();
        loadHeroList();
        return view;
    }

    private void loadHeroList() {


      //  final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        //making the progressbar visible
      //  progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                       // progressBar.setVisibility(View.INVISIBLE);
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("heroes");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Dogsfetchlist hero = new Dogsfetchlist(heroObject.getString("name"), heroObject.getString("address"),
                                        heroObject.getString("age"),heroObject.getString("imageurl"));

                                //adding the hero to herolist
                                heroList.add(hero);
                            }

                            //creating custom adapter object
                            DogsFetchListviewAdapter adapter = new DogsFetchListviewAdapter(heroList, getActivity());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (error.networkResponse.data!=null) {
                                String responseString = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.e("volleyError",
                                        "\n " + responseString);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        //displaying the error in toast if occurrs
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("Content-Type", "application/json; charset=utf-8");
                        String creds = String.format("%s %s", "", strtoken);
                        Log.e("tokern", "" + "Bearer " + creds.trim() + "\n" + creds);
                        params.put("Authorization", "Bearer " + creds.trim());
                        return params;
                    }
                   /* protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String,String> Params = new HashMap<String, String>();
                        Params.put("userName",strname);
                        Params.put("Password",strpassword);
                        Params.put("grant_type","password");
                        return Params;

                    }*/
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //adding the string request to request queue
        requestQueue.add(stringRequest);


    }


}
