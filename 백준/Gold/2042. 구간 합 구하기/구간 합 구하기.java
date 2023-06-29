import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다.
 * - 어떤 N개의 수가 주어져 있다.
 * - 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다.
 */
public class Main {
    static int N, M, K;
    static long[] tree, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 수의 개수 N(1 ≤ N ≤ 1,000,000)
        // M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000)
        // M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        tree = new long[N + 1];

        // 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다.
        for (int i = 1; i <= N; i++) {
            num[i] = Long.parseLong(br.readLine());
            update(tree, i, num[i]);
        }

        // 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데,
        // a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고
        // a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의
        // 합을 구하여 출력하면 된다.

        int count = M + K;
        StringBuilder sb = new StringBuilder();

        while (count-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int flag = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            long num2 = Long.parseLong(st.nextToken());

            if (flag == 1) {
                // 차이값를 펜윅트리에 더해주어서 값을 교환한다.
                long diff = num2 - num[num1];
                num[num1] = num2;
                update(tree, num1, diff);
            } else {
                sb.append(getSum(tree, (int) num2) - getSum(tree, num1 - 1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static long getSum(long[] tree, int idx) {
        long sum = 0L;

        while (idx > 0) {
            sum += tree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }

    private static void update(long[] tree, int idx, long val) {
        while (idx < tree.length) {
            tree[idx] += val;
            idx += (idx & -idx);
        }
    }
}