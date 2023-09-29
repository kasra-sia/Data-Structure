import java.util.Comparator;

public class QuickSort {
    public static void sort(Object[] a, int l, int r, Comparator cmp) {
        if (r - l < 2)
            return;

        int p = l;
        for (int i = l; i < r; i++)
            if (cmp.compare(a[i], a[r - 1]) < 0)
                swap(a, p++, i);
        swap(a, p, r - 1);

        sort(a, l, p, cmp);
        sort(a, p + 1,r, cmp);
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[i] = temp;
    }
}
