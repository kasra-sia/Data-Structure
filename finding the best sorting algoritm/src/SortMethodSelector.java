import java.util.Arrays;
import java.util.Comparator;

public class SortMethodSelector {
    static final double CountSortConstant = 3.2;
    static final double QuickSortConstant = 2.3;
    static final double MergeSortConstant = 3.1;

    public static void analyseDataForStringInput(int n, String[] s) {
        int maxLength = 0;
        long reversedCost = 0;
        long inversionCount = 0;
        for (int i = 0, cnt = 0; i < n; i++, cnt++) {
            for (int j = 0; j < i; j++)
                if (s[j].compareTo(s[i]) > 0)
                    inversionCount++;

            if (i == n - 1 || (i > 0 && s[i].compareTo(s[i - 1]) > 0)) {
                reversedCost += (long) cnt * cnt;
                cnt = 0;
            }
            maxLength = Math.max(maxLength, s[i].length());
        }

        long radixSortApproxTime = (long) n * maxLength;
        long quickSortApproxTime = (long) (reversedCost * QuickSortConstant + n * Math.log(n) * MergeSortConstant);
        long mergeSortApproxTime = (long) (n * Math.log(n) * MergeSortConstant);
        long insertSortApproxTime = inversionCount + n;

        System.out.println("- - - - - - - - - - - - - -");
        System.out.print("Best Approximated Method: ");
        if (radixSortApproxTime <= quickSortApproxTime && radixSortApproxTime <= mergeSortApproxTime && radixSortApproxTime <= insertSortApproxTime)
            System.out.println("Radix Sort");
        else if (quickSortApproxTime <= mergeSortApproxTime && quickSortApproxTime <= insertSortApproxTime)
            System.out.println("Quick Sort");
        else if (mergeSortApproxTime <= insertSortApproxTime)
            System.out.println("Merge Sort");
        else
            System.out.println("Insertion Sort");

        long radixSortStartCheckpoint = System.nanoTime();
        StringSort.radixSort(Arrays.copyOf(s, n), 0, n);
        long radixSortEndCheckpoint = System.nanoTime();

        long quickSortStartCheckpoint = System.nanoTime();
        StringSort.quickSort(Arrays.copyOf(s, n), 0, n);
        long quickSortEndCheckpoint = System.nanoTime();

        long mergeSortStartCheckpoint = System.nanoTime();
        StringSort.mergeSort(Arrays.copyOf(s, n), 0, n);
        long mergeSortEndCheckpoint = System.nanoTime();

        long insertionSortStartCheckpoint = System.nanoTime();
        StringSort.insertionSort(Arrays.copyOf(s, n), 0, n);
        long insertionSortEndCheckpoint = System.nanoTime();

        System.out.println("- - - - - - - - - - - - - -");
        System.out.println("RadixTime: " + (radixSortEndCheckpoint - radixSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("QuickTime: " + (quickSortEndCheckpoint - quickSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("MergeTime: " + (mergeSortEndCheckpoint - mergeSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("InsertionTime: " + (insertionSortEndCheckpoint - insertionSortStartCheckpoint) / 1e6 + "ms");

        long mySortStartCheckpoint = System.nanoTime();
        MySort.sort(Arrays.copyOf(s, n), 0, n, Comparator.comparing(String::toString));
        long mySortEndCheckpoint = System.nanoTime();

        long javaSortStartCheckpoint = System.nanoTime();
        Arrays.sort(Arrays.copyOf(s, n));
        long javaSortEndCheckpoint = System.nanoTime();

        System.out.println("- - - - - - - - - - - - - -");
        System.out.println("MyTime: " + (mySortEndCheckpoint - mySortStartCheckpoint) / 1e6 + "ms");
        System.out.println("JavaTime: " + (javaSortEndCheckpoint - javaSortStartCheckpoint) / 1e6 + "ms");
    }

    public static void analyseDataForInteger(int n, int[] a) {
        int maxValue = 0;
        long reversedCost = 0;
        long inversionCount = 0;
        for (int i = 0, cnt = 0; i < n; i++, cnt++) {
            for (int j = 0; j < i; j++)
                if (a[i] < a[j])
                    inversionCount++;

            if (i == n - 1 || (i > 0 && a[i] > a[i - 1])) {
                reversedCost += (long) cnt * cnt;
                cnt = 0;
            }
            maxValue = Math.max(maxValue, a[i]);
        }
        int maxLength = (int) Math.log10(maxValue) + 1;

        long countSortApproxTime = (long) (CountSortConstant * n + maxValue);
        long radixSortApproxTime = (long) n * maxLength;
        long quickSortApproxTime = (long) (reversedCost * QuickSortConstant + n * Math.log(n) * MergeSortConstant);
        long mergeSortApproxTime = (long) (n * Math.log(n) * MergeSortConstant);
        long insertSortApproxTime = inversionCount + n;

        System.out.println("- - - - - - - - - - - - - -");
        System.out.print("Best Approximated Method: ");
        if (countSortApproxTime <= radixSortApproxTime && countSortApproxTime <= quickSortApproxTime && countSortApproxTime <= mergeSortApproxTime && countSortApproxTime <= insertSortApproxTime)
            System.out.println("Count Sort");
        else if (radixSortApproxTime <= quickSortApproxTime && radixSortApproxTime <= mergeSortApproxTime && radixSortApproxTime <= insertSortApproxTime)
            System.out.println("Radix Sort");
        else if (quickSortApproxTime <= mergeSortApproxTime && quickSortApproxTime <= insertSortApproxTime)
            System.out.println("Quick Sort");
        else if (mergeSortApproxTime <= insertSortApproxTime)
            System.out.println("Merge Sort");
        else
            System.out.println("Insertion Sort");

        long countSortStartCheckpoint = System.nanoTime();
        IntegerSort.countSort(Arrays.copyOf(a, n), 0, n);
        long countSortEndCheckpoint = System.nanoTime();

        long radixSortStartCheckpoint = System.nanoTime();
        IntegerSort.radixSort(Arrays.copyOf(a, n), 0, n);
        long radixSortEndCheckpoint = System.nanoTime();

        long quickSortStartCheckpoint = System.nanoTime();
        IntegerSort.quickSort(Arrays.copyOf(a, n), 0, n);
        long quickSortEndCheckpoint = System.nanoTime();

        long mergeSortStartCheckpoint = System.nanoTime();
        IntegerSort.mergeSort(Arrays.copyOf(a, n), 0, n);
        long mergeSortEndCheckpoint = System.nanoTime();

        long insertionSortStartCheckpoint = System.nanoTime();
        IntegerSort.insertionSort(Arrays.copyOf(a, n), 0, n);
        long insertionSortEndCheckpoint = System.nanoTime();

        System.out.println("- - - - - - - - - - - - - -");
        System.out.println("CountTime: " + (countSortEndCheckpoint - countSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("RadixTime: " + (radixSortEndCheckpoint - radixSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("QuickTime: " + (quickSortEndCheckpoint - quickSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("MergeTime: " + (mergeSortEndCheckpoint - mergeSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("InsertionTime: " + (insertionSortEndCheckpoint - insertionSortStartCheckpoint) / 1e6 + "ms");

        Integer[] A = new Integer[n];
        for (int i = 0; i < n; i++)
            A[i] = a[i];

        long mySortStartCheckpoint = System.nanoTime();
        MySort.sort(Arrays.copyOf(A, n), 0, n, Comparator.comparingInt((x) -> (int) x));
        long mySortEndCheckpoint = System.nanoTime();

        long javaSortStartCheckpoint = System.nanoTime();
        Arrays.sort(Arrays.copyOf(A, n));
        long javaSortEndCheckpoint = System.nanoTime();

        System.out.println("- - - - - - - - - - - - - -");
        System.out.println("MyTime: " + (mySortEndCheckpoint - mySortStartCheckpoint) / 1e6 + "ms");
        System.out.println("JavaTime: " + (javaSortEndCheckpoint - javaSortStartCheckpoint) / 1e6 + "ms");
    }

    public static void analyseDataForDouble(int n, double[] a) {
        int maxLength = 0;
        long reversedCost = 0;
        long inversionCount = 0;
        for (int i = 0, cnt = 0; i < n; i++, cnt++) {
            for (int j = 0; j < i; j++)
                if (a[i] < a[j])
                    inversionCount++;

            if (i == n - 1 || (i > 0 && a[i] > a[i - 1])) {
                reversedCost += (long) cnt * cnt;
                cnt = 0;
            }
            maxLength = Math.max(maxLength, (int) Math.log10(a[i]) + 1);
        }

        int radixSortApproxTime = n * (maxLength + DoubleSort.precisionDigits);
        long quickSortApproxTime = (long) (reversedCost * QuickSortConstant + n * Math.log(n) * MergeSortConstant);
        long mergeSortApproxTime = (long) (n * Math.log(n) * MergeSortConstant);
        long insertSortApproxTime = inversionCount + n;

        System.out.println("- - - - - - - - - - - - - -");
        System.out.print("Best Approximated Method: ");
        if (radixSortApproxTime <= quickSortApproxTime && radixSortApproxTime <= mergeSortApproxTime && radixSortApproxTime <= insertSortApproxTime)
            System.out.println("Radix Sort");
        else if (quickSortApproxTime <= mergeSortApproxTime && quickSortApproxTime <= insertSortApproxTime)
            System.out.println("Quick Sort");
        else if (mergeSortApproxTime <= insertSortApproxTime)
            System.out.println("Merge Sort");
        else
            System.out.println("Insertion Sort");

        long radixSortStartCheckpoint = System.nanoTime();
        DoubleSort.radixSort(Arrays.copyOf(a, n), 0, n);
        long radixSortEndCheckpoint = System.nanoTime();

        long quickSortStartCheckpoint = System.nanoTime();
        DoubleSort.quickSort(Arrays.copyOf(a, n), 0, n);
        long quickSortEndCheckpoint = System.nanoTime();

        long mergeSortStartCheckpoint = System.nanoTime();
        DoubleSort.mergeSort(Arrays.copyOf(a, n), 0, n);
        long mergeSortEndCheckpoint = System.nanoTime();

        long insertionSortStartCheckpoint = System.nanoTime();
        DoubleSort.insertionSort(Arrays.copyOf(a, n), 0, n);
        long insertionSortEndCheckpoint = System.nanoTime();

        System.out.println("- - - - - - - - - - - - - -");
        System.out.println("RadixTime: " + (radixSortEndCheckpoint - radixSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("QuickTime: " + (quickSortEndCheckpoint - quickSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("MergeTime: " + (mergeSortEndCheckpoint - mergeSortStartCheckpoint) / 1e6 + "ms");
        System.out.println("InsertionTime: " + (insertionSortEndCheckpoint - insertionSortStartCheckpoint) / 1e6 + "ms");

        Double[] A = new Double[n];
        for (int i = 0; i < n; i++)
            A[i] = a[i];

        long mySortStartCheckpoint = System.nanoTime();
        MySort.sort(Arrays.copyOf(A, n), 0, n, Comparator.comparingDouble((x) -> (double) x));
        long mySortEndCheckpoint = System.nanoTime();

        long javaSortStartCheckpoint = System.nanoTime();
        Arrays.sort(Arrays.copyOf(A, n));
        long javaSortEndCheckpoint = System.nanoTime();

        System.out.println("- - - - - - - - - - - - - -");
        System.out.println("MyTime: " + (mySortEndCheckpoint - mySortStartCheckpoint) / 1e6 + "ms");
        System.out.println("JavaTime: " + (javaSortEndCheckpoint - javaSortStartCheckpoint) / 1e6 + "ms");
    }
}
