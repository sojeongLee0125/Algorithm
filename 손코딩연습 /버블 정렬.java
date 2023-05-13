import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int tmp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }
}
    
  
