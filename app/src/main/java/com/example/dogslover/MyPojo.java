package com.example.dogslover;

public class MyPojo
{
    private String Message;

    private ModelState ModelState;

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public ModelState getModelState ()
    {
        return ModelState;
    }

    public void setModelState (ModelState ModelState)
    {
        this.ModelState = ModelState;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Message = "+Message+", ModelState = "+ModelState+"]";
    }
}