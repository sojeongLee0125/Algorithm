import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 자연수 N개와 질문 M개가 모두 주어졌을 때, 명우의 대답을 구하는 프로그램을 작성하시오.
 * - 명우는 홍준이와 함께 팰린드롬 놀이를 해보려고 한다.
 * - 홍준이는 자연수 N개를 칠판에 적는다. 그 다음, 명우에게 질문을 총 M번 한다.
 * - 각 질문은 두 정수 S와 E(1 ≤ S ≤ E ≤ N)로 나타낼 수 있으며,
 * - S번째 수부터 E번째 까지 수가 팰린드롬을 이루는지를 물어보며,
 * - 명우는 각 질문에 대해 팰린드롬이다 또는 아니다를 말해야 한다.
 */
public class Main {
    static int N, M;
    static int[] nums = new int[4005];
    static int[][] dp = new int[2005][2005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 수열의 크기 N (1 ≤ N ≤ 2,000)이 주어진다.
        N = Integer.parseInt(br.readLine());

        // 홍준이가 칠판에 적은 수 N개가 순서대로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 칠판에 적은 수는 100,000보다 작거나 같은 자연수이다.
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // dp 배열 중 자기 자신 초기화
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        // dp 배열 중 연속된 범위 초기화
        for (int i = 1; i <= N; i++) {
            if (nums[i] == nums[i + 1]) dp[i][i + 1] = 1;
        }

        // 2개부터 N개 까지 펠린드롬 조사
        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N; i++) {
                if (nums[i] == nums[i + len] && dp[i + 1][i + len - 1] == 1) dp[i][i + len] = 1;
            }
        }

        // 셋째 줄에는 홍준이가 한 질문의 개수 M (1 ≤ M ≤ 1,000,000)이 주어진다.
        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sb.append(dp[from][to]).append("\n");
        }

        System.out.println(sb);
    }
}