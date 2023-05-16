import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        insertionSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int prev = i - 1;
            
            while(prev >= 0 && arr[prev] > tmp){
                arr[prev + 1] = arr[prev];
                prev--;
            }
            
            arr[prev+1] = tmp;
        }
    }
}
    
  
