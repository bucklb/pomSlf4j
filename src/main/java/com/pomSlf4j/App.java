package com.pomSlf4j;

/**
 * Hello world!
 *
 * Want to see if I can do the logging guff via pom rather than JAR files in classpath
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class App 
{
    public static void main( String[] args )
    {

        System.out.println("-- codility");
        Codility c=new Codility();

        // a string and a number of rows
        String seatList="20a 20d 11g 1a 2f 3c 1b 2g 3d 12a 4d 4g 49h 15d 15f 16e 16g 17g 17d 17e 17f 18e 18f";
        int numRows=50;

        System.out.println("method 1      " + c.test(numRows,seatList));
        System.out.println("method 2      " + c.compactTest(numRows,seatList));
        System.out.println("method 3      " + c.arrayTest(numRows,seatList));
        System.out.println("method 4      " + c.newTest(numRows,seatList,"abc|defg_hjk", 3));



if (0==1)  {


        System.out.println("-- calling java 8 stuff");
        Java8Tester j = new Java8Tester();
        j.testJava8();

        System.out.println("-- calling lambda stuff");
        Lambda l = new Lambda();
        l.arse();

        System.out.println("-- calling anonymous stuff");
        Anon a = new Anon();
        a.doHW();


        // 19/2/18 - play with builder
        System.out.println("-- playing with builder stuff");
        bld x = new bld.Bldr("Wendy").job("wifey").build();
        System.out.println(x.getName() + " >> " + x.getJob());

        bld y = new bld.Bldr("Harry").build();
        System.out.println(y.getName() + " >> " + y.getJob());


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
