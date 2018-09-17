package com.pomSlf4j;

import com.pomSlf4j.Picking.Pears;
import org.junit.Assert;
import org.junit.Test;

public class PearTest {

    private int getSolution(int[] A, int K, int L){
        Pears pear = new Pears();
        return pear.solution(A,K,L);
    }

    @Test
    public void checkNoRoom(){
        int[] trees={6,1,4,6,3,2,7,4};
        Assert.assertEquals(-1, getSolution( trees,99,2 ));
    }

    @Test
    public void useGivenCase(){
        int[] trees={6,1,4,6,3,2,7,4};
        Assert.assertEquals(24, getSolution( trees,3,2 ));
    }

    // Look for early exit (on basis of optimal found soonish)
    @Test
    public void useRepetiveTrees(){
        int[] trees={2,1,4,6,3,2,7,4,2,1,4,6,3,2,7,4,2,1,4,6,3,2,7,4};
        Assert.assertEquals(24, getSolution( trees,3,2 ));
    }

    @Test
    public void useGivenCaseReversed(){
        int[] trees={4,7,2,3,6,4,1,6};
        Assert.assertEquals(24, getSolution( trees,3,2 ));
    }

    @Test
    // Ought to implement (and test) an early exit case when a maximum possible count is found
    public void useGivenCaseFullyReversed(){
        int[] trees={4,7,2,3,6,4,1,6};
        Assert.assertEquals(24, getSolution( trees,2,3 ));
    }

    // not a lot of point doing much if we need to pick ALL the trees
    // - could look at where there's only one tree to be left unpicked (no more than 4 options to check)
    @Test
    public void allTreesPicked(){
        int[] trees={6,1,4,999,3,2,7,4};
        Assert.assertEquals(1026, getSolution( trees,3,trees.length-3 ));
    }

    @Test
    public void oneBigTree(){
        int[] trees={6,1,4,999,3,2,7,4};
        Assert.assertEquals(1017, getSolution( trees,3,2));
    }

    @Test
    public void forceOnePickerNonOptimal(){
        int[] trees={11,498,498,1,1,7,4,250,250,250,250};
        Assert.assertEquals(1996, getSolution( trees,4,2));
    }

    @Test
    public void forceBothPickerNonOptimal(){
        int[] trees={1,1,40,10,100,20,20,100,10,40,1,1,1};
        Assert.assertEquals(340, getSolution( trees,4,4));
    }

    // Description threatens huge numbers.  Check we won't break with a few MAX_INT's
    @Test
    public void goLarge() {
        long DIVISOR = 1000000007;     // Pass back values that are modulo'd with this
        int[] trees={Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,10,100,20,20,100,10,40,1,1,1};
        long exp= (250 + 3* (long)Integer.MAX_VALUE) % DIVISOR;
        Assert.assertEquals(exp, getSolution( trees,4,4));
    }
}
