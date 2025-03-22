package arrays;

import java.util.HashMap;

public class CountPairWithGivenSum {
    public static void main(String[] args) {
        int[] arr = {1,5,7,-1,10,-4};
        int targetSum = 6;

//        findV1(arr, targetSum);
        int countOfPairs = findUsingHashMap(arr, targetSum);
        System.out.println(countOfPairs);
    }

    private static int findUsingHashMap(int[] arr, int targetSum) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(targetSum - arr[i])){
                count += hashMap.get(targetSum - arr[i]);
            }
            if (hashMap.containsKey(arr[i])){
                hashMap.put(arr[i], hashMap.get(arr[i] + 1));
            }else{
                hashMap.put(arr[i], 1);
            }
        }
        return count;
    }

    private static void findV1(int[] arr, int targetSum) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j <= arr.length -1; j++) {
                if (arr[i] + arr[j] == targetSum){
                    System.out.println("pair found at location [ "+ i + ", "+ j + "]");
                }
            }
        }
    }
}
