import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. ‘수첩2’에 적혀있는 순서대로, 각각의 수에 대하여, ‘수첩1’에 있으면 1을, 없으면 0을 출력하는 프로그램을 작성해보자.
 */
public class Main {
    static int T, N, M;
    static int[] arr1, arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫째 줄에 테스트케이스의 개수 T가 들어온다.
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr1 = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr1);

            M = Integer.parseInt(br.readLine());
            arr2 = new int[M];

            // ‘수첩2’에 적혀있는 M개의 숫자 순서대로, ‘수첩1’에 있으면 1을, 없으면 0을 출력한다.
            // 1. arr1을 정렬한다.
            // 2. arr2를 차례대로 이분탐색하여 결과를 출력한다.

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (isFind(num)) bw.write(1 + " \n");
                else bw.write(0 + " \n");
            }
        }

        br.close();
        bw.close();
    }

    private static boolean isFind(int num) {
        int lt = 0;
        int rt = arr1.length - 1;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (arr1[mid] == num) return true;
            if (arr1[mid] < num) {
                // 범위를 더 큰쪽으로 확장
                lt = mid + 1;
            } else {
                // 범위를 더 작은 쪽으로 확장
                rt = mid - 1;
            }
        }
        return false;
    }

}