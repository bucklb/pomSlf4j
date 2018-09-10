package com.pomSlf4j.dateStuff;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class DateStuffToo {

    private static boolean dbg=false;
    private static String LINE_BREAK="\n";
    private static String TERMINATOR="Sun 24:00-24:00";
    private static String DOW_FROM_TIMES=" ";
    private static String TIME_FROM_TIME="-";
    private static String HOUR_FROM_MINS=":";

    private static LocalDateTime baseDate=LocalDateTime.of(2018, 8, 13, 0, 0, 0);


    private HashMap<String,Integer> dowMap; //=new HashMap<>();
    private List<Meeting> meetings; // = new ArrayList<>();

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

    // Going to be dealing with many meetings
    private class Meeting {
        LocalDateTime startTime;
        LocalDateTime endTime;

        private LocalDateTime getMeetingDateTime(int dowNumber,String theTime){
            String[] timeParts=theTime.split(HOUR_FROM_MINS);
            return baseDate.plusDays(dowNumber)
                           .plusHours(Integer.parseInt(timeParts[0]))
                           .plusMinutes(Integer.parseInt(timeParts[1]));
        }

        // Construct from the String snippet
        public Meeting(String meetingString){

            if (dbg) System.out.println("Meeting Constructor with " + meetingString);

            // Get the day stuff out and usable (i.e as an integer)
            String[] parts = meetingString.split(DOW_FROM_TIMES);
            int dowNumber = dowMap.get(parts[0].toUpperCase());

            // Get times from the rest of the string
            String[] theTimes = parts[1].split(TIME_FROM_TIME);

            startTime=getMeetingDateTime(dowNumber,theTimes[0]);
            endTime  =getMeetingDateTime(dowNumber,theTimes[1]);
        }

        public LocalDateTime getStartDateTime() {
            return startTime;
        }
        public LocalDateTime getEndDateTime() {
            return endTime;
        }

    }

    // Create list of meetings from the string
    private void createMeetingsFromString(String meetingsString){
        // Assume we need a fresh start
        meetings = new ArrayList<>();

        // split in to hours and minutes and then use to crete Meeting object
        String[] meetingTimes=meetingsString.split(LINE_BREAK);
        for(String meetingTime:meetingTimes){
            Meeting m=new Meeting(meetingTime);
            meetings.add(m);
            if(dbg) System.out.println(m.startTime + " " + m.endTime);
        }

        // Might as well sort them too while we're here
        Collections.sort(meetings, Comparator.comparing(Meeting::getStartDateTime));
    }

    private void listMeetings(){
        for(Meeting m:meetings){
            System.out.println(m.getStartDateTime()+" to "+m.getEndDateTime());
        }
    }

    private long getLongestGap(){

        // Add a terminator meeting (rather than have "after last meeting" finagle
        meetings.add(new Meeting(TERMINATOR));

        long maxGap=0;
        long gap=0;
        LocalDateTime lastEndDateTime=baseDate;

        for(Meeting m:meetings){
            gap = Duration.between(lastEndDateTime,m.getStartDateTime()).toMinutes();
            if (gap>maxGap) maxGap=gap;

            lastEndDateTime=m.getEndDateTime();
        }

        return maxGap;
    }



    public long play(String meetingsString){

        if(meetingsString.isEmpty()) return 7*24*60;

        // dow map needs to exist
        createDowMap();

        // Get meetings in decent format
        createMeetingsFromString(meetingsString);

        // Progress report
        if(dbg)listMeetings();

        return getLongestGap();
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
