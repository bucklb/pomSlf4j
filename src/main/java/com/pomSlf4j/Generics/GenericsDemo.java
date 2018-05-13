package com.pomSlf4j.Generics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//
// Want to see what the deal is with generics in Java (and may end up being able to make sense of the abstract builder generics)
//
public class GenericsDemo {


    public static void main(String[] args) {

        GenericsTests t=new GenericsTests();

        // first stab at any kind of generic
        t.testGenericsArrayToList();



    }
}
