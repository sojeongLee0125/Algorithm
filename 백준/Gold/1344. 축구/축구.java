import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q. 경기가 끝난 후 적어도 한 팀이 골을 소수로 득점할 확률을 구하시오.
 * - 두 팀 중 적어도 한 팀이 골을 소수로 득점할 확률이 궁금해 졌다.
 * - 축구 경기는 90분동안 이루어지고, 분석을 쉽게하기 위해서 경기를 5분 간격으로 나눴다.
 * - 처음 간격은 처음 5분이고, 두 번째 간격은 그 다음 5분, 그리고 이런식으로 나눈다.
 * - 경기가 진행되는 동안 각 간격에서 A팀이 득점할 확률과 B팀이 득점할 확률이 주어진다.
 * - 그리고, 각 간격에서 두 팀은 각각 많아야 한 골을 득점할 수 있다.
 */
public class Main {
    private static final int N = 18;
    private static double a, b;
    private static double[][][] dp = new double[20][20][20];
    private static boolean[] isPrime = new boolean[20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 A팀이 득점할 확률, 둘째 줄에 B팀이 득점할 확률이 퍼센트 단위로 주어진다.
        // 퍼센트 단위로 주어지는 확률은 모두 0보다 크거나 같고 100보다 작거나 같은 정수이다.
        a = Double.parseDouble(br.readLine()) / 100;
        b = Double.parseDouble(br.readLine()) / 100;

        Arrays.fill(isPrime, true);
        markNotPrimeNumber();
        initDp();

        // 첫째 줄에 적어도 한 팀이 골을 소수로 득점할 확률을 출력한다.
        // 정답과의 절대/상대 오차가 10-6이내인 경우에 정답이다.
        System.out.print(game(0, 0, 0));
    }

    private static double game(int cnt, int A, int B) {
        // 경기가 끝났을 때 두 팀 중 한 곳이 소수일 경우 1 리턴
        if (cnt == N) return isPrime[A] || isPrime[B] ? 1.0 : 0.0;

        // 메모이제이션
        if (dp[cnt][A][B] > -0.9) return dp[cnt][A][B];
        dp[cnt][A][B] = 0.0;

        // A팀이 득점할 경우
        dp[cnt][A][B] += game(cnt + 1, A + 1, B) * a * (1 - b);

        // B팀이 득점할 경우
        dp[cnt][A][B] += game(cnt + 1, A, B + 1) * b * (1 - a);

        // 두 팀 모두 득점할 경우
        dp[cnt][A][B] += game(cnt + 1, A + 1, B + 1) * a * b;

        // 두 팀 모두 득점하지 못할 경우
        dp[cnt][A][B] += game(cnt + 1, A, B) * (1 - a) * (1 - b);

        return dp[cnt][A][B];
    }

    private static void initDp() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }

    private static void markNotPrimeNumber() {
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i < isPrime.length; i++) {
            for (int j = i + i; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }
    }
}