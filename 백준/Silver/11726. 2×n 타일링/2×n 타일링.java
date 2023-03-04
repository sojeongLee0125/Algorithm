import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 * <p>
 * 1. 아이디어
 * - n이 1일 때 : 1가지
 * - n이 2일 때 : 2가지
 * - n이 3일 때 : 1 + 2 = 3가지
 * - n이 4일 때 : 2 + 3 = 5가지
 * <p>
 * 2. 시간복잡도
 * - O(N)
 * <p>
 * 3. 자료구조
 * - dp 배열 : int[]
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 5];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        // 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
        System.out.println(dp[n] % 10007);

    }

}