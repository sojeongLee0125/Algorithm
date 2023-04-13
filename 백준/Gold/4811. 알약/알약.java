import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q. 가능한 서로 다른 문자열의 개수는 총 몇 개일까?
 * - 약이 N개 담긴 병에서 매일 매일 약 반알을 먹는다.
 * - 첫째 날에 종수는 병에서 약 하나를 꺼낸다. 그 다음, 그 약을 반으로 쪼개서 한 조각은 먹고, 다른 조각은 다시 병에 넣는다.
 * - 다음 날부터 종수는 병에서 약을 하나 꺼낸다. 반 조각이라면 그 약을 먹고, 아니라면 반을 쪼개서 한 조각을 먹고, 다른 조각은 다시 병에 넣는다.
 * - 한 조각을 꺼낸 날에는 W를, 반 조각을 꺼낸 날에는 H 보낸다.
 * - 총 2N일이 지나면 길이가 2N인 문자열이 만들어지게 된다.
 */
public class Main {
    static int answer; // 총 문자열의 갯수
    static int input; // 약의 갯수 입력값
    static long[][] dp = new long[65][65]; // 약의 상태 - 온전 / 반개

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 각 테스트 케이스는 한 줄이며, 병에 들어있는 약의 개수 N ≤ 30 가 주어진다.
        // 입력의 마지막 줄에는 0이 하나 주어진다.
        while ((input = Integer.parseInt(br.readLine())) != 0) {
            answer = 0;

            // dp 초기화
            for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);

            System.out.println(go(input, 0));
        }
    }

    private static long go(int one, int half) {
        // 기저 사례 : 약이 하나도 남지 않은 경우 return 1
        if (one == 0 && half == 0) return 1;

        // 메모이제이션
        if (dp[one][half] != -1) return dp[one][half];
        dp[one][half] = 0;

        // 1개 짜리 약이 남아있을 경우 - 1개 를 먹고 반개를 추가
        if (one > 0) dp[one][half] += go(one - 1, half + 1);

        // 반개짜리 약이 남아있을 경우 - 반개 제거
        if (half > 0) dp[one][half] += go(one, half - 1);

        return dp[one][half];
    }

}