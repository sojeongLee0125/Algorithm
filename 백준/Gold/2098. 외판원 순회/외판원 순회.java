import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. N과 비용 행렬이 주어졌을 때, 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하는 프로그램을 작성하시오.
 */
public class Main {
    static int N;
    static int[][] cost;
    static int[][] dp;
    static int NotBack = 16 * 1000000 + 1; // 다시 돌아올 수 없는경우
    static int NotChk = 32 * 1000000; // 방문하지 않은 경우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 도시의 수 N이 주어진다. (2 ≤ N ≤ 16)
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][1 << N];

        // 다음 N개의 줄에는 비용 행렬이 주어진다.
        // 각 행렬의 성분은 1,000,000 이하의 양의 정수이며, 갈 수 없는 경우는 0이 주어진다.
        // W[i][j]는 도시 i에서 j로 가기 위한 비용을 나타낸다.
        // 항상 순회할 수 있는 경우만 입력으로 주어진다.
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열 초기화
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], NotChk);

        // 첫째 줄에 외판원의 순회에 필요한 최소 비용을 출력한다.
        System.out.println(go(0, 1));

    }

    private static int go(int cur, int chk) {
        // 모든 도시를 방문 완료했을 경우
        if (chk == ((1 << N) - 1)) {
            // 원래 도시로 돌아가는 경로가 없다면 INF 리턴
            if (cost[cur][0] == 0) return NotBack;
            return cost[cur][0];
        }

        // 이미 방문했던 루트라면 리턴
        if (dp[cur][chk] != NotChk) return dp[cur][chk];

        for (int i = 0; i < N; i++) {
            if ((chk & (1 << i)) != 0 || cost[cur][i] == 0) continue; // 방문했던 도시라면 pass, 방문 불가라면 pass
            dp[cur][chk] = Math.min(dp[cur][chk], go(i, (chk | (1 << i))) + cost[cur][i]);
        }

        return dp[cur][chk];
    }

}