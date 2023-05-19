import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. N과 비용 행렬이 주어졌을 때, 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하기.
 * - 1번부터 N번 까지 번호가 매겨져 있는 도시들이 있고, 도시들 사이에는 길이 있다.
 * - (길이 없을 수도 있다)
 * - 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐
 * - 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획하려고 한다.
 * - 단, 한 번 갔던 도시로는 다시 갈 수 없다.
 * - 여행 경로는 여러 가지가 있을 수 있는데, 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.
 * - 각 도시간에 이동하는데 드는 비용은 행렬 W[i][j]형태로 주어진다.
 * - W[i][j]는 도시 i에서 도시 j로 가기 위한 비용을 나타낸다. 비용은 대칭적이지 않다.
 * - 모든 도시간의 비용은 양의 정수이다. W[i][i]는 항상 0이다.
 * - 도시 i에서 도시 j로 갈 수 없는 경우도 있으며 이럴 경우 W[i][j]=0
 */
public class Main {
    static int N;
    static int INF = 1_000_000 * 16 + 5;
    static int[][] W;
    static int[][] dp = new int[16][1 << 16];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 도시의 수 N이 주어진다. (2 ≤ N ≤ 16)
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        // 다음 N개의 줄에는 비용 행렬이 주어진다.
        for (int i = 0; i < N; i++) {
            // 각 행렬의 성분은 1,000,000 이하의 양의 정수이며, 갈 수 없는 경우는 0이 주어진다.
            // W[i][j]는 도시 i에서 j로 가기 위한 비용을 나타낸다.
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 16; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 첫째 줄에 외판원의 순회에 필요한 최소 비용을 출력한다.
        System.out.println(tsp(0, 1));
    }

    private static int tsp(int cur, int chk) {
        if (chk == (1 << N) - 1) {
            return W[cur][0] == 0 ? INF : W[cur][0];
        }

        if (dp[cur][chk] != -1) return dp[cur][chk];
        dp[cur][chk] = INF;

        for (int i = 0; i < N; i++) {
            if ((chk & (1 << i)) != 0) continue;
            if (W[cur][i] == 0) continue;
            dp[cur][chk] = Math.min(dp[cur][chk], tsp(i, chk | (1 << i)) + W[cur][i]);
        }

        return dp[cur][chk];
    }
}