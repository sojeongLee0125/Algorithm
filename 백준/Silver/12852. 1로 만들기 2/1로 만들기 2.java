import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q. 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * <p>
 * - 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * - X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * - X가 2로 나누어 떨어지면, 2로 나눈다.
 * - 1을 뺀다.
 */
public class Main {
    static int[] dp; // 각 숫자별 1까지의 연산 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 1보다 크거나 같고, 10^6보다 작거나 같은 자연수 N이 주어진다.
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 5];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
        // 둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다.
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= N; i++) {
            if (i % 3 == 0) dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
            if (i % 2 == 0) dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }

        System.out.println(dp[N]);
        go(N);
    }

    private static void go(int cur) {
        if (cur == 0) return;
        System.out.print(cur + " ");
        if (cur % 3 == 0 && dp[cur] == dp[cur / 3] + 1) go(cur / 3);
        else if (cur % 2 == 0 && dp[cur] == dp[cur / 2] + 1) go(cur / 2);
        else go(cur - 1);
    }

}