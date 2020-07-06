package com.example.dogslover;

public class Dogs {

    private String name;
    private String address;
    private String age;
    private String image;

    public Dogs(String name, String address, String age, String image) {

        this.name = name;
        this.address = address;
        this.age = age;
        this.image = image;

    }

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
}
