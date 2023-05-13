import java.util.Arrays;

public class Main {
  
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        selectionSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[idx] > arr[j]) {
                    idx = j;
                }
            }
            if (idx != i) {
                int tmp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = tmp;
            }
        }
    }
}
    
  
