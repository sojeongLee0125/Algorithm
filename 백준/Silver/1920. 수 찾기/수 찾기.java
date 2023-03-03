import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
 * <p>
 * 1. 아이디어
 * - M개의 수가 각각 N개의 수 안에 있는 지 확인 => 시간 초과 : O(N) * O(M)
 * - 이진탐색 사용
 * - N개 정렬 >> M개 반복해서 탐색
 * <p>
 * 2. 시간복잡도
 * - N개의 수 정렬 : O(N * logN)
 * - M개의 수 이진 탐색 : O(M * logN)
 * => (N+M)logN : 2e5 * log2^10^5(20)
 * <p>
 * 3. 자료구조
 * - 탐색 대상 : int[]
 */
public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다.
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(arr);

        // 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어진다.
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            // 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
            divide(0, N - 1, Integer.parseInt(st.nextToken()));
        }
        // M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
        System.out.println(sb);
    }

    private static void divide(int st, int ed, int tar) {
        if (st == ed) {
            if (arr[st] == tar) {
                sb.append(1).append("\n");
            } else sb.append(0).append("\n");
            return;
        }

        int mid = (st + ed) / 2;
        if (arr[mid] < tar) {
            divide(mid + 1, ed, tar);
        } else {
            divide(st, mid, tar);
        }
    }

}