import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사
 * -  0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
 */
public class Main {
    static int T, N, M, K;
    static int[][] map;
    static int[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스의 개수 T가 주어진다.
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50) 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            chk = new int[N][M];

            // K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }

            // dfs
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && chk[i][j] == 0) {
                        cnt++;
                        chk[i][j] = 1;
                        dfs(i, j);
                    }
                }
            }

            // 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
            System.out.println(cnt);
        }
    }

    private static void dfs(int cy, int cx) {
        for (int k = 0; k < 4; k++) {
            int ny = cy + dy[k];
            int nx = cx + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
            if (map[ny][nx] == 1 && chk[ny][nx] == 0) {
                chk[ny][nx] = 1;
                dfs(ny, nx);
            }
        }
    }

}