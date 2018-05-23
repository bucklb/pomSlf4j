package com.pomSlf4j.Builders;

//
// Seem to be a number of approaches to using the "builder" pattern, so want to play with them ...
//
public class BuildersTests {

    //
    // bld is a fairly vanilla approach.
    //
    public void testVanilla(){

        Vanilla v = new Vanilla.VanillaBuilder("Harry").build();
        System.out.println("Bare minimum : " + v.getName() + " >> " + v.getJob());

        Vanilla w = new Vanilla.VanillaBuilder("Irene").job("Ran a canteen").build();
        System.out.println("Full build   : " + w.getName() + " >> " + w.getJob());

    }


}
