import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 총 2N일이 지나면 길이가 2N인 문자열이 만들어지게 된다. 이때, 가능한 서로 다른 문자열의 개수?
 * - 70세 박종수 할아버지는 매일 매일 약 반알을 먹는다.
 * - 손녀 선영이는 종수 할아버지에게 약이 N개 담긴 병을 선물로 주었다.
 * - 첫째 날에 종수는 병에서 약 하나를 꺼낸다.
 * - 그 다음, 그 약을 반으로 쪼개서 한 조각은 먹고, 다른 조각은 다시 병에 넣는다.
 * - 다음 날부터 종수는 병에서 약을 하나 꺼낸다.
 * - 반 조각이라면 그 약을 먹고, 아니라면 반을 쪼개서 한 조각을 먹고, 다른 조각은 다시 병에 넣는다.
 * - 종수는 손녀에게 한 조각을 꺼낸 날에는 W를, 반 조각을 꺼낸 날에는 H 보낸다.
 */
public class Main {
    static long[][] dp = new long[31][31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력은 최대 1000개의 테스트 케이스로 이루어져 있다.
        // 각 테스트 케이스는 한 줄이며, 병에 들어있는 약의 개수 N ≤ 30 가 주어진다.
        // 입력의 마지막 줄에는 0이 하나 주어진다.
        // 각 테스트 케이스에 대해서 가능한 문자열의 개수를 출력한다.
        String input;
        while (!(input = br.readLine()).equals("0")) {
            int num = Integer.parseInt(input);
            System.out.println(calc(num, 0));
        }
    }

    private static long calc(int one, int half) {
        // 약을 다 먹은 경우
        if (one == 0 && half == 0) return 1;
        if (dp[one][half] != 0) return dp[one][half];
        if (one > 0) dp[one][half] += calc(one - 1, half + 1);
        if (half > 0) dp[one][half] += calc(one, half - 1);
        return dp[one][half];
    }

}