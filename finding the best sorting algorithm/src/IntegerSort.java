import java.util.Arrays;
import java.util.Comparator;

public class IntegerSort {
    public static void countSort(int[] a, int l, int r) {
        int maxValue = 0;
        for (int i = l; i < r; i++)
            maxValue = Math.max(maxValue, a[i]);

        int[] cnt = new int[maxValue + 1];
        for (int i = l; i < r; i++)
            cnt[a[i]]++;

        for (int i = 0, p = l; i <= maxValue; i++)
            while (cnt[i]-- > 0)
                a[p++] = i;
    }

    public static void radixSort(int[] a, int l, int r) {
        int depth = 1;
        for (int i = l; i < r; i++)
            while (10L * depth <= a[i])
                depth *= 10;
        integerRadixSort(a, l, r, depth);
    }

    private static void integerRadixSort(int[] a, int l, int r, int depth) {
        if (r - l < 2)
            return;

        int[] ranksCount = new int[10];
        for (int i = l; i < r; i++) {
            int rank = a[i] / depth % 10;
            ranksCount[rank]++;
        }

        int[] initialPosition = Arrays.copyOf(ranksCount, 10);
        for (int i = 0; i + 1 < 10; i++)
            initialPosition[i + 1] += initialPosition[i];

        int[] result = new int[r - l];
        for (int i = l; i < r; i++) {
            int rank = a[i] / depth % 10;
            result[--initialPosition[rank]] = a[i];
        }
        System.arraycopy(result, 0, a, l, r - l);

        if (depth > 1)
            for (int i = 0, currentStartPosition = l; i < 10; i++) {
                if (ranksCount[i] > 0)
                    integerRadixSort(a, currentStartPosition, currentStartPosition + ranksCount[i], depth / 10);
                currentStartPosition += ranksCount[i];
            }
    }

    public static void quickSort(int[] a, int l, int r) {
        Integer[] A = new Integer[r - l];
        for (int i = l; i < r; i++)
            A[i - l] = a[i];
        QuickSort.sort(A, 0, r - l, Comparator.comparingInt((x) -> (int) x));
        for (int i = l; i < r; i++)
            a[i] = A[i - l];
    }

    public static void mergeSort(int[] a, int l, int r) {
        Integer[] A = new Integer[r - l];
        for (int i = l; i < r; i++)
            A[i - l] = a[i];
        MergeSort.sort(A, 0, r - l, Comparator.comparingInt((x) -> (int) x));
        for (int i = l; i < r; i++)
            a[i] = A[i - l];
    }

    public static void insertionSort(int[] a, int l, int r) {
        Integer[] A = new Integer[r - l];
        for (int i = l; i < r; i++)
            A[i - l] = a[i];
        InsertionSort.sort(A, 0, r - l, Comparator.comparingInt((x) -> (int) x));
        for (int i = l; i < r; i++)
            a[i] = A[i - l];
    }
}
