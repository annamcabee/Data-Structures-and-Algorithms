import java.util.ArrayList;
import java.util.Random;
import java.util.List;


import java.util.Comparator;

public class SortingForTest {

    public ArrayList<Integer> mergeSort(ArrayList<Integer> input) {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        if (input.size() <= 1) {
            return input;
        }
        int mid = input.size()/2;
        for (int i = 0; i < mid; i++) {
            left.add(input.get(i));
        }
        for (int j = mid; j < input.size(); j++) {
            right.add(input.get(j));
        }
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);

    }
    public ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> output = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
        int restIndex = 0;
        ArrayList<Integer> rest = new ArrayList<>();
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                output.add(wholeIndex++, left.get(leftIndex++));
            } else {
                output.add(wholeIndex++, right.get(rightIndex++));
            }
        }
        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        } else {
            rest = left;
            restIndex = leftIndex;
        }
        for (int i = restIndex; i < rest.size(); i++) {
            output.add(wholeIndex++, rest.get(i));
        }
        return output;
    }

    public int[] bubbleSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - i - 1; j++) {
                if (input[j] > input[j + 1]) {
                    swap(input, j, j + 1);
                }
            }
        }
        return input;

    }
    public int[] quickSort(int[] input) {
        return input;
    }
    public int partition(int[] input, int left, int right) {
        return left;
    }
    public int[] insertionSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            int j = i;
            while (j > 0 && input[j - 1] > input[j]) {
                swap(input, j -1, j);
                j--;
            }
        }
        return input;
    }
    public int[] selectionSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int min = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[min]) {
                    min = j;
                }
            }
            swap(input, i, min);
        }
        return input;
    }
    public void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
