package com.pomSlf4j.Callback;

import java.util.concurrent.TimeUnit;

//
// Quick illustration of callbacks in both synchronous and asynchronous stylee
//
public class CallbackDemo {

    // Need a listener.  Define at i/f level so we can add chosen implementation later
    private CallbackListener callbackListener;

    // and allow a listener to be given
    public void registerCallbackListener(CallbackListener listener){
        this.callbackListener=listener;
    }

    public void doSynchronousStuff(){

        System.out.println("Doing a synchronous task, before calling callback");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (callbackListener!=null){
            callbackListener.onCallbackEvent();
        }

    }

    public void doAsynchronousStuff(){

        // perform any operation
        System.out.println("Doing an asynchronous task, before calling callback");

        // An Async task always executes in new thread
        new Thread(new Runnable() {
            public void run()
            {

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // check if listener is registered.
                if (callbackListener != null) {

                    // invoke the callback method
                    callbackListener.onCallbackEvent();
                }
            }
        }).start();
    }



        // Keep outside stuff simple by doing set up in here
    public void showCallback(boolean doSynchronous) {
        System.out.println("Show synch callback");

        if (doSynchronous) {

            // Create the listener and register it
            registerCallbackListener(new CallbackListenerImpl("synch call back"));
            doSynchronousStuff();
        } else {
            // Create the listener and register it
            registerCallbackListener(new CallbackListenerImpl("Asynch call back"));
            doAsynchronousStuff();
        }

        for (int i = 1; i<5;i++) System.out.println("Text to illustrate synch Vs Asynch");


    }



}
