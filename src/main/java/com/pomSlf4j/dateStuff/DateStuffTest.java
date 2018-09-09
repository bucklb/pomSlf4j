package com.pomSlf4j.dateStuff;

import java.util.*;

public class DateStuffTest {

    // Don't need new calendar every time.  Basedate matters little, but makes sense to match DOW in debug ...
    private static Calendar cal = Calendar.getInstance();
    private static Date baseDate=new Date(2018,8,2);
    // Sort the meetings would be nice
    private static TreeMap<Date,Date> sched; //=new TreeMap<>();
    // Turn date string in to something easier to work with
    private static HashMap<String,Integer> dowMap; //=new HashMap<>();

    private static boolean dbg=false;

    // Need to get from day of week ("Mon") to its day of week as number
    // - is there a built in way?
    private void createDowMap(){
        dowMap=new HashMap<>();
        dowMap.put("MON",0);
        dowMap.put("TUE",1);
        dowMap.put("WED",2);
        dowMap.put("THU",3);
        dowMap.put("FRI",4);
        dowMap.put("SAT",5);
        dowMap.put("SUN",6);
    }

    // Want our stuff to relate a common point (even if that common point matters little). Tolerates 24:00 :)
    private Date addToBaseDate(int numDays, int numHours, int numMins){
        // Record our base date and work from it
        cal.setTime(baseDate);

        // Amend it
        cal.add(Calendar.DATE,numDays);
        cal.add(Calendar.HOUR,numHours);
        cal.add(Calendar.MINUTE,numMins);

        // Return the outcome
        return cal.getTime();
    }

    // We'll have split things up already ??
    private Date getMeetingDate(String theDayOfWeek, String theTime){

        if (dbg) System.out.println("getMeetingDate  " + theDayOfWeek + " and " + theTime);

        // split in to hours and minutes
        String[] meetingTime=theTime.split(":");

        // Get day of week as index
        int dow=dowMap.get(theDayOfWeek.toUpperCase());

        // Generate date
        return addToBaseDate(dow,Integer.parseInt(meetingTime[0]),Integer.parseInt(meetingTime[1]));
    }

    // Need the appointments sorted somehow (presumably on when they start)
    // String is in format DoW start-end
    private void addMeeting(String meetingString){

        if (dbg) System.out.println("AddMeeting with " + meetingString);

        // Empty string not hugely useful
        if(!meetingString.isEmpty()) {

            // Get the day stuff out and usable (i.e as an integer)
            String[] first = meetingString.split(" ");
            String dowString = first[0];

            // Get times
            String[] theTimes = first[1].split("-");

            // Add to our list/treemap
            sched.put(getMeetingDate(dowString, theTimes[0]), getMeetingDate(first[0], theTimes[1]));
        }
    }

    // Wouldn't hurt to spin through things and see what's happening
    private void listMeetings(){
        for(Map.Entry<Date,Date> entry: sched.entrySet() ){
            Date start=entry.getKey();
            Date cmplt=entry.getValue();

            // in millisecs
            long delta=cmplt.getTime()-start.getTime();

            // next meeting and a gap
            if (dbg) System.out.println("Begins " + entry.getKey() + "   Ends " + entry.getValue() + " >> duration " + delta/60000);
        }
    }



    public long play(String meetingString){

        if (dbg) System.out.println("Play in DateStuffTest " + meetingString);

        // get mechanism to turn dow$ to dow#
        sched=new TreeMap<>();
        createDowMap();

        // Get the meetings from the string in to a useful form
        String[] meetings=meetingString.split("\n");
        for(String meeting: meetings){
            addMeeting(meeting);
        }

        // add a terminator
        addMeeting("Sun 24:0-0:0");

        // Debug while I suss this ...
        if (dbg) listMeetings();

        Date last=baseDate;
        long maxGap=0;
        long theGap=0;

        // Zip through the real meetings, start from baseDate and end with "dummy" terminator meeting
        for(Map.Entry<Date,Date> entry: sched.entrySet() ){
            Date start=entry.getKey();

            // gap
            theGap=start.getTime()-last.getTime();

            // next meeting and a gap
            if (dbg) System.out.println(entry.getKey() + " " + entry.getValue() + " >> " + theGap/60000);
            last=entry.getValue();
            if (theGap>maxGap) {
                maxGap=theGap;
            }
        }
        return maxGap/60000;
    }

    // Outsi
    public void runTests(){

        System.out.println(play("")
                + " " + 10080);
        System.out.println(play("Sun 10:00-20:00\nFri 05:00-10:00\nFri 16:30-23:50\nSat 10:00-24:00\nSun 01:00-04:00\nSat 02:00-06:00\nTue 03:30-18:15\nTue 19:00-20:00\nWed 04:25-15:14\nWed 15:14-22:40\nThu 00:00-23:59\nMon 05:00-13:00\nMon 15:00-21:00")
                + " " + 505);
        System.out.println(play("Mon 01:00-23:00\nTue 01:00-23:00\nWed 01:00-23:00\nThu 01:00-23:00\nFri 01:00-23:00\nSat 01:00-23:00\nSun 01:00-21:00")
                + " " + 180);
        System.out.println(play("Mon 12:00-13:00\nTue 12:00-13:00\nWed 12:00-13:00\nThu 12:00-13:00\nFri 12:00-13:00\nSat 12:00-13:00\nSun 12:00-13:00")
                + " " + 1380);
        System.out.println(play("Mon 00:00-24:00\nSun 00:00-24:00")
                + " " + 7200);
        System.out.println(play("Tue 00:00-24:00")
                + " " + 7200);
        System.out.println(play("Mon 00:00-06:00\nMon 12:00-18:00\nTue 00:00-06:00\nTue 12:00-18:00\nWed 00:00-06:00\nWed 12:00-18:00\nThu 00:00-06:00\nThu 12:00-18:00\nFri 00:00-06:00\nFri 12:00-18:00\nSat 00:00-06:00\nSat 12:00-18:00\nSun 00:00-06:00\nSun 12:00-18:00")
                + " " + 360);
        System.out.println(play("Mon 00:00-06:00\nMon 06:00-12:00\nMon 12:00-18:00\nMon 18:00-24:00\nTue 00:00-06:00\nTue 06:00-12:00\nTue 12:00-18:00\nTue 18:00-24:00\nWed 00:00-06:00\nWed 06:00-12:00\nWed 12:00-18:00\nWed 18:00-24:00\nThu 00:00-06:00\nThu 06:00-12:00\nThu 12:00-18:00\nThu 18:00-24:00\nFri 00:00-06:00\nFri 06:00-12:00\nFri 12:00-18:00\nFri 18:00-24:00\nSat 00:00-06:00\nSat 06:00-12:00\nSat 12:00-18:00\nSat 18:00-24:00\nSun 00:00-06:00\nSun 06:00-12:00\nSun 12:00-18:00\nSun 18:00-24:00")
                + " " + 0);
        System.out.println(play("Mon 00:00-00:01\nMon 23:59-24:00\nTue 00:00-00:01\nTue 23:59-24:00\nWed 00:00-00:01\nWed 23:59-24:00\nThu 00:00-00:01\nThu 23:59-24:00\nFri 00:00-00:01\nFri 23:59-24:00\nSat 00:00-00:01\nSat 23:59-24:00\nSun 00:00-00:01\nSun 23:59-24:00")
                + " " + 1438);
        System.out.println(play("Sun 23:59-24:00")
                + " " + 10079);
        System.out.println(play("Mon 13:40-13:45")
                + " " + 9255);
        System.out.println(play("")
                + " " + 10080);
    }


}
