package com.algos.sort;

import com.algos.util.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 10; t++) {
            int[] arr = rand.ints(100, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics m = new Metrics();
            QuickSort.sort(arr, m);

            assertArrayEquals(expected, arr);
        }
    }
}
