package com.example.dogslover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DogsFetchListviewAdapter extends ArrayAdapter<Dogsfetchlist> {

    private List<Dogsfetchlist> heroList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public DogsFetchListviewAdapter(List<Dogsfetchlist> heroList, Context mCtx) {
        super(mCtx, R.layout.dogsfetchlistitem, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.dogsfetchlistitem, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.fetchdogslistname);
        TextView textViewAddress=listViewItem.findViewById(R.id.fetchdogslistaddress);
        TextView textViewAge=listViewItem.findViewById(R.id.fetchdogslistage);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.fetchdogslistimageurl);

        //Getting the hero for the specified position
        Dogsfetchlist hero = heroList.get(position);

        //setting hero values to textviews
        textViewName.setText(hero.getName());
        textViewAddress.setText(hero.getAddress());
        textViewAge.setText(hero.getAge());
        textViewImageUrl.setText(hero.getImage());

        //returning the listitem
        return listViewItem;
    }

}
