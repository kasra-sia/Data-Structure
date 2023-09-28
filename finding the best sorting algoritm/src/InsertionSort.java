import java.util.Comparator;

public class InsertionSort {
    public static void sort(Object[] a, int l, int r, Comparator cmp) {
        for (int i = l; i < r; i++)
            for (int j = i; j > l && cmp.compare(a[j], a[j - 1]) < 0; j--)
                swap(a, j, j - 1);
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
