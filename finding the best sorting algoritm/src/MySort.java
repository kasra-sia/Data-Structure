import java.util.Comparator;

public class MySort{
    public static void sort(Object a[], int l, int r, Comparator cmp) {
        int depth = 1;
        while ((1 << depth) < r - l)
            depth++;
        introSort(a, l, r, depth << 1, cmp);
    }

    private static void introSort(Object a[], int l, int r, int depth, Comparator cmp) {
        int n = r - l;
        if (n < 2)
            return;

        if (n < 16) {
            InsertionSort.sort(a, l, r, cmp);
            return;
        }
        if (depth == 0) {
            heapSort(a, l, r, cmp);
            return;
        }

        int pivot = getTreeMedian(a, l, (l + r) / 2, r - 1, cmp);
        if (pivot != r - 1)
            swap(a, pivot, r - 1);

        pivot = partition(a, l, r, cmp);
        introSort(a, l, pivot, depth - 1, cmp);
        introSort(a, pivot + 1, r, depth - 1, cmp);
    }

    private static int getTreeMedian(Object a[], int p1, int p2, int p3, Comparator cmp) {
        int temp = cmp.compare(a[p1], a[p2]);
        if (temp == 0)
            return p1;
        if (temp > 0) {
            temp = p1;
            p1 = p2;
            p2 = temp;
        }

        temp = cmp.compare(a[p2], a[p3]);
        if (temp <= 0)
            return p2;

        temp = cmp.compare(a[p1], a[p3]);
        if (temp <= 0)
            return p3;
        return p1;
    }

    private static int partition(Object a[], int l, int r, Comparator cmp) {
        int p = l;
        for (int i = l; i < r; i++)
            if (cmp.compare(a[i], a[r - 1]) < 0)
                swap(a, i, p++);

        swap(a, r - 1, p);
        return p;
    }
    private static void heapSort(Object a[], int l, int r, Comparator cmp) {
        int n = r - l;
        for (int i = (l + r) / 2; i >= l; i--)
            heapify(a, i, l, r, cmp);

        for (int i = r - 1; i > l; i--) {
            swap(a, l, i);
            heapify(a, l, l, i, cmp);
        }
    }

    private static void heapify(Object a[], int p, int l, int r, Comparator cmp) {
        int best = p;

        for (int candidate: new int[]{l + 2 * (p - l) + 1, l + 2 * (p - l) + 2})
            if (candidate < r && cmp.compare(a[best], a[candidate]) < 0)
                best = candidate;

        if (best != p) {
            swap(a, p, best);
            heapify(a, best, l, r, cmp);
        }
    }

    private static void swap(Object a[], int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
