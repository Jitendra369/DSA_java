package arrays.slidingWindow;

public class OptimizedVersionGenralConncept {
    public static void main(String[] args) {
        int[] arr = {2,4,5,6,7,8,9,2,3,5};
        int sum = 0;
        int i = 0;
        int j = 0;
        int windowSize =  4;
        int max = Integer.MIN_VALUE;
        while(j < arr.length){
            sum = sum + arr[j];
            if (j - i + 1 < windowSize){  // increase the j pointer , till we reached the window size
                j++;
            } else if (j - i + 1 == windowSize) {  // check if we reach the window size
                max = Math.max(max, sum);
                sum =  sum - arr[i];
                i++; // increment the window size
                j++; // increment the window size
            }
        }
        System.out.println("max value form window is "+ max);
    }
}
