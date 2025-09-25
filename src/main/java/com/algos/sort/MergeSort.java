package com.algos.sort;

import com.algos.util.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        int[] buffer = new int[a.length];
        m.incAllocations();
        sort(a, buffer, 0, a.length - 1, m);
    }

    private static void sort(int[] a, int[] buffer, int lo, int hi, Metrics m) {
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(a, lo, hi, m);
            return;
        }

        int mid = lo + (hi - lo) / 2;

        m.enterRecursion();
        sort(a, buffer, lo, mid, m);
        sort(a, buffer, mid + 1, hi, m);
        m.exitRecursion();

        merge(a, buffer, lo, mid, hi, m);
    }

    private static void merge(int[] a, int[] buffer, int lo, int mid, int hi, Metrics m) {
        for (int i = lo; i <= mid; i++) {
            buffer[i] = a[i];
        }

        int i = lo;
        int j = mid + 1;
        int k = lo;

        while (i <= mid && j <= hi) {
            m.incComparisons(1);
            if (buffer[i] <= a[j]) {
                a[k++] = buffer[i++];
            } else {
                a[k++] = a[j++];
            }
        }
        while (i <= mid) {
            a[k++] = buffer[i++];
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.incComparisons(1);
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    m.incSwaps();
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}
