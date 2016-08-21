package mjq.cwru;

import java.util.*;

public class Main {

    public static void main(String[] args) {
    }

    public static void exch(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    public static void shuffle(Object[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int r = new Random().nextInt(N);
            exch(a, i, r);
        }
    }

    // Merge sort with optimized
    public static class MergeSort {

        private static void Merge(Comparable[] c, Comparable[] aux, int lo, int mid, int hi) {
            for (int k = lo; k <= hi; k++)
                aux[k] = c[k];

            int i = lo;
            int j = mid + 1;

            for (int k = lo; k < hi; k++) {
                if (i > mid)
                    c[k] = aux[j++];
                else if (j > hi)
                    c[k] = aux[i++];
                else if (aux[j].compareTo(aux[i]) < 0)
                    c[k] = aux[j++];
                else c[k] = aux[i++];
            }
        }

        private static void sort(Comparable[] c, Comparable[] aux, int lo, int hi) {
            if (lo >= hi)
                return;

            int mid = lo + (hi - lo) / 2;

            sort(c, aux, lo, mid);
            sort(c, aux, mid + 1, hi);
            if (c[mid].compareTo(c[mid + 1]) < 0)
                //if this condition is satisfied, it means sub arrays are sorted
                return;
            Merge(c, aux, lo, mid, hi);
            return;

        }

        public void sort(Comparable[] c) {
            Comparable[] aux = new Comparable[c.length];
            sort(c, aux, 0, c.length - 1);
        }
    }


    public static class QSort {

        private static int partition(Comparable[] a, int lo, int hi) {
            int i = lo, j = hi + 1;
            while (true) {
                while (a[lo].compareTo(a[--j]) < 0) {
                    if (j == lo)
                        break;
                }

                while (a[lo].compareTo(a[++i]) > 0) {
                    if (i == hi)
                        break;
                }
                if (i >= j)
                    break;
                exch(a, i, j);
            }

            exch(a, lo, j);
            return j;
        }

        private static void sort(Comparable[] a, int lo, int hi) {
            if (lo >= hi)
                return;
            int j = partition(a, lo, hi);
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }

        public static void sort(Comparable[] a) {
            //make sure the array is shuffled
            shuffle(a);
            sort(a, 0, a.length - 1);
        }
    }

    //Given an array of N items, find the kth largest
    public static class QuickSelection {

        private static int partition(Comparable[] a, int lo, int hi) {
            int i = lo, j = hi + 1;
            while (true) {
                while (a[lo].compareTo(a[--j]) < 0) {
                    if (j == lo)
                        break;
                }

                while (a[lo].compareTo(a[++i]) > 0) {
                    if (i == hi)
                        break;
                }
                if (i >= j)
                    break;
                exch(a, i, j);
            }

            exch(a, lo, j);
            return j;
        }

        public static Comparable select(Comparable[] a, int k) {
            shuffle(a);

            int lo = 0, hi = a.length - 1;
            while (lo < hi) {
                int j = partition(a, lo, hi);
                if (k < j)
                    hi = j - 1;
                else if (k > j)
                    lo = j + 1;
                else
                    return a[k];
            }
            return a[k];
        }
    }

    //if there are so many duplications, use this method
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else i++;
        }

        sort(a, lt, lt - 1);
        sort(a, gt + 1, hi);

    }

}
