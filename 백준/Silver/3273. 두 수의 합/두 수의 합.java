import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Q. 자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.
 * - n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다. 1보다 크거나 같고, 1,000,000보다 작거나 같은 자연수이다.
 */
public class Main {

    static int n, x;
    static long cnt;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 수열의 크기 n이 주어진다.
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        // 다음 줄에는 수열에 포함되는 수가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 셋째 줄에는 x가 주어진다.
        x = Integer.parseInt(br.readLine());

        // 1. 오름차순 정렬한다.
        Arrays.sort(arr);

        // 2. 투포인터로 검색한다.
        int lt = 0;
        int rt = n - 1;
        while (lt < rt) {
            if (arr[lt] + arr[rt] == x) {
                cnt++;
                lt++;
            } else if (arr[lt] + arr[rt] < x) {
                lt++;
            } else {
                rt--;
            }
        }

        // 문제의 조건을 만족하는 쌍의 개수를 출력한다.
        System.out.println(cnt);
    }

}