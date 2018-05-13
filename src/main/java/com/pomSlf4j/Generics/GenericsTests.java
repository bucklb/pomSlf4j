package com.pomSlf4j.Generics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//
// Need to get better handle on the whole generic stuff.
//
public class GenericsTests {

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
        for( X i:xArray){
            System.out.print(i.toString() + " ");
        }
        System.out.println("");

        // The list that we got back
        System.out.print("As basic sorted list ... ");
        for( X i:xList){
            System.out.print(i.toString() + " ");
        }
        System.out.println("");

        // done.
    }

    // Need something to exercise the basic attempts at generic stuff
    public void testGenericsArrayToList(){
        Integer[] T={1,5,3,4,2};
        String[] S={"one","two","three","four","five"};
        testArrayToList(T);
        testArrayToList(S);
    }





}
