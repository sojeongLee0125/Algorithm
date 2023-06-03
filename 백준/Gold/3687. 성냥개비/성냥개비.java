import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q. 성냥개비의 개수가 주어졌을 때, 성냥개비를 모두 사용해서 만들 수 있는 가장 작은 수와 큰 수
 */
public class Main {
    static int N;
    static int[] nums = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6}; // 0 ~ 9 까지 필요한 성냥개비 수
    static String[] dp = new String[105];
    static String MAX = "11111111111111111111111111111111111111111111111111111"; // 51자리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 테스트 케이스의 개수가 주어진다.
        // 테스트 케이스는 최대 100개 이다.
        // 각 테스트 케이스는 한 줄로 이루어져 있고,
        // 성냥개비의 개수 n이 주어진다. (2 ≤ n ≤ 100)
        int t = Integer.parseInt(br.readLine());
        Arrays.fill(dp, MAX);

        StringBuilder answer = new StringBuilder();

        while (t-- > 0) {
            // 각 테스트 케이스에 대해서 입력으로 주어진 성냥개비를 모두 사용해서
            // 만들 수 있는 가장 작은 수와 가장 큰 수를 출력한다.
            // 두 숫자는 모두 양수이어야 하고, 숫자는 0으로 시작할 수 없다.
            N = Integer.parseInt(br.readLine());
            Arrays.fill(dp, MAX);
            answer.append(getMin(N)).append(" ").append(getMax(N)).append("\n");
        }

        System.out.println(answer);
    }

    private static String getMin(int n) {
        // 성냥을 다 쓴 경우
        if (n == 0) return "";

        // 메모이젱션
        if (!dp[n].equals(MAX)) return dp[n];

        // 숫자 1개씩 추가하며 적용(완탐)
        for (int i = 0; i <= 9; i++) {
            if (n - nums[i] < 0) continue; // 성냥개비가 모자랄 경우
            if (n == N && i == 0) continue; // 숫자는 0으로 시작할 수 없다.
            dp[n] = compareStringMin(dp[n], i + getMin(n - nums[i]));
        }

        return dp[n];
    }

    private static String compareStringMin(String s1, String s2) {
        if (s1.length() == s2.length()) return (s1.compareTo(s2) < 0 ? s1 : s2);
        if (s1.length() > s2.length()) return s2;
        return s1;
    }

    // 자리수를 무조건 크게 만들기
    private static String getMax(int n) {
        StringBuilder sb = new StringBuilder();

        // 홀수일 경우 제일 앞에 7 이후로 1을 붙인다.
        if (n % 2 == 1) {
            sb.append(7);
            n -= 3;
        }

        // 1을 계속 이어 붙이면 된다.
        while (n > 0) {
            sb.append(1);
            n -= 2;
        }

        return sb.toString();
    }
}