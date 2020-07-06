package com.example.dogslover;

public class DogRegisterResponseModel
{
    private String Address;

    private String DogID;

    private String UserID;

    private String Picture;

    private String  UpdatedOn;

    private String CreatedOn;

    private String AspNetUser;

    private String Age;

    private String Name;

    public String getAddress ()
    {
        return Address;
    }

    public void setAddress (String Address)
    {
        this.Address = Address;
    }

    public String getDogID ()
    {
        return DogID;
    }

    public void setDogID (String DogID)
    {
        this.DogID = DogID;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    public String getPicture ()
    {
        return Picture;
    }

    public void setPicture (String Picture)
    {
        this.Picture = Picture;
    }

    public String getUpdatedOn ()
    {
        return UpdatedOn;
    }

    public void setUpdatedOn (String UpdatedOn)
    {
        this.UpdatedOn = UpdatedOn;
    }

    public String getCreatedOn ()
    {
        return CreatedOn;
    }

    public void setCreatedOn (String CreatedOn)
    {
        this.CreatedOn = CreatedOn;
    }

    public String getAspNetUser ()
    {
        return AspNetUser;
    }

    public void setAspNetUser (String AspNetUser)
    {
        this.AspNetUser = AspNetUser;
    }

    public String getAge ()
    {
        return Age;
    }

    public void setAge (String Age)
    {
        this.Age = Age;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Address = "+Address+", DogID = "+DogID+", UserID = "+UserID+", Picture = "+Picture+", UpdatedOn = "+UpdatedOn+", CreatedOn = "+CreatedOn+", AspNetUser = "+AspNetUser+", Age = "+Age+", Name = "+Name+"]";
    }
}
