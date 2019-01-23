package com.gary.weatherdemo.eventbus;

public class EventMessage {
    private String mMessage;
    public EventMessage(String message){
        this.mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }
}
