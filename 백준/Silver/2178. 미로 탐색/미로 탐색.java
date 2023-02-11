import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. N×M 크기 배열로 표현되는 미로
 * - 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다.
 * - (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성
 * - 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 */
public class Main {
    static int N, M;
    static int[][] map;
    static int[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        chk = new int[N][M];

        // 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        chk[0][0] = 1;
        bfs(0, 0);

        // 지나야 하는 최소의 칸 수를 출력한다.
        System.out.println(chk[N - 1][M - 1]);
    }

    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (map[ny][nx] == 1 && chk[ny][nx] == 0) {
                    chk[ny][nx] = chk[cy][cx] + 1;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

}