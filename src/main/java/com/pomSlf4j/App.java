package com.pomSlf4j;

/**
 * Hello world!
 *
 * Want to see if I can do the logging guff via pom rather than JAR files in classpath
 *
 */
import com.pomSlf4j.lambda.Anon;
import com.pomSlf4j.lambda.Java8Tester;
import com.pomSlf4j.lambda.Lambda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//
// Basically redundant as the sub sections can be run independently now
//
public class App 
{

    // Splitting out in to sub-packages that should have their own "Demo" classes to run them ...
    public static void main( String[] args )
    {




if (0==0)  {


        System.out.println("-- calling java 8 stuff");
        Java8Tester j = new Java8Tester();
        j.testJava8();

        System.out.println("-- calling lambda stuff");
        Lambda l = new Lambda();
//        l.arse();

        System.out.println("-- calling anonymous stuff");
        Anon a = new Anon();
        a.doHW();

/*
        // 19/2/18 - play with builder
        System.out.println("-- playing with builder stuff");
        Vanilla x = new Vanilla.Bldr("Wendy").job("wifey").build();
        System.out.println(x.getName() + " >> " + x.getJob());

        Vanilla y = new Vanilla.Bldr("Harry").build();
        System.out.println(y.getName() + " >> " + y.getJob());
        System.out.println(x.getName() + " >> " + x.getJob());
*/

        System.out.println("-- playing with logger stuff");
        // Get a logger.  If we have slf4j-log4j12 in dependencies we'll get LOG4J
        Logger logger = LoggerFactory.getLogger(App.class);

        logger.warn("warn stuff ");
        logger.error("Temperature set to {}. Old temperature was {}.", "32", "96");


        System.out.println("-- playing with ? operator stuff");
        String s = null;
        System.out.println(s != null ? s : "empty");
        s = "full";
        System.out.println(s != null ? s : "empty");
}

    }






}
