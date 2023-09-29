import java.util.Arrays;
import java.util.Comparator;

public class StringSort {
    public static void radixSort(String[] s, int l, int r) {
        stringRadixSort(s, l, r, 0);
    }

    private static void stringRadixSort(String[] s, int l, int r, int depth) {
        if (r - l < 2)
            return;

        int[] ranksCount = new int[27];
        for (int i = l; i < r; i++) {
            int rank = s[i].length() > depth ? s[i].charAt(depth) - 'a' + 1 : 0;
            ranksCount[rank]++;
        }

        int[] initialPosition = Arrays.copyOf(ranksCount, 27);
        for (int i = 0; i < 26; i++)
            initialPosition[i + 1] += initialPosition[i];

        String[] result = new String[r - l];
        for (int i = l; i < r; i++) {
            int rank = s[i].length() > depth ? s[i].charAt(depth) - 'a' + 1 : 0;
            result[--initialPosition[rank]] = s[i];
        }
        System.arraycopy(result, 0, s, l, r - l);

        for (int i = 1, currentStartPosition = l; i < 27; i++) {
            currentStartPosition += ranksCount[i - 1];
            if (ranksCount[i] > 0)
                stringRadixSort(s, currentStartPosition, currentStartPosition + ranksCount[i], depth + 1);
        }
    }

    public static void quickSort(String[] s, int l, int r) {
        QuickSort.sort(s, l, r, Comparator.comparing(String::toString));
    }

    public static void mergeSort(String[] s, int l, int r) {
        MergeSort.sort(s, l, r, Comparator.comparing(String::toString));
    }

    public static void insertionSort(String[] s, int l, int r) {
        InsertionSort.sort(s, l, r, Comparator.comparing(String::toString));
    }
}
