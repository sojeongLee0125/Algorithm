import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 보드의 상태가 주어졌을 때, 형택이가 최대 몇 번 동전을 움직일 수 있는지 구하는 프로그램을 작성하시오.
 * - 형택이는 1부터 9까지의 숫자와, 구멍이 있는 직사각형 보드에서 재밌는 게임을 한다.
 * - 보드의 가장 왼쪽 위에 동전을 하나 올려놓는다. 그다음에 다음과 같이 동전을 움직인다.
 * - 1. 동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
 * - 2. 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
 * - 3. 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.
 * - 만약 동전이 구멍에 빠지거나, 보드의 바깥으로 나간다면 게임은 종료된다.
 */
public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int N, M;
    static int[][] map, chk, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다.
        // 이 값은 모두 50보다 작거나 같은 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        chk = new int[N][M];
        dp = new int[N][M];

        // 둘째 줄부터 N개의 줄에 보드의 상태가 주어진다.
        // 쓰여 있는 숫자는 1부터 9까지의 자연수 또는 H이다.
        // 가장 왼쪽 위칸은 H가 아니다. H는 구멍이다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'H') map[i][j] = -1;
                else map[i][j] = str.charAt(j) - '0';
            }
        }

        // 첫째 줄에 문제의 정답을 출력한다. 만약 형택이가 동전을 무한번 움직일 수 있다면 -1을 출력한다.
        System.out.println(move(0, 0));
    }

    private static int move(int y, int x) {
        // 유효하지 않은 범위일 경우
        if (!isValid(y, x) || map[y][x] == -1) return 0;
        
        // 이미 왔던 경로
        if (chk[y][x] != 0) {
            System.out.println(-1);
            System.exit(0);
        }

        if (dp[y][x] != 0) return dp[y][x];
        chk[y][x] = 1;
        int val = map[y][x];

        for (int k = 0; k < 4; k++) {
            int ny = y + (dy[k] * val);
            int nx = x + (dx[k] * val);
            dp[y][x] = Math.max(dp[y][x], move(ny, nx) + 1);
        }

        chk[y][x] = 0;
        return dp[y][x];
    }

    private static boolean isValid(int y, int x) {
        return y < N && x < M && y >= 0 && x >= 0;
    }
}