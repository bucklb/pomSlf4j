package com.pomSlf4j;



import com.pomSlf4j.Picking.Apples;
import org.junit.Assert;
import org.junit.Test;

public class AppleTest {

    @Test
    public void NodAndSmile() {
    }

//    @Test
    public void zeroPicker(){
        Apples apples=new Apples();
        int[] trees={6,1,4,6,3,2,7,4};
        long pick=apples.solve(trees,0,2);
        Assert.assertEquals(11,pick);
    }


    @Test
    public void checkNoRoom(){
        Apples apples=new Apples();
        int[] trees={6,1,4,6,3,2,7,4};
        long pick=apples.solve(trees,99,2);
        Assert.assertEquals(-1,pick);
    }

    @Test
    public void useGivenCase(){
        Apples apples=new Apples();
        int[] trees={6,1,4,6,3,2,7,4};
        long pick=apples.solve(trees,3,2);
        Assert.assertEquals(24,pick);
    }

    // ensure that picker order doesn't matter
    @Test
    public void useGivenCasePickersReversed(){
        Apples apples=new Apples();
        int[] trees={6,1,4,6,3,2,7,4};
        long pick=apples.solve(trees,2,3);
        Assert.assertEquals(24,pick);
    }

    @Test
    public void oneBigTree(){
        Apples apples=new Apples();
        int[] trees={6,1,4,999,3,2,7,4};
        long pick=apples.solve(trees,3,2);
        Assert.assertEquals(1017,pick);
    }

    @Test
    public void forceOnePickerNonOptimal(){
        Apples apples=new Apples();
        int[] trees={1,11,498,498,1,1,7,4,250,250,250,250};
        long pick=apples.solve(trees,4,2);
        Assert.assertEquals(1996,pick);
    }

    @Test
    public void forceBothPickerNonOptimal(){
        Apples apples=new Apples();
        int[] trees={1,1,40,10,100,20,20,100,10,40,1,1,1};
        long pick=apples.solve(trees,4,4);
        Assert.assertEquals(340,pick);
    }

}
