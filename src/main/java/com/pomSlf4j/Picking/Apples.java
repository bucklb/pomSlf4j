package com.pomSlf4j.Picking;


// Picking apples optimally

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Apples {

    boolean dbg=true;

    // How many apples can be picked from n trees
    private int[] treeCount(int[] treeApples, int n){
        int N=treeApples.length;
        int apples[]=new int[N];

        // Set count for first tree (tree zero)
        for(int i=0;i<n;i++) {
            apples[0] += treeApples[i];
        }

        // And the other trees can be based on initial count
        for(int j=0;j<N-n;j++){
            apples[j+1]=apples[j] - treeApples[j] + treeApples[j+n];
        }

        return apples;
    }

    // Maximum apples that can be picked from n trees
    private int[] treeMax(int[] treeCount, int n){
        int N=treeCount.length;
        int maxTree[]=new int[N];

        // Work from end ....  Think if stream would be way to go ....
        int maxApples=0;
        for(int i=N-n; i>=0; i--){
            maxApples=max(maxApples,treeCount[i]);
            maxTree[i]=maxApples;
        }

        return maxTree;
    }



    public long solve(int[] treeApples, int K, int L){
        // Looking to avoid iterating through EVERYTHING, but may want to drop back to hard way
        boolean newWay=true;
        int N=treeApples.length;

        // If there's no point then duck out
        if(N<(L+K)) {
            return -1;
        } else {
            // We have some intervals to play with
            // - special case of L+K=N could be covered ???

            // For each tree, what's the total over the next "n" trees, including this one
            int[] pickerTrees={K,L,K+L};
            int[][] apples=new int[2][];
            apples[0]=treeCount(treeApples,pickerTrees[0]);
            apples[1]=treeCount(treeApples,pickerTrees[1]);
            if(dbg) for(int i=0; i<N;i++) System.out.println(i + " : " + apples[0][i] + " : " +apples[1][i] );

            // Work out the maximum apples a picker can get by choice of start trre
            int[][] maxApples=new int[2][];
            maxApples[0]=treeMax(apples[0],pickerTrees[0]);
            maxApples[1]=treeMax(apples[1],pickerTrees[1]);
            if(dbg) for(int i=0; i<N;i++) System.out.println(i + " : " + maxApples[0][i] + " : " +maxApples[1][i] );

            // Now time to mix and match ?!?  Start with picker 0 then 1 (and then picker 1 gets first dibs, picker 0 second)
            long thisPick=0;
            long bestPick=0;
            long maxPick =0;
            for(int i=0; i<=1;i++){
                int thisPicker=i;
                int nextPicker=abs(i-1);

                // only iterate over trees where we can get both pickers enough room
                for(int j=0;j<=N-pickerTrees[2];j++){
                    // bunch of trees are already taken, what about the rest.
                    if(newWay){
                        // A set of trees are selected for a picker
                        // so what's the best the other picker can manage from trees left?
                        // This picker
                        bestPick = apples[thisPicker][j];

                        // best the other picker can manage
                        bestPick += maxApples[nextPicker][j+pickerTrees[thisPicker]];

                        System.out.println("Picker : " + thisPicker + ", tree : " + j + ", pick : " +bestPick);

                        // Have we achieved optimal already ??  If so we could just stop
                        if(bestPick == maxApples[thisPicker][0]+maxApples[nextPicker][0]){
                            System.out.println("Found OPTIMAL pick of : " + bestPick);
                        }

                        maxPick=max(maxPick,bestPick);

                    } else {
                        // spin through every combination
                        for(int k=j+pickerTrees[i];k<=N-pickerTrees[abs(i-1)];k++){
                            thisPick=apples[i][j]
                                    +apples[abs(i-1)][k];

                            if (dbg) System.out.println(i + " : " + apples[i][j] + " " + j + " : " + k + apples[abs(i-1)][k] + " >> " + thisPick);
                            if (thisPick>maxPick) maxPick=thisPick;
                        }
                    }
                }
            }
            return maxPick;
        }

    }




}
