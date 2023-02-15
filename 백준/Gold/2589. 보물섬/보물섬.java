import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성
 * - 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다.
 * - 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다.
 * - 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다.
 */
public class Main {

    static int N, M, ans;
    static char[][] map;
    static int[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 보물 지도의 세로의 크기와 가로의 크기가 빈칸을 사이에 두고 주어진다. 가로, 세로의 크기는 각각 50이하이다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 해결방안
        // 1. 육지를 발견하면 bfs를 돌려서 해당 육지에서 최대한 멀어진 곳의 거리값을 구한다.
        // 2. 해당 값 중 최대값을 구한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    chk = new int[N][M];
                    chk[i][j] = 1;
                    bfs(i, j);
                }
            }
        }

        // 첫째 줄에 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간을 출력한다.
        System.out.println(ans - 1);

    }

    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || chk[ny][nx] != 0 || map[ny][nx] != 'L') continue;
                chk[ny][nx] = chk[cur[0]][cur[1]] + 1;
                ans = Math.max(ans, chk[ny][nx]);
                q.add(new int[]{ny, nx});
            }
        }
    }

}