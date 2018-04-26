package com.pomSlf4j;

//
// Few options as per the codility tests
//

import org.springframework.util.StringUtils;

import java.util.HashMap;



public class Codility {

    // Want to turn "debug" on & off easily
    void showMeDebug(String s){
//        System.out.println(s);
    }
    
    
    // If the string tgt contains any character from lst then return true (and stop)
    private boolean StringContainsAny(String tgt, String lst){
        int i=0;
        boolean found=false;

        while (i < lst.length() && !found) {
            found = tgt.contains(lst.substring(i, i + 1));
            i++;
        }

        return found;
    }


    public int test(int numRows, String seatList){

        // split in to array & splat to screen
        String[] seats=seatList.split(" ");
        for(String s:seats){            System.out.print(s + " : ");        };showMeDebug("");
        int free=3*numRows;

        // For each row, gather all seats sold in that row via hashmap
        HashMap<String,String> hm=new HashMap();
        for(String s:seats){
            String r = s.substring(0,s.length()-1);
            String c = s.substring(s.length()-1,s.length());
            String t = hm.get(r)==null ? "" : hm.get(r);
            hm.put(r,t+c);
        }   // built hm

        // Get each row and its seats
        for(HashMap.Entry<String,String> entry: hm.entrySet()){
            showMeDebug(entry.getKey()+"//"+ entry.getValue());
            String v=entry.getValue();
            if (StringContainsAny(v,"abc")) {
                showMeDebug("Lost left bank (abc)");
                free--;
            };
            if (StringContainsAny(v,"hjk")) {
                showMeDebug("Lost right bank (hjk)");
                free--;
            };
            if (StringContainsAny(v,"ef")){
                showMeDebug("Lost middle bank - mid (ef)");
                free--;
            }else if (StringContainsAny(v,"d") && StringContainsAny(v,"g")){
                showMeDebug("Lost middle bank - ends d+g");
                free--;
            }
        }
        return free;
    }

    public int compactTest(int numRows, String seatList){

//        String seatList="1a 2f 3c 1b 2g 3d 12a 4d 4g 49h";
        // split in to array & splat to screen
        String[] seats=seatList.split(" ");
        for(String s:seats){            System.out.print(s + " : ");        };showMeDebug("");
        int free=3*numRows;

        // For each row, gather all seats sold in that row via hashmap
        HashMap<String,String> hm=new HashMap();
        for(String s:seats){
            String r = s.substring(0,s.length()-1);
            String c = s.substring(s.length()-1,s.length());

            // if there's a key add the seat to its value. If none then use empty string and add to that
            String t=hm.containsKey(r) ? hm.get(r) : "";
            hm.put(r,t.concat(c));
        }   // built hm

        // Get each row and its seats
        for(HashMap.Entry<String,String> entry: hm.entrySet()){
            showMeDebug(entry.getKey()+"//"+ entry.getValue());
            String v=entry.getValue();
            if (!v.matches("[^abc]+")) {
                showMeDebug("Lost  left  bank (abc)");
                free--;
            }
            if (!v.matches("[^hjk]+")) {
                showMeDebug("Lost  right bank (hjk)");
                free--;
            }
            if (StringContainsAny(v,"ef")){
                showMeDebug("Lost middle bank - mid (ef)");
                free--;
            }else if (StringContainsAny(v,"d") && StringContainsAny(v,"g")){
                showMeDebug("Lost middle bank - ends d+g");
                free--;
            }
        }
        return free;
    }

    // Recreate the original dirty approach
    public int arrayTest(int numRows, String seatList){
        int free=3*numRows;
        String[] seats=seatList.split(" ");
        for(String s:seats){            System.out.print(s + " : ");        };showMeDebug("");
        final String DIRTY="x";

        // Set up array of banks of seats.  Make sure we have the room
        String[][] banks=new String[numRows][3];

        // Grab the row & seat index.  Want the row as int this time but map to zero indexed array by -1
        for(String s:seats){
            int r = Integer.parseInt(s.substring(0,s.length()-1))-1;
            String c = s.substring(s.length()-1,s.length());

            // Could look that c is in abcdefghjk & r is between 0 & numRows-1

            // Need to work out which "bank" seats the seat index sits in and if not dirty then lose an option
            if("abc".indexOf(c) > -1        && StringUtils.isEmpty(banks[r][0])) {
                banks[r][0]=DIRTY;
                free--;
            } else if("hjk".indexOf(c) > -1 && StringUtils.isEmpty(banks[r][2])) {
                banks[r][2]=DIRTY;
                free--;
            } else {
                // must be in middle bank (as not in left or right)
                // What is the state of the middle bank?  If it's already DIRTY then move on
                String x=StringUtils.isEmpty(banks[r][1]) ? "" : banks[r][1];
                if (!x.equals(DIRTY)) {

                    if("ef".indexOf(c) > -1  || !x.equals("")   ) {
                        // in middle of middle = instant dirty OR if bank is already tainted then make fully dirty
                        banks[r][1] = DIRTY;
                        free--;
                    } else {
                        // an end seat, so mark that bank is no longer pristine
                        banks[r][1]=c;
                    }
                }
            }
        }


        return free;
    }




}
