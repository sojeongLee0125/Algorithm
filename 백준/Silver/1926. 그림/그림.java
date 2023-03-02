import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 어떤 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라.
 * - 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의 / 그림의 넓이란 그림에 포함된 1의 개수
 * - 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림
 *
 * 1. 아이디어
 * - 2중 for => 값 1 && 방문 X => BFS
 * - BFS 돌때마다 그림 갯수 + 1, 최댓값 갱신
 *
 * 2. 시간복잡도
 * - BFS : 0(V+E)
 * - V : m * n
 * - E : 4 * V
 * = 500 * 500 * 5 (100만)
 *
 * 3. 자료구조
 * - 그래프 전체 지도 : int[][]
 * - 방문 : boolean[][]
 * - Queue(BFS)
 */
public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][] chk;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        chk = new boolean[n][m];

        // 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다.
        // (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !chk[i][j]) {
                    cnt++;
                    int w = bfs(i, j);
                    max = Math.max(max, w);
                }
            }
        }

        // 첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라.
        // 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.
        System.out.println(cnt);
        System.out.println(max);
    }

    private static int bfs(int i, int j) {
        int rs = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        chk[i][j] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m || chk[ny][nx] || map[ny][nx] == 0) continue;
                chk[ny][nx] = true;
                rs++;
                q.add(new int[]{ny, nx});
            }
        }
        return rs;
    }

}