package com.pomSlf4j.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.stream.Stream;

/**
 * Created by buckl on 20/02/2018.
 *
 * Try & get handle on lambda & functional programming
 *
 * https://jlordiales.me/2014/11/01/overview-java-8/
 *
 *
 */
public class Lambda {

    //
    // FileFilter seems a candidate for lambda
    //
    public void exercise() {

        // Looking at using a filter on what the file names end with.  Doesn't seem to need to be Final ...
        String endsWith="-ms";

        System.out.println("Hello Directories!");

        // Create a fileFilter up front, complete with name.  It's an implementation (effectively) so we need to provide
        // details of what accept is meant to do
        FileFilter namedFF=new FileFilter() {
            @Override
            public boolean accept(File path) {
                return path.toString().endsWith(endsWith);
            }
        };

        // usingNamed  - creates a FFclass in readiness, and then use it
        // usingAnonFF - create an inner class on the fly ( that in other cases would be an implement of FileFilter),
        //               that exists to provide an "accept" function
        // usingLambda - a lambda that relies on easy mapping File->isDirectory (so use an existing function that suits us)
        //               the lamdda takes a file and realises that isDirectory wil work as contents of an "accept" function
        // vsingLambda - a lambda that is more hand crafted, rather than uses pre-existing single function



        // Plug the predefined filter IN. Had to set things up in advance. Not likely to be able to set internal values now!
        File[] usingNamedFF = new File("C:\\Users\\buckl").listFiles(namedFF);
        System.out.println("usingNamedFF" + " -> " + usingNamedFF[0]+ " -> " + usingNamedFF[1]);


        // On the fly.  Not using a named instance & can tweak on the fly ...
        File[] usingAnonFF = new File("C:\\Users\\buckl").listFiles(
                new FileFilter() {
                    // we haven't created a named variable
                    @Override
                    public boolean accept(File anonpathname) {
                        return anonpathname.toString().endsWith(endsWith);
                    }
                }
        );
        System.out.println("usingAnonFF " + " -> " + usingAnonFF[0]+ " -> " + usingAnonFF[1]);


        // Not even needing to explicitly specify that we are doing FileFilter, but it limits options
        //                                                                    VVVVVVVVVVVVVVVVV
        File[] usingLambda = new File("C:\\Users\\buckl").listFiles(File::isDirectory);
        System.out.println("usingLamda " + " -> " + usingLambda[0]+ " -> " + usingLambda[1]);


        // This next bit does what usingAnonFF does in a fraction of the lines
        File[] vsingLambda = new File("C:\\Users\\buckl").listFiles(f->f.toString().endsWith(endsWith));
        System.out.println("vsingLamda " + " -> " + vsingLambda[0]+ " -> " + vsingLambda[1]);







    }

}
