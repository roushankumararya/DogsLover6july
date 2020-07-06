package com.example.dogslover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomeAdapter extends BaseAdapter {

    private ArrayList<Dogs> dogsList;
    private Context context;

    public CustomeAdapter(ArrayList<HashMap<String, String>> list, DogsLIist cont){
       // this.dogsList = list;
      //  this.context = cont;
    }

    @Override
    public int getCount() {
        return this.dogsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dogsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.list_row, null);

            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.lstname);
            holder.address = (TextView)convertView.findViewById(R.id.lstaddress);
            holder.age = (TextView)convertView.findViewById(R.id.lstage);
            holder.image = (ImageView)convertView.findViewById(R.id.lstimage);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        Dogs stu = dogsList.get(position);

        holder.name.setText(stu.getName());
        holder.age.setText(stu.getAge());
        holder.address.setText(stu.getAddress());
       // holder.image.setImageBitmap(stu.getImage());

        return convertView;
    }

    private static class ViewHolder{

        public TextView name;
        public TextView age;
        public TextView address;
        public ImageView image;
    }
}
