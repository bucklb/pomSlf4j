package com.pomSlf4j.lambda;

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

    //
    // Rather than pass a name to the greeter, allow the greeter to be passed where the name is (so no need to expose name)
    // - so the name we want to portray is not known to wider code base
    //
    private void testGreeter(HW hw){
        hw.greetByName("Scarlet Pimpernel");
    }




    public void doHW(){

        // Define a class with requisite functionality & then create an instance of it to do the work
        // Can be used many times & oft, but no obvious reasons we'd really want to if it's a trivial case like this
        // - we'd be wanting to generate an awful lot of distinct names for the various classes
        final String sGreet="Yo yo yo ";
        class NamedHW implements HW {
            public void greet() {                System.out.println(sGreet);            }
            public void greetByName(String name) {                System.out.println(sGreet + name);            }
        }
        NamedHW namedHW=new NamedHW();


        // Below we create hw by means of a "nameless" implementation of HW interface (it's not got an explicit class name)
        // Assume this is less reusable / more disposable.  It's defined/exists only as long as it's needed/relevant
        //
        // This is perhaps defining an implementation on the fly.  Might be able to tweak what it does too...
        // - could be especially useful if we want to set the implementation up according to the program, so for instance
        //   might want to use a local variable directly, rather than passing it in via a constructor/property
        final String theGreeting="IMPROVISED GREETING ";
        HW hw = new HW() {
            public void greet() {                System.out.println(theGreeting);            }
            public void greetByName(String name) {                System.out.println(theGreeting + name);            }
        };

        // Use named version (explicit class name used)
        namedHW.greet();
        namedHW.greetByName("named One");

        // Use "anonymous" version.  We've not giving this CLASS a name, but it still needs a name for this instance
        hw.greet();
        hw.greetByName("Anon");

        // Pass greeter(s) as function
        System.out.println("==== Passing implementations of HW as parameter to testGreeter");
        testGreeter(namedHW);
        testGreeter(hw);

    }



}
