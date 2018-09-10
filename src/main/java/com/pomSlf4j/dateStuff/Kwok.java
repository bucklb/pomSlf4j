package com.pomSlf4j.dateStuff;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kelvi on 14/08/2018.
 */
public class Kwok {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String MEETING_ENTRY_REGEX = "^([A-Z][a-z]{2}) ([0-9]{2}):([0-9]{2})-([0-9]{2}):([0-9]{2})";
    private static final Pattern pattern = Pattern.compile(MEETING_ENTRY_REGEX);
    private static final String START_DAY = "Mon";
    private static final String END_DAY = "Sun";

    private Map<String, LocalDateTime> templateDates = new HashMap<>();
    private List<Meeting> meetings = new ArrayList<>();





    public int solution(String S) {

        templateDates = new HashMap<>();
        meetings = new ArrayList<>();


        // write your code in Java SE 8
        setupTemplateDates();

        populateMeetings(S);

        sortMeetings();

        Long longestGap = getLongestGap();

        return longestGap.intValue();
    }

    private void setupTemplateDates() {
        templateDates.put("Mon", LocalDateTime.of(2018, 8, 13, 0, 0, 0));
        templateDates.put("Tue", LocalDateTime.of(2018, 8, 14, 0, 0, 0));
        templateDates.put("Wed", LocalDateTime.of(2018, 8, 15, 0, 0, 0));
        templateDates.put("Thu", LocalDateTime.of(2018, 8, 16, 0, 0, 0));
        templateDates.put("Fri", LocalDateTime.of(2018, 8, 17, 0, 0, 0));
        templateDates.put("Sat", LocalDateTime.of(2018, 8, 18, 0, 0, 0));
        templateDates.put("Sun", LocalDateTime.of(2018, 8, 19, 0, 0, 0));
    }

    private void populateMeetings(String S) {
//        String[] times = S.split(NEWLINE);
        String[] times = S.split("\n");
        for (String time : times) {

            Matcher matcher = pattern.matcher(time);
            if (matcher.find()) {
                String dayOfWeek = matcher.group(1);
                int startTimeHour = Integer.parseInt(matcher.group(2));
                int startTimeMinute = Integer.parseInt(matcher.group(3));
                int endTimeHour = Integer.parseInt(matcher.group(4));
                int endTimeMinute = Integer.parseInt(matcher.group(5));

                LocalDateTime meetingDateStart = templateDates.get(dayOfWeek).plusHours(startTimeHour).plusMinutes(startTimeMinute);
                LocalDateTime meetingDateEnd = templateDates.get(dayOfWeek).plusHours(endTimeHour).plusMinutes(endTimeMinute);

                Meeting meeting = new Meeting(meetingDateStart, meetingDateEnd);
                meetings.add(meeting);
            }
        }
    }

    private void listMeetings(){
        for(Meeting meeting:meetings){
            System.out.println(meeting.startDateTime + " " + meeting.getEndDateTime());
        }
    }



    private void sortMeetings() {
        Collections.sort(meetings, Comparator.comparing(Meeting::getStartDateTime));
        //listMeetings();
    }

    private long getLongestGap() {
        LocalDateTime previousMeetingEndDateTime = templateDates.get(START_DAY);
        long longestGap = 0L;
        for (Meeting currentMeeting : meetings) {
            longestGap = measureGap(previousMeetingEndDateTime, currentMeeting.getStartDateTime(), longestGap);
            previousMeetingEndDateTime = currentMeeting.getEndDateTime();
        }
        longestGap = measureGap(previousMeetingEndDateTime, templateDates.get(END_DAY).plusDays(1), longestGap);
        return longestGap;
    }

    private long measureGap(LocalDateTime previousEndTime, LocalDateTime startTime, Long longestGap) {
        long gap = Duration.between(previousEndTime, startTime).toMinutes();
        if (gap > longestGap) {
            longestGap = gap;
        }
        return longestGap;
    }

    class Meeting {
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;

        public Meeting(LocalDateTime startDateTime, LocalDateTime endDateTime) {
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
        }

        public LocalDateTime getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(LocalDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }
    }

    // Outsi
    public void runTests(){

        System.out.println(solution("")
                + " " + 10080);
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
        System.out.println(solution("")
                + " " + 10080);
    }



}