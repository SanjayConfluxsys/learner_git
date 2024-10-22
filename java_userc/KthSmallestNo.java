//given k --> find which is the kth number or kth smallest number
//example: Input: arr[] = [7, 10, 4, 3, 20, 15], k = 3
//Output:  7
//Explanation: 3rd smallest element in the given array is 7.
public class KthSmallestNo {
    public static void main(String[] args) {
        int arr[] = {7, 10, 4, 3, 20, 15};
        int k = 3;
        //bubble sort
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        //print condition
        System.out.println(arr[k-1]);
    }
}
