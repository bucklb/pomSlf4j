package com.pomSlf4j;

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

    public void arse() {

        final String endsWith="-ms";

        System.out.println("Hello Directories!");

        // Create a fileFilter up front, complete with name
        FileFilter namedFF=new FileFilter() {
            public boolean accept(File path) {
                return path.toString().endsWith(endsWith);
            }
        };

        // Plug the predefined filter IN. Had to set things up in advance. Not likely to be able to set internal values now!
        File[] usingNamedFF = new File("C:\\Users\\buckl").listFiles(namedFF);
        System.out.println("usingNamedFF" + " -> " + usingNamedFF[0]+ " -> " + usingNamedFF[1]);

        // On the fly.  Not using a named instance & can tweak on the fly ...
        File[] usingAnonFF = new File("C:\\Users\\buckl").listFiles(
                new FileFilter() {
                    // we haven't created a named variable
                    public boolean accept(File anonpathname) {
                        return anonpathname.toString().endsWith(endsWith);
                    }
                }
        );
        System.out.println("usingAnonFF " + " -> " + usingAnonFF[0]+ " -> " + usingAnonFF[1]);

        // Not even needing to explicitly specify that we are doing FileFilter, but it limits options
        File[] usingLambda = new File("C:\\Users\\buckl").listFiles(File::isDirectory);
        System.out.println("usingLamda " + " -> " + usingLambda[0]+ " -> " + usingLambda[1]);

        // This next bit does what usingAnonFF does in a fraction of the lines
        File[] vsingLambda = new File("C:\\Users\\buckl").listFiles(f->f.toString().endsWith(endsWith));
        System.out.println("vsingLamda " + " -> " + vsingLambda[0]+ " -> " + vsingLambda[1]);







    }

}
