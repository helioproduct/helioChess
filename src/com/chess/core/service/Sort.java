package com.chess.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {

    public static boolean isSorted(int[] array, boolean ascending) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(ArrayList<Integer> list, boolean ascending) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static int min(int[] array) {
        int min = array[0];
        for (int i : array) {
            if (i < min) min = i;
        }
        return min;
    }

    public static int min(List<Integer> list) {
        int min = list.get(0);
        for (int i : list) {
            if (i < min) min = i;
        }
        return min;
    }

    public static int min(int[] array, int from, int to) {
        return min(Arrays.copyOfRange(array, from, to + 1));
    }

    public static int min(List<Integer> list, int from, int to) {
        int min = list.get(from);
        for (int i = from; i <= to; i++) {
            if (list.get(i) < min) min = list.get(i);
        }
        return min;
    }

    public static int max(int[] array) {
        int max = array[0];
        for (int i : array) {
            if (i > max) max = i;
        }
        return max;
    }

    public static int max(List<Integer> sourceList) {
        int max = sourceList.get(0);
        for (int i : sourceList) {
            if (i > max) max = i;
        }
        return max;
    }

    public static int max(int[] array, int from, int to) {
        return max(Arrays.copyOfRange(array, from, to + 1));
    }

    public static int[] bubbleSort(int[] array, final boolean ascending) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    isSorted = false;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        if (!ascending) {
            for (int i = 0; i < array.length / 2; i++) {
                int temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
        }
        return array;
    }

    public static int[] selectionSort(int[] sourceArray, final boolean ascending) {
        ArrayList<Integer> listToSort = new ArrayList<>();
        for (int i : sourceArray) listToSort.add(i);
        for (int i = 0; i < sourceArray.length; i++) {
            int min = min(listToSort);
            listToSort.remove((Integer) min);
            sourceArray[i] = min;
        }
        return sourceArray;
    }


    public static ArrayList<Integer> qSort(ArrayList<Integer> list) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (list.size() == 1 || isSorted(list, true)) {
            return list;
        }
        else {
            int pivot = (min(list) + max(list)) / 2;
            ArrayList<Integer> leftPart = new ArrayList<>();
            ArrayList<Integer> rightPart = new ArrayList<>();

            for (int number : list) {
                if (number <= pivot) leftPart.add(number);
                else rightPart.add(number);
            }

            sortedList.addAll(qSort(leftPart));
            sortedList.addAll(qSort(rightPart));
        }
        return sortedList;
    }
}