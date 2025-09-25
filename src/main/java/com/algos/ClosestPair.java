package com.algos;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double dist(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                         (p1.y - p2.y) * (p1.y - p2.y));
    }

    private static double bruteForce(Point[] P, int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, dist(P[i], P[j]));
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        Arrays.sort(strip, 0, size, Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; j++) {
                min = Math.min(min, dist(strip[i], strip[j]));
            }
        }
        return min;
    }

    private static double closestUtil(Point[] Px, Point[] Py, int n) {
        if (n <= 3) return bruteForce(Px, n);

        int mid = n / 2;
        Point midPoint = Px[mid];

        Point[] Pyl = new Point[mid];
        Point[] Pyr = new Point[n - mid];
        int li = 0, ri = 0;

        for (int i = 0; i < n; i++) {
            if (Py[i].x <= midPoint.x) Pyl[li++] = Py[i];
            else Pyr[ri++] = Py[i];
        }

        double dl = closestUtil(Arrays.copyOfRange(Px, 0, mid), Pyl, mid);
        double dr = closestUtil(Arrays.copyOfRange(Px, mid, n), Pyr, n - mid);

        double d = Math.min(dl, dr);

        Point[] strip = new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(Py[i].x - midPoint.x) < d)
                strip[j++] = Py[i];
        }

        return Math.min(d, stripClosest(strip, j, d));
    }

    public static double closest(Point[] points) {
        int n = points.length;
        Point[] Px = Arrays.copyOf(points, n);
        Point[] Py = Arrays.copyOf(points, n);

        Arrays.sort(Px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(Py, Comparator.comparingDouble(p -> p.y));

        return closestUtil(Px, Py, n);
    }
}
