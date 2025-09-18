package com.algos.util;

import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();
  
    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(a, i, j);
        }
    }
    public static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            m.incComparisons();
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
                m.incSwaps();
            }
        }
        swap(a, i + 1, hi);
        m.incSwaps();
        return i + 1;
    }
}
