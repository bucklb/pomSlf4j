package com.pomSlf4j.Picking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class navinbanerjee {

        public static void function(Integer[] a, int k, int l){
            int totalOfKAndL = 0;
            Integer[] maxAndStartingIndex = getMaxContiguosFromArray(a, k);
            totalOfKAndL+=maxAndStartingIndex[0];
            System.out.println(maxAndStartingIndex[0]);
            Integer[] leadingElements = Arrays.copyOfRange(a, 0, maxAndStartingIndex[1]);
            Integer[] trailingElements = Arrays.copyOfRange(a, (maxAndStartingIndex[1]+k), a.length);
            List<Integer> mergedLeadingAndTrailingElements = new ArrayList(Arrays.asList(leadingElements));
            mergedLeadingAndTrailingElements.addAll(Arrays.asList(trailingElements));
            Integer[] mergedLeadingAndTrailingElementsArray =  mergedLeadingAndTrailingElements.toArray(new Integer[0]);
            maxAndStartingIndex = getMaxContiguosFromArray(mergedLeadingAndTrailingElementsArray, l);
            System.out.println(maxAndStartingIndex[0]);
            totalOfKAndL+=maxAndStartingIndex[0];
            System.out.println(totalOfKAndL);
        }
        static Integer[] getMaxContiguosFromArray(Integer[] a, int numberOrElements){
            int sumForFirst = 0;
            int sumForFirstPrev = 0;
            int startingIndexForK = 0;
            Integer[] maxAndStartingIndex = new Integer[2];
            for(int i = 0; i < a.length; i++){
                if(i <= a.length - numberOrElements) {
                    for (int j = i; j < i + numberOrElements; j++) {
                        sumForFirst += a[j];
                    }
                }
                if(sumForFirst >  sumForFirstPrev) {
                    sumForFirstPrev = sumForFirst;
                    startingIndexForK = i;
                }
                sumForFirst = 0;
            }
            maxAndStartingIndex[0] = sumForFirstPrev;
            maxAndStartingIndex[1] = startingIndexForK;
            return maxAndStartingIndex;
        }
        public static void main(String[] args) {

            // Stuff that breaks his approach.

            Integer a[] = {1,11,498,498,1,1,7,4,250,250,250,250};
            function(a, 4,2);

            Integer b[] = {1,1,40,10,100,20,20,100,10,40,1,1,1};
            function(b, 4,2);



        }


}