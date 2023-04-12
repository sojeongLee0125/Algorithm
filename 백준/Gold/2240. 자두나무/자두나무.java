import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 매 초마다 어느 나무에서 자두가 떨어질지에 대한 정보가 주어졌을 때, 자두가 받을 수 있는 자두의 개수를 구해내는 프로그램을 작성하시오.
 */
public class Main {
    static int T, W;
    static int[] drop; // 각 시간마다 자두가 떨어지는 위치
    static int[][][] dp = new int[1002][32][2]; // 시간, 움직인 횟수, 자두의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 두 정수 T, W가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(st.nextToken()); // 자두가 떨어지는 초
        W = Integer.parseInt(st.nextToken()); // 자두가 움직일 수 있는 횟수
        drop = new int[T];

        // 다음 T개의 줄에는 각 순간에 자두가 떨어지는 나무의 번호가 1 또는 2로 주어진다.
        for (int i = 0; i < T; i++) {
            drop[i] = Integer.parseInt(br.readLine()) - 1;
        }

        // dp 배열 초기화 : -1
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = Math.max(go(0, W, 0), go(0, W - 1, 1));

        System.out.println(answer);
    }

    private static int go(int cnt, int w, int cur) {
        // 기저사례
        if (w < 0) return Integer.MIN_VALUE; // 움직이는 횟수를 다 쓴 경우
        if (cnt == T) return w == 0 ? 0 : Integer.MIN_VALUE; // 끝에 도달했을 경우

        // 메모이제이션
        if (dp[cnt][w][cur] != -1) return dp[cnt][w][cur];

        return dp[cnt][w][cur] = Math.max(go(cnt + 1, w - 1, ((cur + 1) % 2)),
                go(cnt + 1, w, cur)) + (drop[cnt] == cur ? 1 : 0);
    }

}