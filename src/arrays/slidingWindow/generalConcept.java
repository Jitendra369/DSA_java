package arrays.slidingWindow;

public class generalConcept {
    public static void main(String[] args) {
        int[] arr = {2,3,4,5,6,7,8,9,7};
        int windowSize = 4;
        int i = 0;
        int j = windowSize + i;
        int maxValue  = 0;
        while(j < arr.length){
            int max = printWindowElement(arr, i, j);
            if (max > maxValue){
                maxValue = max;
            }
            i++;
            j++;
        }
        System.out.println("maximum value is "+ maxValue);
    }

    private static int printWindowElement(int[] arr, int startPosition, int endPosition) {
        int sum = 0;
        System.out.println("window values ");
        for (int i = startPosition; i < endPosition; i++) {
            System.out.print(arr[i] + " ");
            sum += arr[i];
        }
        System.out.println("sum of above window is "+ sum);
        return sum;
    }
}
