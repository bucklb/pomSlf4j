package com.pomSlf4j;

/**
 * Created by buckl on 20/02/2018.
 *
 * Look at anonymous classes (in run up to lamdda)
 *
 *
 *
 */
public class Anon {

    // anonymous class often an extension/implementation of summat
    interface HW{
        public void greet();
        public void greetByName(String name);
    }

    public void doHW(){

        // Define a class with requisite functionality & then create an instance of it to do the work
        // Can be used many times & oft, but no obvious reasons we'd really want to
        class NamedHW implements HW {
            public void greet() {                System.out.println("NamedHi");            }
            public void greetByName(String name) {                System.out.println("NamedHi " + name);            }
        }
        NamedHW namedHW=new NamedHW();


        // Below we create hw by means of a "nameless" implementation of HW interface
        // Assume this is less reusable / more disposable.  It's defined/exists only as long as it's needed/relevant
        //
        // This is perhaps defining an implementation on the fly.  Might be able to tweak what it does too...
        // - could be especially useful if we want to set the implementation up according to the program, so for instance
        //   might want to use a local variable directly, rather than passing it in via a constructor/property
        final String theGreeting="howdy ";
        HW hw = new HW() {
            public void greet() {                System.out.println(theGreeting);            }
            public void greetByName(String name) {                System.out.println(theGreeting + name);            }
        };

        // Use named version (explicit class)
        namedHW.greet();
        namedHW.greetByName("named One");

        // Use anonymous version
        hw.greet();
        hw.greetByName("Anon");





    }



}
