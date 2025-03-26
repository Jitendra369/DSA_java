package arrays.twopointer;


import java.util.HashSet;

public class IntersectionOfTwoArray {
    /* Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2]
        Output: [2]

        Input: nums1 = [4, 9, 5], nums2 = [9, 4, 9, 8, 4]
        Output: [9, 4]
         */
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 1, 4, 3, 9};
        int[] arr2 = {2, 2, 3, 3, 3, 4};

        usingSetOperation(arr, arr2);
//        if (arr.length < arr2.length) {`
//            findIntersection(arr, arr2);
//        } else {
//            findIntersection(arr2, arr);
//        }
    }

    private static Integer[] uniqueElement(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : arr) {
            set.add(x);
        }
        return set.toArray(new Integer[0]);
    }

    private static void usingSetOperation(int[] arr, int[] arr2) {
        Integer[] integers = null;
        if (arr.length > arr2.length) {
            integers = uniqueElement(arr2);
        } else {
            integers = uniqueElement(arr);
        }
        printArray(integers);

    }

    private static void findIntersection(int[] sArray, int[] bArray) {
        int[] subset = new int[sArray.length];
        int count = 0;
        for (int i = 0; i < sArray.length; i++) {
            for (int j = 0; j < bArray.length; j++) {
                if (sArray[i] == bArray[j]) {
                    subset[count++] = sArray[i];
                    break;
                }
            }
        }
        printArray(subset);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void printArray(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
