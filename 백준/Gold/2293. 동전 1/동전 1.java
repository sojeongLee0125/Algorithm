import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오.
 * - n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다.
 * - 각각의 동전은 몇 개라도 사용할 수 있다.
 * - 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 */
public class Main {
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        dp = new int[k + 5];

        // 초기값을 1로 갱신한다.
        dp[0] = 1;

        // 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다.
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // 1. 동전을 오름차순 정렬한다.
        Arrays.sort(coins);

        // 2. 작은 금액부터 차례대로 dp 테이블을 갱신한다.
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] += dp[i - coin];
            }
        }

        // 첫째 줄에 경우의 수를 출력한다. 경우의 수는 2^31보다 작다.
        System.out.println(dp[k]);
    }
}