package com.pomSlf4j;

import com.pomSlf4j.Picking.Apples;
import com.pomSlf4j.dateStuff.SignOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// Test SignOf which handles interesting times
public class InterestingTest {

    SignOf signOf;

    @Before
    public void createObject(){
        signOf=new SignOf();
    }

    private void genericTest(String lBound,String uBound,int exp){
        int hits=signOf.theTimes(lBound, uBound);
        Assert.assertEquals(exp,hits);
    }

    @Test
    public void NodAndSmile() {
    }

    @Test
    public void checkNoHits(){
        genericTest("23:55:59","23:59:59",0);
    }

    @Test
    public void checkTinyInterval(){
        genericTest("15:15:15","15:15:15",1);
    }

    @Test
    public void checkTinyIntervalToo(){
        genericTest("15:15:15","15:15:15",1);
    }

    // Double digit hours allow an effective wildcard for the second digit in combinations
    @Test
    public void checkDoubleDigitHours(){
        genericTest("00:00:00","00:59:59",88);
        genericTest("11:00:00","12:00:00",88);
        genericTest("22:00:00","23:20:20",88);
    }

    // How many interesting times?
    @Test
    public void checkFullDay(){

        int all=0;
        all +=  3*88; // hours 00,11,22 are special (wildcard effect)
        all += 13*16; // hours 01-05 are 16, as are 10 & 12-15 & 20-21 & 23
        all +=  8*4;  // hours 06-09 are 4, likewise 16-19

        genericTest("00:00:00","23:59:59",all);
    }


        @Test
    public void checkHourly(){
        genericTest("00:00:00","00:59:59",88);
        genericTest("01:00:00","01:59:59",16);
        genericTest("05:00:00","05:59:59",16);

        genericTest("06:00:00","06:59:59",4);
        genericTest("09:00:00","09:59:59",4);

        genericTest("10:00:00","10:59:59",16);
        genericTest("15:00:00","15:59:59",16);

        genericTest("16:00:00","16:59:59",4);
        genericTest("19:00:00","19:59:59",4);

        genericTest("20:00:00","20:59:59",16);
        genericTest("22:00:00","22:59:59",88);
        genericTest("23:00:00","23:59:59",16);

    }


    @Test
    public void legion(){
        genericTest("00:00:00","00:01:00",21);
        genericTest("19:20:21","19:50:51",0);


    }


}
