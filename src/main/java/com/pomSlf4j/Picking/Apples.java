package com.pomSlf4j.Picking;

import java.util.Arrays;
import static java.lang.Math.abs;
import static java.lang.Math.max;

// Picking apples optimally
public class Apples {

    boolean DBG=true;                           // Control verbosity (without importing logging)
    static final long DIVISOR = 1000000007;     // Pass back values that are modulo'd with this

    // How many apples can be picked from each set of n trees (n is a function of the picker)
    private long[] calculateApplePickByTree(int[] treeApples, int n){
        int N=treeApples.length;
        long apples[]=new long[N];

        // Set count for first tree (tree zero).
        for(int  i = 0; i < n; i++) {
            apples[0] += treeApples[i];
        }

        // And the other trees can be based on previous count (drop a tree from the start and add a new tree to the end)
        for(int j = 0; j < N-n; j++){
            apples[j+1] = apples[j] - treeApples[j] + treeApples[j+n];
        }

        return apples;
    }

    // Maximum apples that can be picked starting from any tree onward (including the tree)
    // Pass in array of possible pick counts starting from each tree.
    // Passing in n just saves some unnecessary calculation
    private long[] calculateMaxApplePickByTree(long[] applePick, int n){

        // Could avoid calculating maximum values at start of tree list (as well as at the end)
        int N = applePick.length;
        long maxApplePick[] = new long[N];

        // Work from end .... tree zero will be the maximum possible
        long maxApples = 0;
        for(int i = N-n; i >= 0; i--){
            maxApples = max(maxApples, applePick[i]);
            maxApplePick[i] = maxApples;
        }

        return maxApplePick;
    }


    // Orchard with people that can pick K & L trees, but not overlapping. Ought to try & be efficient
    public int solve(int[] treeApples, int K, int L){
        // Looking to avoid iterating through EVERYTHING, but may want to drop back to hard way
        boolean newWay = true;
        int numTrees = treeApples.length;

        // Block the sillier options
        if( numTrees < (L+K) ) {
            // Not enough trees to go round. No point trying to play around
            return -1;

        } else if ( numTrees == (L+K) ){
            // Don't get to play much.  Just return the total (and show how we can use a stream)
            return (int)(Arrays.stream(treeApples).sum() % DIVISOR);

        } else {
            // Can we save some effort if the two pickers share the same choice (i.e K==L)?
            // - don't have to go through stuff TWICE !!! Maybe later ...

            int[] numberOfTreesToPick = {K, L, K+L};
            long[][] apples =    new long[2][];
            long[][] maxApples = new long[2][];

            // For each tree, what's the total over the next "n" trees, including this one
            for(int i = 0; i < 2; i++) {
                int picker = i;
                // Work out the apple pick and then the maximum apple pick by choice of start trre
                apples[picker] = calculateApplePickByTree( treeApples, numberOfTreesToPick[picker]);
                maxApples[picker] = calculateMaxApplePickByTree( apples[picker], numberOfTreesToPick[picker]);
            }
            if (DBG) {
                for (int i = 0; i < numTrees; i++) System.out.println(i + " : " + apples[0][i] + " : " + apples[1][i] +
                                                                      " || " + maxApples[0][i] + " : " + maxApples[1][i]);
            }

            // Now time to mix and match ?!?  Start with picker 0 then 1 (and then picker 1 gets first dibs, picker 0 second)
            long thisPick = 0;
            long maxPick  = 0;

            for(int i = 0; i < 2; i++){
                // Try and improve legibility
                int thisPicker  = i;
                int otherPicker = abs(i-1);

                // only iterate over trees where we can get both pickers enough room
                for(int j = 0; j < (numTrees - numberOfTreesToPick[2]); j++){
                    // bunch of trees are already taken, what about the rest.
                    if(newWay){
                        // legibility again ... The "other" picker has to start from BEYOND thisPicker's current selection
                        int thisStartTree  = j;
                        int otherStartTree = j + numberOfTreesToPick[thisPicker];

                        // This picker starts at j.  Other picker can therefore start at "j+numberOfTreesToPick"
                        // Get the best the other picker can manage starting from that position
                        thisPick = apples[thisPicker][thisStartTree];

                        // best the other picker can manage from where they are allowed to start
                        thisPick += maxApples[otherPicker][otherStartTree];

                        if ( DBG ) System.out.println("Picker : " + thisPicker + ", tree : " + thisStartTree + ", pick : " +thisPick);

                        // Have we achieved optimal already ??  If so we could just stop (not hugely elegant though)
                        if(thisPick == maxApples[thisPicker][0]+maxApples[otherPicker][0]){
                            if( DBG ) System.out.println("Found OPTIMAL pick of : " + thisPick);
                            return (int)(thisPick % DIVISOR);
                        }

                        // Update tally
                        maxPick=max( maxPick, thisPick);

                    } else {
                        // spin through every combination
                        for(int k=j+numberOfTreesToPick[thisPicker];k<=numTrees-numberOfTreesToPick[otherPicker];k++){
                            thisPick=apples[thisPicker][j]
                                    +apples[otherPicker][k];

                            if (DBG) System.out.println(i + " : " + apples[thisPicker][j] + " " + j + " : " + k + apples[otherPicker][k] + " >> " + thisPick);
                            if ( thisPick > maxPick) maxPick=thisPick;
                        }
                    }
                }
            }
            System.out.println(maxPick);
            return (int) (maxPick % DIVISOR);
        }
    }
}
