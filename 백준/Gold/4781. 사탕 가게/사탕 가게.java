import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 사탕 가게에 있는 모든 사탕의 가격과 칼로리가 주어졌을 때, 칼로리의 합이 가장 크게 되는 방법.
 * - 두 사람은 같은 돈을 가지고 가게에 들어가서 사탕을 산다.
 * - 이때, 구매한 사탕의 칼로리가 더 큰 사람이 내기에서 이기게 된다.
 * - 현재 사탕 가게에 있는 사탕의 가격과 칼로리가 모두 등재되어 있다.
 * - 각 사탕의 개수는 매우 많기 때문에, 원하는 만큼 사탕을 구매할 수 있다.
 * - 또, 사탕은 쪼갤 수 없기 때문에, 일부만 구매할 수 없다.
 */
public class Main {
    static int n, m1, m2;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // 각 테스트 케이스의 첫째 줄에는 가게에 있는 사탕 종류의 수 n과 상근이가 가지고 있는 돈의 양 m이 주어진다.
            // (1 ≤ n ≤ 5,000, 0.01 ≤ m ≤ 100.00)
            // m은 항상 소수점 둘째자리까지 주어진다.
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            st = new StringTokenizer(st.nextToken(), ".");
            m1 = Integer.parseInt(st.nextToken());
            m2 = Integer.parseInt(st.nextToken());

            dp = new int[100005];
            int cost = m1 * 100 + m2;

            // 다음 n개 줄에는 각 사탕의 칼로리 c와 가격 p가 주어진다. (1 ≤ c ≤ 5,000, 0.01 ≤ p ≤ 100.00)
            // c는 항상 정수, p는 항상 소수점 둘째자리이다.
            // 입력의 마지막 줄에는 '0 0.00'이 주어진다.
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int c = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(st.nextToken(), ".");
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());
                int p = p1 * 100 + p2;

                for (int j = p; j <= cost; j++) {
                    dp[j] = Math.max(dp[j], dp[j - p] + c);
                }
            }

            System.out.println(dp[cost]);
        }
    }
}