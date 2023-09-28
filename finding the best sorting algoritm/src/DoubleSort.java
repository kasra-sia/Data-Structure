import java.util.Arrays;
import java.util.Comparator;

public class DoubleSort {
    static final int precisionDigits = 6;

    public static void radixSort(double[] a, int l, int r) {
        double depth = 1;
        for (int i = l; i < r; i++)
            while (10 * depth <= a[i])
                depth *= 10;
        doubleRadixSort(a, l, r, depth);
    }

    private static void doubleRadixSort(double[] a, int l, int r, double depth) {
        if (r - l < 2)
            return;

        int[] ranksCount = new int[10];
        for (int i = l; i < r; i++) {
            int rank = (int) (a[i] / depth) % 10;
            ranksCount[rank]++;
        }

        int[] initialPosition = Arrays.copyOf(ranksCount, 10);
        for (int i = 0; i + 1 < 10; i++)
            initialPosition[i + 1] += initialPosition[i];

        double[] result = new double[r - l];
        for (int i = l; i < r; i++) {
            int rank = (int) (a[i] / depth) % 10;
            result[--initialPosition[rank]] = a[i];
        }
        System.arraycopy(result, 0, a, l, r - l);

        if (depth > Math.pow(10, -precisionDigits))
            for (int i = 0, currentStartPosition = l; i < 10; i++) {
                if (ranksCount[i] > 0)
                    doubleRadixSort(a, currentStartPosition, currentStartPosition + ranksCount[i], depth / 10);
                currentStartPosition += ranksCount[i];
            }
    }

    public static void quickSort(double[] a, int l, int r) {
        Double[] A = new Double[r - l];
        for (int i = l; i < r; i++)
            A[i - l] = a[i];
        QuickSort.sort(A, 0, r - l, Comparator.comparingDouble((x) -> (double) x));
        for (int i = l; i < r; i++)
            a[i] = A[i - l];
    }

    public static void mergeSort(double[] a, int l, int r) {
        Double[] A = new Double[r - l];
        for (int i = l; i < r; i++)
            A[i - l] = a[i];
        MergeSort.sort(A, 0, r - l, Comparator.comparingDouble((x) -> (double) x));
        for (int i = l; i < r; i++)
            a[i] = A[i - l];
    }

    public static void insertionSort(double[] a, int l, int r) {
        Double[] A = new Double[r - l];
        for (int i = l; i < r; i++)
            A[i - l] = a[i];
        InsertionSort.sort(A, 0, r - l, Comparator.comparingDouble((x) -> (double) x));
        for (int i = l; i < r; i++)
            a[i] = A[i - l];
    }
}
