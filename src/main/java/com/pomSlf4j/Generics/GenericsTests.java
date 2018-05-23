package com.pomSlf4j.Generics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//
// Need to get better handle on the whole generic stuff.
//
public class GenericsTests {

    private interface GenericIface{
        public String sayHello();
    }

    private class Greeter implements  GenericIface{

        @Override
        public String sayHello() {
            return "Yo!";
        }
    }



    // Just turn an array in to a sorted list.  No transformation of type!
    // presumably really ought to check that there's something meaningful that T's can be sorted on.
    private <T> List<T> arrayToSortedList(T[] a){
        // Don't actually care about the type we've been asked to deal with in the method itself this time
        return Arrays.stream(a).sorted().collect(Collectors.toList());
    }

    // Allow an array of (just about) any type to be passed to use, so we can splat the array and sorted list from array to sysout
    // use <X> between private and void to flag that we will use an (as yet) unspecified class in the function for the array
    // Note - need to use X in the for loops too...
    private <X> void testArrayToList(X[] xArray){

        // The bit that was meant to be the first hint of generic.  Turn array in to sorted list
        List<X> xList= arrayToSortedList(xArray);

        // The untouched array
        System.out.print("As  (unsorted) array ... ");
        for( X x:xArray){
            System.out.print(x.toString() + " ");
        }
        System.out.println("");

        // The list that we got back
        System.out.print("As basic sorted list ... ");
        for( X x:xList){
            System.out.print(x.toString() + " ");
        }
        System.out.println("");

        // done.
    }

    // Now want to transform the Type we got as array to another Type in list
    // - so an extra step compared to arrayToList
    // Need to know how to transform ...
    private <T,G> List<G> arrayToListWithMapping(T[] tArray, Function<T,G> mapTtoGFunction){
        // Use the mapper function to transformn T to G
        return Arrays.stream(tArray).map(mapTtoGFunction).sorted().collect(Collectors.toList());
    }

    private <X,Y> void testarrayToListWithMapping(X[] xArray, Function<X,Y> mapping){
        // The bit that was meant to be the first hint of generic.  Turn array in to sorted list
        List<Y> yList= arrayToListWithMapping(xArray, mapping);

        // The untouched array
        System.out.print("As  (unsorted) array of type X ... ");
        for( X x:xArray){
            System.out.print(x.toString() + " ");
        }
        System.out.println("");

        // The list that we got back
        System.out.print("As basic sorted list of type Y ... ");
        for( Y y:yList){
            System.out.print(y.toString() + " ");
        }
        System.out.println("");


    }



    // Need something to exercise the basic attempts at generic stuff
    public void testGenericsArrayToList(){
        Integer[] T={1,5,3,4,2};
        String[] S={"one","two","three","four","five"};
        Integer[] X={11,5,13,4,12};

        testArrayToList(T);
        testArrayToList(S);

        // Use multi digit array to illustrate that stuff got converted to strings (and so sorted rather differently to numbers)
        testarrayToListWithMapping(X,Object::toString);
        // Convert string to string ... just cause we can
        testarrayToListWithMapping(S,Object::toString);
        // Convert string to substring.  Kind of assumes that the array is something that substring applie to ...
        testarrayToListWithMapping(S,s->s.substring(1,3));
        // Illustrate Integer to double with explicit lambda
        testarrayToListWithMapping(X,s->s.doubleValue()*0.3333333);

    }



    // Look at restriction guff
    public void testRestriction(){
        // TODO
    }



}
