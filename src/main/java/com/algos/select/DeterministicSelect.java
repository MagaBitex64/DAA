package com.algos.select;

import com.algos.util.ArrayUtils;
import com.algos.util.Metrics;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics m) {
        return select(arr, 0, arr.length - 1, k, m);
    }

    private static int select(int[] arr, int lo, int hi, int k, Metrics m) {
        while (true) {
            if (lo == hi) {
                return arr[lo];
            }

            int pivotIndex = medianOfMedians(arr, lo, hi, m);


            ArrayUtils.swap(arr, pivotIndex, hi);
            m.incSwaps();
            int p = ArrayUtils.partition(arr, lo, hi, m);

            int rank = p - lo + 1; 

            if (k == rank) {
                return arr[p];
            } else if (k < rank) {
                hi = p - 1;
            } else {
                k = k - rank;
                lo = p + 1;
            }
        }
    }


    private static int medianOfMedians(int[] arr, int lo, int hi, Metrics m) {
        int n = hi - lo + 1;
        if (n <= 5) {
            Arrays.sort(arr, lo, hi + 1);
            m.incComparisons(n * n); 
            return lo + n / 2;
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            Arrays.sort(arr, subLo, subHi + 1);
            m.incComparisons((subHi - subLo + 1) * (subHi - subLo + 1)); 
            int median = subLo + (subHi - subLo) / 2;
            ArrayUtils.swap(arr, lo + i, median);
            m.incSwaps();
        }
      
        return medianOfMedians(arr, lo, lo + numMedians - 1, m);
    }
}
