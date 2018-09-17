package com.pomSlf4j.Picking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;

// Improve?
public class Pears {

    boolean DBG=true;                           // Control verbosity (without importing logging)
    static final long DIVISOR = 1000000007;     // Pass back values that are modulo'd with this

    // Get list of picks given number of trees someone can pick
    private List<Pick> getPickList(int[] A, int n){
        List<Pick> thePickList =new ArrayList<>();

        // calculate first yield via stream - EXCEPT it seems to work on basis of int mathematics (as array is int) and breaks
//        long yield = Arrays.stream(A, 0, n).sum();
        long yield = 0;
        for ( int i = 0; i < n; i++) yield += A[i];
        thePickList.add(new Pick(0,n,yield));

        // other yields by moving along the set
        for ( int i = 0; i < A.length - n; i++){
            yield = yield - A[i] + A[i+n];
            thePickList.add( new Pick(i+1, n, yield) );
        }

        return thePickList;
    }

    private void showPickList( List<Pick> thePickList){
        for( Pick p: thePickList){
            System.out.println(p.first + " - " + p.last + " >> " + p.yield);
        }
    }


    // Optimise the yields
    public int solution(int[] A, int K, int L) {

        int  N = A.length;
        long maxPick=0;

        // Block obvious routes.  Do we need to worry about total trees and INT?
        if( N <  ( K + L ) ) return -1;
        if( N == ( K + L ) ) {
            System.out.println("Exact match !!!!");
            return (int)(Arrays.stream(A).sum() % DIVISOR);
        }

        // Get the yields for first picker
        List<Pick> x=getPickList( A, K);
        Collections.sort( x, Collections.reverseOrder() );

        showPickList(x);

        // If second picker has same pick length then do NOT need to calculate the SAME pick list again!!
        List<Pick> y;
        if ( K==L ) {
            y=x;
        } else {
            y = getPickList(A, L);
            Collections.sort(y, Collections.reverseOrder());
        }

        // If we find ANY combination that allows both pickers to peak pick then no point proceeding
        long peakPick = x.get(0).yield + y.get(0).yield;

        // Work through list 1 and for each element find the first NON-conflicting pick from the second picker
        // which will be the best that can be managed given pick 1
        long thisPick=0;
        for(Pick p:x){
            for(Pick q:y){
                if( p.isDiscreteFrom( q )) {
                    // No overlap.  Best match, given we have chosen p
                    thisPick = p.yield + q.yield;
                    break;
                }
            }
            maxPick = max( thisPick, maxPick );
            if( maxPick == peakPick ) {
                // Can't do better, so stop trying.
                System.out.println("Hit peak pick !!!");
                break;
            }
        }

        System.out.println(maxPick);

        return (int)(maxPick % DIVISOR);
    }



    private class Pick implements Comparable{

        int first;  // starting tree (index)
        int last;   // ending tree (index)
        Long yield; // fruit

        public Pick(int start, int length, long yield){
            this.first = start + 1;
            this.last  = start + length;
            this.yield = yield;
        }

        public long getYield() {
            return yield;
        }

        public int getFirst() {
            return first;
        }

        public int getLast() {
            return last;
        }

        // If this pick and the passed pick show no overlap then they are discrete
        public boolean isDiscreteFrom(Pick that) {
            return ( that.last < this.first ) || (that.first > this.last);
        }

        @Override
        public int compareTo(Object o) {
            return this.yield.compareTo(((Pick)o).getYield());
        }
    }
}
