//Majority element gfg --> find the highest occurred number
/*Input: arr[] = [3, 1, 3, 3, 2]
Output: 3
Explanation: Since, 3 is present more than n/2 times, so it is the majority element.
*/
//approach
//1st loop select elements, 2nd counting of occurrence, 3rd step check condition, if occurred more than n/2
public class MajorityElement {
    public static void main(String[] args) {
        int arr[] = {3,1,2,3,3,3,4};
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            //selected element is v[i]
            int count = 0;
            for (int j = 0; j < n; j++) {
                // counting the frequency of v[i]
                if (arr[j] == arr[i]) {
                    count++;
                }
            }

            // check if frquency is greater than n/2:
            if (count > (n / 2)) {
                System.out.println(arr[i]);
                System.exit(0);
            }
            else continue;
        }

        System.out.println("-1");
        System.exit(0);
    }
}
