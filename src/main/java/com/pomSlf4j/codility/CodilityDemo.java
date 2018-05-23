package com.pomSlf4j.codility;

//
// Group the codility stuff somewaht (in attempt to declutter the whole thing)
//
public class CodilityDemo {

    // Push a test case through every variant of codility
    // could amend to allow it to catch differences, but equally could do it with better testing in test area
    private static void tryAll(String seatList, int numRows){
        Codility c=new Codility();
        System.out.println(seatList);
        System.out.println("method 1      " + c.test(numRows,seatList));
        System.out.println("method 2      " + c.compactTest(numRows,seatList));
        System.out.println("method 3      " + c.arrayTest(numRows,seatList));
        System.out.println("method 4      " + c.newTest(numRows,seatList,"abc|defg_hjk", 3));
        System.out.println("method 5      " + c.streamTest(numRows,seatList));
        System.out.println("jeffTest      " + c.jeffTest(numRows,seatList));
    }

    // Split from the "main" app to allow level of granularity in playing etc
    public static void main(String[] args) {


        System.out.println("-- codility");

        // see how a handful of scenarios are handled by various approaches
//        String seatList="20a 20d 11g 1a 2f 3c 1b 2g 3d 12a 4d 4g 19h 15d 15f 16e 16g 17g 17d 17e 17f 18e 18f";
//        int numRows=50;
        tryAll("20a 20d 11g 1a 2f 3c 1b 2g 3d 12a 4d 4g 19h 15d 15f 16e 16g 17g 17d 17e 17f 18e 18f",20);
        tryAll( "1A 1B 1C 2F 3G 3E 3D 2D 2G 1H 1J".toLowerCase(),3);
        tryAll( "1A".toLowerCase(),1);
        tryAll( "1A 1D 1G 25D 25G".toLowerCase(),34);
        tryAll( "1A 1E 1H 2A 2E 2H 3A 3E 3H 1B 1C".toLowerCase(),3);
        tryAll( "1A 1B 1C 3B 3C 3D 3E 1K 2A 2B 2C 2D 2E 2F 2G 2H 1D 1E 1F 1G 1H 1J 2J 2K 3A 3F 3G 3H 3J 3K".toLowerCase(),3);




    }


}
