package com.algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testSmallSet() {
        ClosestPair.Point[] points = {
            new ClosestPair.Point(2, 3),
            new ClosestPair.Point(12, 30),
            new ClosestPair.Point(40, 50),
            new ClosestPair.Point(5, 1),
            new ClosestPair.Point(12, 10),
            new ClosestPair.Point(3, 4)
        };

        double result = ClosestPair.closest(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testHorizontalPoints() {
        ClosestPair.Point[] points = {
            new ClosestPair.Point(0, 0),
            new ClosestPair.Point(1, 0),
            new ClosestPair.Point(5, 0),
            new ClosestPair.Point(10, 0)
        };

        double result = ClosestPair.closest(points);
        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testVerticalPoints() {
        ClosestPair.Point[] points = {
            new ClosestPair.Point(0, 0),
            new ClosestPair.Point(0, 2),
            new ClosestPair.Point(0, 5),
            new ClosestPair.Point(0, 7)
        };

        double result = ClosestPair.closest(points);
        assertEquals(2.0, result, 1e-6);
    }

    @Test
    void testDiagonalPoints() {
        ClosestPair.Point[] points = {
            new ClosestPair.Point(0, 0),
            new ClosestPair.Point(1, 1),
            new ClosestPair.Point(2, 2),
            new ClosestPair.Point(5, 5)
        };

        double result = ClosestPair.closest(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testSinglePair() {
        ClosestPair.Point[] points = {
            new ClosestPair.Point(3, 4),
            new ClosestPair.Point(7, 1)
        };

        double result = ClosestPair.closest(points);
        assertEquals(5.0, result, 1e-6);
    }
}
