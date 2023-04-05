import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.
 */
public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp = new int[20][20][3]; // y, x , 가로세로대각선

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 집의 크기 N(3 ≤ N ≤ 16)이 주어진다.
        N = Integer.parseInt(br.readLine());
        map = new int[20][20];

        // 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다. 빈 칸은 0, 벽은 1로 주어진다. (1, 1)과 (1, 2)는 항상 빈 칸이다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1; // 최초지점 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 가로의 경우
                if(isOk(i, j+1, 0)) dp[i][j + 1][0] += dp[i][j][0]; // 가로
                if(isOk(i+1, j+1, 2)) dp[i+1][j+1][2] += dp[i][j][0]; // 대각선
                // 세로
                if(isOk(i+1, j, 1)) dp[i+1][j][1] += dp[i][j][1]; // 세로
                if(isOk(i+1, j+1, 2)) dp[i+1][j+1][2] += dp[i][j][1]; // 대각선
                // 대각선
                if(isOk(i, j+1, 0)) dp[i][j + 1][0] += dp[i][j][2]; // 가로
                if(isOk(i+1, j, 1)) dp[i+1][j][1] += dp[i][j][2]; // 세로
                if(isOk(i+1, j+1, 2)) dp[i+1][j+1][2] += dp[i][j][2]; // 대각선
            }
        }

        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
    }

    private static boolean isOk(int y, int x, int dir) {
        if(dir < 2){
            // 가로 세로일경우
            return map[y][x] == 0;
        }else{
            // 대각선일 경우
            return map[y][x] == 0 &&  map[y-1][x] == 0 && map[y][x-1] == 0;
        }
    }

}