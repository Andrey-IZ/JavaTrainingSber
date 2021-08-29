package com.sber.javaschool.hometask1.array_utilities;

public class ArrayManager {
    /**
     * Выполняет сортировку пузырьком данный массив
     *
     * @param array - массив целых чисел
     */
    public static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Выполняет бинарный поиск в отсортированном массиве
     *
     * @param sortedArray     - сортированный массив
     * @param elementToSearch - искомое значение
     * @return результат поиска: true - значение найдено, false - значение не найдено
     */
    public static int binarySearch(int[] sortedArray, int elementToSearch) {
        int firstIndex = 0;
        int lastIndex = sortedArray.length - 1;

        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (sortedArray[middleIndex] == elementToSearch) {
                return middleIndex;
            } else if (sortedArray[middleIndex] < elementToSearch) {
                firstIndex = middleIndex + 1;
            } else if (sortedArray[middleIndex] > elementToSearch) {
                lastIndex = middleIndex - 1;
            }
        }
        return -1;
    }
}
