package com.example.dogslover;

import android.widget.ImageView;

public class Dogsfetchlist {

    String name,address,age,image;
  //  ImageView image;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }


    public Dogsfetchlist(String name, String address, String age, String image){
        this.name=name;
        this.address=address;
        this.age=age;
        this.image=image;

    }
}
