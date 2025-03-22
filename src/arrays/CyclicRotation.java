package arrays;

import java.util.Arrays;

public class CyclicRotation {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        cyclicRotateElements(arr);
    }

    private static void cyclicRotateElements(int[] arr) {
        int lastElement = arr[arr.length-1];
        int i = arr.length -1 ;
        int pre = arr.length -2;
        while (i > 0){
            swap(arr,i,pre);
            i--;
            pre--;
        }
        Arrays.stream(arr).forEach(x-> System.out.print(x + ", "));
    }

    private static void swap(int[] arr, int pre, int next){
        int temp = arr[pre];
        arr[pre] = arr[next];
        arr[next] = temp;
    }
}
