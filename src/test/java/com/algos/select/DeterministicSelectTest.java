package com.algos.select;

import com.algos.util.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(arr.clone(), k, m);

        Arrays.sort(arr);
        assertEquals(arr[k - 1], result);
    }

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 5; t++) {
            int[] arr = rand.ints(50, -500, 500).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rand.nextInt(arr.length) + 1;
            Metrics m = new Metrics();
            int result = DeterministicSelect.select(arr.clone(), k, m);

            assertEquals(sorted[k - 1], result);
        }
    }
}
