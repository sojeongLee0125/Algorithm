import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.
 * - 새 집의 크기는 N×N의 격자판으로 나타낼 수 있고, 1×1크기의 정사각형 칸으로 나누어져 있다.
 * - 각각의 칸은 (r, c)로 나타낼 수 있다. 행과 열의 번호는 1부터 시작한다. 각각의 칸은 빈 칸이거나 벽이다.
 * - 집 수리를 위해서 파이프 하나를 옮기려고 한다. 2개의 연속된 칸을 차지하는 크기이다.
 * - 파이프를 밀어서 이동시키려고 한다. 파이프가 벽을 긁으면 안 된다. 즉, 파이프는 항상 빈 칸만 차지해야 한다.
 * - 파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다.
 * - 파이프는 밀면서 회전시킬 수 있다. 회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다.
 * - 파이프가 가로로 놓여진 경우에 가능한 이동 방법은 총 2가지, 세로로 놓여진 경우에는 2가지, 대각선 방향으로 놓여진 경우에는 3가지가 있다.
 * - 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다.
 */
public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp; // 방향, y좌표, x좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 집의 크기 N(3 ≤ N ≤ 16)이 주어진다.
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[3][N][N];

        // 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다.
        // 빈 칸은 0, 벽은 1로 주어진다. (1, 1)과 (1, 2)는 항상 빈 칸이다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 초기화
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // 첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력한다.
        // 이동시킬 수 없는 경우에는 0을 출력한다. 방법의 수는 항상 1,000,000보다 작거나 같다.
        System.out.println(go(0, 1, 0));
    }

    private static int go(int y, int x, int dir) {
        // 기저 조건 (끝지점에 도달했을 경우)
        if (y == N - 1 && x == N - 1) return 1;

        // 메모이제이션
        if (dp[dir][y][x] != -1) return dp[dir][y][x];
        dp[dir][y][x] = 0;

        // 여러 방향에서 오는 값들을 합산
        // 현재 가로일 경우 : 가로에서 오는경우 + 대각선에서 오는 경우
        if (dir == 0) {
            if (isOk(y, x, 0)) {
                dp[dir][y][x] += go(y, x + 1, 0);
            }
            if (isOk(y, x, 2)) {
                dp[dir][y][x] += go(y + 1, x + 1, 2);
            }
        } else if (dir == 1) {
            // 현재 세로일 경우 : 세로에서 오는경우 + 대각선에서 오는 경우
            if (isOk(y, x, 1)) {
                dp[dir][y][x] += go(y + 1, x, 1);
            }
            if (isOk(y, x, 2)) {
                dp[dir][y][x] += go(y + 1, x + 1, 2);
            }
        } else {
            // 현재 대각선일 경우 : 가로에서 오는경우 +  세로에서 오는경우 + 대각선에서 오는 경우
            if (isOk(y, x, 0)) {
                dp[dir][y][x] += go(y, x + 1, 0);
            }
            if (isOk(y, x, 1)) {
                dp[dir][y][x] += go(y + 1, x, 1);
            }
            if (isOk(y, x, 2)) {
                dp[dir][y][x] += go(y + 1, x + 1, 2);
            }
        }

        return dp[dir][y][x];
    }

    private static boolean isOk(int y, int x, int chk) {
        if (chk == 0) return x + 1 < N && map[y][x + 1] == 0;
        else if (chk == 1) return y + 1 < N && map[y + 1][x] == 0;
        else {
            return x + 1 < N && y + 1 < N && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0;
        }
    }
}