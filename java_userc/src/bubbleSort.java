/*
first loop gives iteration in loop 2nd loop check whether the j-1 element is greater than j[i]
if yes the swapping is done and boolean swap is made true so that it iterate to the full loop if not given
it goes to the first loop so time increases
 */
import java.util.Arrays;
public class bubbleSort {
    public static void main(String[] args) {
        int arr[] = {4,1,3,9,7,82,2,91,100};
        System.out.println("Before swap: "+Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j-1]>arr[j]){
                    //swap
                    int temp =arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    swapped=true;
                }
            }
            if (!swapped){
                break;
            }
        //System.out.println(Arrays.toString(arr));
        }
        System.out.println("After swap: "+Arrays.toString(arr));
    }
}
