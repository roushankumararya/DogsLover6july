package com.example.dogslover;

public class PojoMessage {

    private String Msg;
    private ModelMessage ModelMessage;

    public ModelMessage getModelMessage() {
        return ModelMessage;
    }

    public void setModelMessage(ModelMessage modelMessage) {
        this.ModelMessage = modelMessage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Message = "+Msg+", ModelState = "+ModelMessage+"]";
    }
}
