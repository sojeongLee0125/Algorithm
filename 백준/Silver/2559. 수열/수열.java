import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 측정한 온도가 어떤 정수의 수열로 주어졌을 때, 연속적인 며칠 동안의 온도의 합이 가장 큰 값
 */
public class Main {
    static int N, K, max = Integer.MIN_VALUE;
    static int[] preSum = new int[100005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 정수 N은 온도를 측정한 전체 날짜의 수이다. N은 2 이상 100,000 이하이다.
        // 두 번째 정수 K는 합을 구하기 위한 연속적인 날짜의 수이다. K는 1과 N 사이의 정수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 매일 측정한 온도를 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다.
        // 이 수들은 모두 -100 이상 100 이하이다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            preSum[i] = preSum[i - 1] + num;
        }

        for (int i = K; i <= N; i++) {
            max = Math.max(max, preSum[i] - preSum[i - K]);
        }

        System.out.println(max);
    }
}