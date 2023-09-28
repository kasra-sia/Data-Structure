import java.util.Comparator;

public class MergeSort {
    public static void sort(Object a[], int l, int r, Comparator cmp) {
        if (r - l < 2)
            return;

        int mid = (l + r) / 2;
        sort(a, l, mid, cmp);
        sort(a, mid, r, cmp);
        merge(a, l, mid, r, cmp);
    }

    private static void merge(Object a[], int l, int mid, int r, Comparator cmp) {
        int n = r - l;
        Object[] b = new Object[n];
        for (int i = 0, p1 = l, p2 = mid; i < n; i++)
            if (p2 == r || (p1 < mid && cmp.compare(a[p1], a[p2]) < 0))
                b[i] = a[p1++];
            else
                b[i] = a[p2++];

        for (int i = 0; i < n; i++)
            a[l + i] = b[i];
    }
}
