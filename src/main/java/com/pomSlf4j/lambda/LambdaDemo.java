package com.pomSlf4j.lambda;

//
// Potentially more than just lambda in this package !!
//
// Need to group things in to meaningful blobs, rather than letting the play stuff get utterly out of control
//
public class LambdaDemo {

    //
    public static void main(String[] args) {

        System.out.println("\n+++ ANON bits ++++++++++++++++++++++++++++++");
        Anon anon=new Anon();
        anon.doHW();

        System.out.println("\n+++ LAMBDA bits ++++++++++++++++++++++++++++++");
        Lambda lambda=new Lambda();
        lambda.exercise();

        System.out.println("\n+++ Java8Tester ++++++++++++++++++++++++++++++");
        Java8Tester java8Tester=new Java8Tester();
        java8Tester.testJava8();
    }
}
