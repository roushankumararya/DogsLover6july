package com.example.dogslover;

public class ModelMessage {
    private String[] msg;

    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }
    @Override
    public String toString()
    {
        return "ClassPojo [data = "+msg+"]";
    }
}
