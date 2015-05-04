import java.util.ArrayList;
import java.util.Random;
import java.util.List;


import java.util.Comparator;

//Anna McAbee
public class Sorting {

    /**
     * Implement bubble sort.
     * <p/>
     * It should be:
     * in-place
     * stable
     * <p/>
     * Have a worst case running time of:
     * O(n^2)
     * <p/>
     * And a best case running time of:
     * O(n)
     * <p/>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void bubblesort(T[] arr, Comparator<T> comparator) {
        if (comparator != null || arr != null) {
            boolean sorted = false;
            while (!sorted) {
                sorted = true;
                for (int i = 0; i < arr.length - 1; i++) {
                    if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                        T temp = arr[i + 1];
                        arr[i + 1] = arr[i];
                        arr[i] = temp;
                        sorted = false;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }

    /**
     * Implement insertion sort.
     * <p/>
     * It should be:
     * in-place
     * stable
     * <p/>
     * Have a worst case running time of:
     * O(n^2)
     * <p/>
     * And a best case running time of:
     * O(n)
     * <p/>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void insertionsort(T[] arr, Comparator<T> comparator) {
        if (comparator != null || arr != null) {
            for (int i = 0; i != arr.length; i++) {
                T temp = arr[i];
                int j = i;
                while (j != 0 && comparator.compare(arr[j - 1], temp) > 0) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
            }
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }

    /**
     * Implement shell sort.
     * <p/>
     * It should be:
     * in-place
     * <p/>
     * Have a worst case running time of:
     * O(n^2)
     * <p/>
     * And a best case running time of:
     * O(n log(n))
     * <p/>
     * Note that there may be duplicates in the array.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void shellsort(T[] arr, Comparator<T> comparator) {
        if (comparator != null || arr != null) {
            int h = 1;
            while (h < arr.length / 3) {
                h = 3 * h + 1;
            }
            while (h >= 1) {
                for (int i = h; i < arr.length; i++) {
                    for (int j = i; j >= h && comparator.
                            compare(arr[j], arr[j - h]) < 0; j -= h) {
                        T temp = arr[j];
                        arr[j] = arr[j - h];
                        arr[j - h] = temp;
                    }
                }
                h /= 3;
            }
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }

    /**
     * Implement quick sort.
     * <p/>
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     * <p/>
     * int pivotIndex = r.nextInt(b - a) + a;
     * <p/>
     * It should be:
     * in-place
     * <p/>
     * Have a worst case running time of:
     * O(n^2)
     * <p/>
     * And a best case running time of:
     * O(n log n)
     * <p/>
     * Note that there may be duplicates in the array.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws IllegalArgumentException if the array or comparator or rand is
     *                                  null
     */
    public static <T> void quicksort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (comparator != null || arr != null || rand != null) {
            if (arr.length > 1) {
                quicksort(arr, comparator, rand, 0, arr.length - 1);
            }
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }

    /**
     * helper method for quick sort
     *
     * @param arr         the array
     * @param comparator  how items will be compared
     * @param rand        the random object
     * @param lowerIndex  index
     * @param higherIndex index
     * @param <T>         type of array
     */
    private static <T> void quicksort(T[] arr, Comparator<T>
            comparator, Random rand, int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        T pivot = arr[Math.abs(
                rand.nextInt(higherIndex - lowerIndex) + lowerIndex)];
        while (i <= j) {
            while (comparator.compare(arr[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(arr[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        if (lowerIndex < j) {
            quicksort(arr, comparator, rand, lowerIndex, j);
        }
        if (i < higherIndex) {
            quicksort(arr, comparator, rand, i, higherIndex);
        }
    }

    /**
     * swap helper method
     *
     * @param array  the array
     * @param index1 first index
     * @param index2 second index
     * @param <T>    type of the array
     */
    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Implement merge sort.
     * <p/>
     * It should be:
     * stable
     * <p/>
     * Have a worst case running time of:
     * O(n log n)
     * <p/>
     * And a best case running time of:
     * O(n log n)
     * <p/>
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     * <p/>
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     * <p/>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void mergesort(T[] arr, Comparator<T> comparator) {
        if (comparator != null || arr != null) {
            T[] temp = (T[]) new Object[arr.length];
            mergesort(arr, temp, comparator, 0, arr.length - 1);
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }

    /**
     * helper method for merge sort
     *
     * @param arr        the array
     * @param temp       temporary array
     * @param comparator how items will be compared
     * @param left       index
     * @param right      index
     * @param <T>        type of array
     */
    private static <T> void mergesort(T[] arr, T[] temp, Comparator<T>
            comparator, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergesort(arr, temp, comparator, left, center);
            mergesort(arr, temp, comparator, center + 1, right);
            merge(arr, temp, comparator, left, center + 1, right);
        }
    }

    /**
     * merge sort helper method
     *
     * @param arr        the array
     * @param temp       new array
     * @param comparator how items will be compared
     * @param left       index
     * @param right      index
     * @param rightEnd   index
     * @param <T>        the type of the array
     */
    private static <T> void merge(T[] arr, T[] temp, Comparator<T> comparator,
                                  int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if (comparator.compare(arr[left], arr[right]) <= 0) {
                temp[k++] = arr[left++];
            } else {
                temp[k++] = arr[right++];
            }
        }
        while (left <= leftEnd) {
            temp[k++] = arr[left++];
        }
        while (right <= rightEnd) {
            temp[k++] = arr[right++];
        }
        for (int i = 0; i < num; i++, rightEnd--) {
            arr[rightEnd] = temp[rightEnd];
        }
    }

    /**
     * Implement radix sort.
     * <p/>
     * Remember you CANNOT convert the ints to strings.
     * <p/>
     * It should be:
     * stable
     * <p/>
     * Have a worst case running time of:
     * O(kn)
     * <p/>
     * And a best case running time of:
     * O(kn)
     * <p/>
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     * <p/>
     * You may use an ArrayList or LinkedList if you wish,
     * but it may only be used inside radixsort and any radix sort helpers
     * Do NOT use these classes with other sorts.
     *
     * @param arr the array to be sorted
     * @return the sorted array
     * @throws IllegalArgumentException if the array is null
     */
    public static int[] radixsort(int[] arr) {
        if (arr != null) {
            final int radix = 10;
            List<Integer>[] bucket = new ArrayList[radix];
            List<Integer>[] bucketNeg = new ArrayList[radix];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
                bucketNeg[i] = new ArrayList<>();
            }
            boolean maxLength = false;
            int tmp;
            int placement = 1;
            while (!maxLength) {
                maxLength = true;
                for (Integer i : arr) {
                    tmp = i / placement;
                    if (tmp % radix >= 0) {
                        bucket[tmp % radix].add(i);
                    } else {
                        bucketNeg[bucketNeg.length
                                 - Math.abs(tmp % radix)].add(i);
                    }
                    if (maxLength && tmp > 0) {
                        maxLength = false;
                    }
                }
                int a = 0;
                for (int x = 0; x < radix; x++) {
                    for (Integer i : bucketNeg[x]) {
                        arr[a++] = i;
                    }
                    bucketNeg[x].clear();
                }
                for (int b = 0; b < radix; b++) {
                    for (Integer i : bucket[b]) {
                        arr[a++] = i;
                    }
                    bucket[b].clear();
                }
                placement *= radix;
            }
            return arr;
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }


}
