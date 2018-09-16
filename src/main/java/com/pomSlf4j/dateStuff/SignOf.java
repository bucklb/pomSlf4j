package com.pomSlf4j.dateStuff;

import java.util.ArrayList;
import java.util.List;

// Look at interesting times (with 2 or fewer digits)
public class SignOf {

    private static final boolean DBG=false;

    // Turn digits in to what would appear on a clock display
    private static String clockify(int d1,int d2){
        return Integer.toString(d1) + Integer.toString(d2);
    }

    // Grab the hour part of the timeString (delimited by :)
    private static int getIntHour(String theTime){
        String[] bits=theTime.split(":");
        return Integer.parseInt(bits[0]);
    }

    // Got the digits and ordered, so know d1<=d2 and pair are from 00-23, so d1 is 0, 1 or 2
    // dupeD1 if we want to allow d1 d1 as combination
    private static ArrayList<String> getCombos(int d1,int d2){

        ArrayList<String> combos = new ArrayList<>();
        if( d1 == d2 ){
            // one combo - the two digits together.
            combos.add(clockify(d1,d1));

        } else if (d2 > 5) {
            // two combos. d1+d2, d1+d1, can't use second digit in first place as >5
            combos.add(clockify(d1,d1));
            combos.add(clockify(d1,d2));

        } else {
            // four combos. d1+d1, d1+d2, d2+d1, d2+d2
            combos.add(clockify(d1,d1));
            combos.add(clockify(d1,d2));
            combos.add(clockify(d2,d1));
            combos.add(clockify(d2,d2));
        }

        return combos;
    }


    // Given a pair of digits, work out (in order) the valid combinations
    private static ArrayList<String> interesting(String digitPair){

        int[] digits=new int[2];
        ArrayList<String> combos;

        // Need to extract and then order the digits
        if(digitPair.length()==1){
            digits[0]=0;
            digits[1]=Integer.parseInt(digitPair.substring(0,1));
        } else {
            digits[0]=Integer.parseInt(digitPair.substring(0,1));
            digits[1]=Integer.parseInt(digitPair.substring(1,2));
        }

        ArrayList<String> times=new ArrayList<>();

        if (digits[0] != digits[1]) {

            // Get valid combos using our non-matching pair
            if( digits[0] < digits[1]) {
                combos= getCombos(digits[0], digits[1]);
            } else {
                combos= getCombos(digits[1], digits[0]);
            }

            // The same combinations work for minutes as seconds.  So using square
            int numCombos = combos.size();

            // Create full set of meaningful minutes:seconds using the pair of digits
            int n=0;
            for(int i=0; i<numCombos; i++){
                for (int j=0; j<numCombos; j++){
                    times.add(combos.get(i) + ":" + combos.get(j));
                }
            }

        } else {

            // matching digits

            // Add this combination ONCE. And do it BEFORE we look through the digits
            times.add(digitPair+":"+digitPair);

            // Spin through the various pairs of combinations
            for(int d=0; d<=9; d++){
                combos=getCombos(digits[0], d);
                int numCombos = combos.size();
//                for(int i=0;i<numCombos;i++) System.out.println(i + " " + combos.get(i));

                for(int i=0; i<numCombos; i++){
                    for (int j=0; j<numCombos; j++){
                        if( combos.get(i).compareTo(digitPair)==0 && combos.get(j).compareTo(digitPair)==0) {
                            // Avoid re-adding the duplicate digit (dd:dd) again
                            if (DBG) System.out.println("skip " + combos.get(i) + " " +combos.get(j));
                        } else {
                            // Avoid adding dd:dd many, many times !!!
                            if (DBG) System.out.println("-> " + combos.get(i) + ":" + combos.get(j));
                            times.add(combos.get(i) + ":" + combos.get(j));
                        }
                    }
                }
            }
        }
        return times;
    }

    // Check time falls within given bounds
    private static boolean withinBounds(String time, String lBound, String uBound){

        return ( lBound.compareTo(time) < 1 && uBound.compareTo(time) > -1) ;

    }

    // Given two digits, work out the combinations and if they fall within our limits
    public static int byTheHour(int intHour, String lBound, String uBound){

        // Want to be sure to have a double digit hour at some point
        String theHour=Integer.toString(intHour);
        if (theHour.length()<2) theHour="0"+theHour;

        // Get the minutes and seconds that marry up to the hour
        ArrayList<String> combos =  interesting(theHour);

        int n = 0;
        // Do we care about the limits this time?  If it's 03:00 - 23:00 and this hour is 12 then all a bit pointless
        if( getIntHour(lBound)==intHour ||
            getIntHour(uBound)==intHour ) {

            // How many fall within given limits
            boolean ok;

            for (int i = 0; i < combos.size(); i++) {
                ok =withinBounds(theHour + ":" + combos.get(i), lBound, uBound);
                if (ok) n++;
                if (DBG) System.out.println(theHour + ":" + combos.get(i) + " " + ok);
            }
        } else {

            // All in scope
            if (DBG) for (int i = 0; i < combos.size(); i++) System.out.println(theHour + ":" + combos.get(i) + " obviously ");

            n = combos.size();
        }

        return n;
    }


    // Get start and end times
    public static int theTimes(String lBound, String uBound){

        int num=0;

        // Start and end hours ...
        for(int hour = getIntHour(lBound); hour <= getIntHour(uBound); hour++){
            num += byTheHour(hour, lBound, uBound);
//            System.out.println(num);
        }

        return num;
    }

    // Very basic test launch site
    public static void main(String[] args) {

        ArrayList<String> test2=new ArrayList<>();
        ArrayList<String> test1=new ArrayList<>();
        test1.add("first");
        test2.add("second");
        test1.addAll(test2);
        for(int i=0;i<test1.size();i++){
            System.out.println(test1.get(i));
        }


       theTimes( "00:00:00", "00:59:59");
    }
}
