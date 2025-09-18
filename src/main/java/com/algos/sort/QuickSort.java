package com.algos.sort;

import com.algos.util.ArrayUtils;
import com.algos.util.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] a, Metrics m) {
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            int pivotIndex = lo + random.nextInt(hi - lo + 1);
            ArrayUtils.swap(a, pivotIndex, hi);
            m.incSwaps();

            m.enterRecursion();
            int p = ArrayUtils.partition(a, lo, hi, m);
            m.exitRecursion();
            if (p - lo < hi - p) {
                quicksort(a, lo, p - 1, m);
                lo = p + 1;
            } else {
                quicksort(a, p + 1, hi, m);
                hi = p - 1;
            }
        }

    }
}
