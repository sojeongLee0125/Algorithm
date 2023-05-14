import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] Left = Arrays.copyOfRange(arr, left, mid + 1);
        int[] Right = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0; // 왼쪽 배열 포인터
        int j = 0; // 오른쪽 배열 포인터

        int LeftLen = Left.length;
        int RightLen = Right.length;

        while (i < LeftLen && j < RightLen) {
            if (Left[i] <= Right[j]) {
                arr[left] = Left[i++];
            } else {
                arr[left] = Right[j++];
            }
            left++;
        }

        while (i < LeftLen) {
            arr[left++] = Left[i++];
        }
        while (j < RightLen) {
            arr[left++] = Right[j++];
        }
    }
}
