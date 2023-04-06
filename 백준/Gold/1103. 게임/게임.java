import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;

/**
 * Q. 보드의 상태가 주어졌을 때, 형택이가 최대 몇 번 동전을 움직일 수 있는지 구하는 프로그램을 작성하시오.
 */
public class Main {
    static int N, M;
    static int[][] board;
    static int[][] chk; // 방문체크
    static int[][] dp; // y좌표, x좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다. 이 값은 모두 50보다 작거나 같은 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        chk = new int[N][M];
        dp = new int[N][M];

        // 둘째 줄부터 N개의 줄에 보드의 상태가 주어진다. 쓰여 있는 숫자는 1부터 9까지의 자연수 또는 H이다.
        // 가장 왼쪽 위칸은 H가 아니다. H는 구멍이다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'H') board[i][j] = -1;
                else board[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i=0; i<dp.length; i++) Arrays.fill(dp[i], -1);
        System.out.println(go(0, 0));
    }

    private static int go(int y, int x) {
        // 범위 체크
        if (y < 0 || x < 0 || y >= N || x >= M || board[y][x] == -1) return 0;

        // 기저사례
        if (chk[y][x] == 1) {
            System.out.println(-1);
            System.exit(0);
        }

        // 메모이제이션
        if (dp[y][x] != -1) return dp[y][x];

        // 방문체크
        chk[y][x] = 1;

        // 4방향 체크 후 진행
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        int val = board[y][x];

        for (int k = 0; k < 4; k++) {
            int ny = y + (dy[k] * val);
            int nx = x + (dx[k] * val);
            dp[y][x] = Math.max(dp[y][x], go(ny, nx) + 1);
        }

        // 방문체크 해제
        chk[y][x] = 0;

        return dp[y][x];
    }
}