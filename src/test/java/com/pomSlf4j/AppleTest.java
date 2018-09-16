package com.pomSlf4j;

import com.pomSlf4j.Picking.Apples;
import org.junit.Assert;
import org.junit.Test;

public class AppleTest {

    @Test
    public void NodAndSmile() {
    }

// Assume we can never be given zero picker (for now)
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

    // Look for early exit (on basis of optimal found soonish)
    @Test
    public void useRepetiveTrees(){
        Apples apples=new Apples();
        int[] trees={2,1,4,6,3,2,7,4,2,1,4,6,3,2,7,4,2,1,4,6,3,2,7,4};
        long pick=apples.solve(trees,3,2);
        Assert.assertEquals(24,pick);
    }

    @Test
    public void useGivenCaseReversed(){
        Apples apples=new Apples();
        int[] trees={4,7,2,3,6,4,1,6};
        long pick=apples.solve(trees,3,2);
        Assert.assertEquals(24,pick);
    }

    @Test
    // Ought to implement (and test) an early exit case when a maximum possible count is found
    public void useGivenCaseFullyReversed(){
        Apples apples=new Apples();
        int[] trees={4,7,2,3,6,4,1,6};
        long pick=apples.solve(trees,2,3);
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


    // not a lot of point doing much if we need to pick ALL the trees
    // - could look at where there's only one tree to be left unpicked (no more than 4 options to check)
    @Test
    public void allTreesPicked(){
        Apples apples=new Apples();
        int[] trees={6,1,4,999,3,2,7,4};
        long pick=apples.solve(trees,3,trees.length-3);
        Assert.assertEquals(1026,pick);
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
        int[] trees={11,498,498,1,1,7,4,250,250,250,250};
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
