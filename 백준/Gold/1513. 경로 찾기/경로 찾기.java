import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 오락실을 0개 방문했을 때부터, C개 방문했을 때 까지 경우의 수를 출력하는 프로그램을 작성하시오.
 * - 세준이는 크기가 N*M인 직사각형 도시에 살고 있다.
 * - 세준이의 집은 (1, 1)에 있고, 학원은 (N, M)에 있고, 오락실이 C개 있다.
 * - 세준이의 현재 위치가 (r, c) 일 때, (r+1, c) 또는 (r, c+1)로만 이동할 수 있다.
 * - 오락실을 방문할 때는 규칙이 하나 있는데, 오락실 번호가 증가하는 순서대로 가야한다는 것이다.
 * - 2번 오락실을 먼저 가고, 그 후에 1번 오락실을 가면 안 되고,
 * - 2번 오락실을 가려면, 그 전에 아무 오락실도 가지 않거나, 1번 오락실을 방문했을 때만 가능하다.
 * - 세준이는 오락실을 K번 방문해서 학원에서 도착하는 경로의 경우의 수가 궁금해지기 시작했다.
 */
public class Main {
    static int N, M, C, mod = 1_000_007;
    static int[][] map;
    static int[][][][] dp = new int[55][55][55][55];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N, M, C가 주어진다.
        // N과 M은 50보다 작거나 같은 자연수이고, C는 50보다 작거나 같은 자연수 또는 0이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 지도 초기화
        map = new int[N + 1][M + 1];

        // dp 배열 초기화
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        // 둘째 줄부터 C개의 줄에 1번 오락실부터 C번 오락실까지 위치가 차례대로 주어진다.
        // 오락실의 위치가 중복되는 경우는 없지만, 오락실의 위치가 (1,1) 또는 (N,M)일 수도 있다.
        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = i;
        }

        // 첫째 줄에 0개 방문했을 때, 1개 방문했을 때, ..., C개 방문했을 때
        // 총 경로의 개수를 한 줄에 공백을 사이에 두고 출력한다.
        // 경로의 개수는 1,000,007로 나눈 나머지를 출력한다.
        for (int i = 0; i <= C; i++) {
            System.out.print(go(0, 0, i, 0) + " ");
        }
    }

    private static int go(int y, int x, int cnt, int prev) {
        // 기저조건
        if (y > N || x > M || cnt < 0) return 0;

        // 도착했을 경우
        if (y == (N - 1) && x == (M - 1)) {
            // 도착지가 오락실이 아닐 경우
            if (cnt == 0 && map[y][x] == 0) return 1;
            // 도착지가 오락실일 경우
            if (cnt == 1 && map[y][x] > prev) return 1;
            // 그 외의 경우
            return 0;
        }

        if (dp[y][x][cnt][prev] != -1) return dp[y][x][cnt][prev];
        dp[y][x][cnt][prev] = 0;

        // 오락실이 아닐 경우
        if (map[y][x] == 0) {
            dp[y][x][cnt][prev] = (go(y + 1, x, cnt, prev) + go(y, x + 1, cnt, prev)) % mod;
        } else if (map[y][x] > prev) {
            // 오락실인데 이전 오락실보다 큰 숫자여서 방문 가능한 경우
            dp[y][x][cnt][prev] = (go(y + 1, x, cnt - 1, map[y][x]) + go(y, x + 1, cnt - 1, map[y][x])) % mod;
        }

        return dp[y][x][cnt][prev];
    }
}