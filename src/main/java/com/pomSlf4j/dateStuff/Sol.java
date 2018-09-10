package com.pomSlf4j.dateStuff;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Created by admin on 02/08/2018.
 */
public class Sol {
    //pick any monday as start time
    private static final LocalDateTime startTime = LocalDateTime.of(2018, Month.AUGUST,13 , 0, 0, 0);
    private static final LocalDateTime endTime = LocalDateTime.of(2018, Month.AUGUST,20 , 0, 0, 0);

    public int solution(String S) {
//        String split[] = S.split(System.getProperty("line.separator"));
        String split[] = S.split("\n");
        List<Meeting> meetings = new ArrayList<>();

        for (String s: split) {
            Meeting m = new Meeting();
            m.setStartTime(getDateTimeFromString(s.substring(0,3), s.substring(4,9)));
            m.setEndTime(getDateTimeFromString(s.substring(0,3), s.substring(10)));
            meetings.add(m);
        }

        Collections.sort(meetings,new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if(o1.getStartTime().isBefore(o2.getStartTime())){
                    return -1;
                }
                return 1;
            }
        });

        //work out gap between initial monday 00:00 and earliest start time
        int gap = (int) ChronoUnit.MINUTES.between(startTime,meetings.get(0).getStartTime());

        //find biggest gap between end times and next start time
        //size - 1 because you want to compare the last end time with the end time of week and not against any start time
        for (int i = 0; i < meetings.size() - 1; i++) {
            int gap2 = (int) ChronoUnit.MINUTES.between(meetings.get(i).getEndTime(),meetings.get( i + 1).getStartTime());
            if (gap2 > gap) {
                gap = gap2;
            }
        }

        //find biggest gap between the final end time and sun 24:00
        int endGap = (int) ChronoUnit.MINUTES.between(meetings.get(meetings.size() - 1).getEndTime(),endTime);
        if (endGap > gap) {
            gap = endGap;
        }
        return gap;
    }
    private LocalDateTime getDateTimeFromString(String day, String time) {
        String hr = time.substring(0,2);
        int hour = Integer.parseInt(hr);
        int minutes = Integer.parseInt(time.substring(3,5));
        if (day.equals("Mon")) {
            return startTime.plusHours(hour).plusMinutes(minutes);
        } else if (day.equals("Tue")) {
            return startTime.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).plusHours(hour).plusMinutes(minutes);
        } else if (day.equals("Wed")) {
            return startTime.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)).plusHours(hour).plusMinutes(minutes);
        } else if (day.equals("Thu")) {
            return startTime.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).plusHours(hour).plusMinutes(minutes);
        } else if (day.equals("Fri")) {
            return startTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).plusHours(hour).plusMinutes(minutes);
        } else if (day.equals("Sat")) {
            return startTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).plusHours(hour).plusMinutes(minutes);
        }  else {
            return startTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).plusHours(hour).plusMinutes(minutes);
        }
    }
    class Meeting {
        LocalDateTime startTime;
        LocalDateTime endTime;
        public LocalDateTime getStartTime() {
            return startTime;
        }
        public LocalDateTime getEndTime() {
            return endTime;
        }
        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }
        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
    }
    // Outsi
    public void runTests(){

//        System.out.println(solution("")
//                + " " + 10080);
        System.out.println(solution("Sun 10:00-20:00\nFri 05:00-10:00\nFri 16:30-23:50\nSat 10:00-24:00\nSun 01:00-04:00\nSat 02:00-06:00\nTue 03:30-18:15\nTue 19:00-20:00\nWed 04:25-15:14\nWed 15:14-22:40\nThu 00:00-23:59\nMon 05:00-13:00\nMon 15:00-21:00")
                + " " + 505);
        System.out.println(solution("Mon 01:00-23:00\nTue 01:00-23:00\nWed 01:00-23:00\nThu 01:00-23:00\nFri 01:00-23:00\nSat 01:00-23:00\nSun 01:00-21:00")
                + " " + 180);
        System.out.println(solution("Mon 12:00-13:00\nTue 12:00-13:00\nWed 12:00-13:00\nThu 12:00-13:00\nFri 12:00-13:00\nSat 12:00-13:00\nSun 12:00-13:00")
                + " " + 1380);
        System.out.println(solution("Mon 00:00-24:00\nSun 00:00-24:00")
                + " " + 7200);
        System.out.println(solution("Tue 00:00-24:00")
                + " " + 7200);
        System.out.println(solution("Mon 00:00-06:00\nMon 12:00-18:00\nTue 00:00-06:00\nTue 12:00-18:00\nWed 00:00-06:00\nWed 12:00-18:00\nThu 00:00-06:00\nThu 12:00-18:00\nFri 00:00-06:00\nFri 12:00-18:00\nSat 00:00-06:00\nSat 12:00-18:00\nSun 00:00-06:00\nSun 12:00-18:00")
                + " " + 360);
        System.out.println(solution("Mon 00:00-06:00\nMon 06:00-12:00\nMon 12:00-18:00\nMon 18:00-24:00\nTue 00:00-06:00\nTue 06:00-12:00\nTue 12:00-18:00\nTue 18:00-24:00\nWed 00:00-06:00\nWed 06:00-12:00\nWed 12:00-18:00\nWed 18:00-24:00\nThu 00:00-06:00\nThu 06:00-12:00\nThu 12:00-18:00\nThu 18:00-24:00\nFri 00:00-06:00\nFri 06:00-12:00\nFri 12:00-18:00\nFri 18:00-24:00\nSat 00:00-06:00\nSat 06:00-12:00\nSat 12:00-18:00\nSat 18:00-24:00\nSun 00:00-06:00\nSun 06:00-12:00\nSun 12:00-18:00\nSun 18:00-24:00")
                + " " + 0);
        System.out.println(solution("Mon 00:00-00:01\nMon 23:59-24:00\nTue 00:00-00:01\nTue 23:59-24:00\nWed 00:00-00:01\nWed 23:59-24:00\nThu 00:00-00:01\nThu 23:59-24:00\nFri 00:00-00:01\nFri 23:59-24:00\nSat 00:00-00:01\nSat 23:59-24:00\nSun 00:00-00:01\nSun 23:59-24:00")
                + " " + 1438);
        System.out.println(solution("Sun 23:59-24:00")
                + " " + 10079);
        System.out.println(solution("Mon 13:40-13:45")
                + " " + 9255);
//        System.out.println(solution("")
//                + " " + 10080);
    }
}
