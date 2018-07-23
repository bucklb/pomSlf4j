package com.pomSlf4j.Callback;

public class CallbackListenerImpl implements CallbackListener {

    String greeting="default greeting";

    public CallbackListenerImpl(String greeting){
        this.greeting=greeting;
    }

    @Override
    public void onCallbackEvent() {
        System.out.println("Greeting = " + greeting);
    }
}
