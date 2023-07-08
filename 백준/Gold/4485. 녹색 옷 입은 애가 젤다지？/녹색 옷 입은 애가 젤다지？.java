import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q. 링크가 잃을 수밖에 없는 최소 금액은 얼마일까?
 * - 젤다의 전설 게임에서 화폐의 단위는 루피(rupee)다.
 * - 도둑 루피라 불리는 검정색 루피도 존재하는데, 오히려 소지한 루피가 감소하게 된다.
 * - 링크는 지금 도둑루피만 가득한 N x N 크기의 동굴의 제일 왼쪽 위에 있다.
 * - 링크는 이 동굴의 반대편 출구, 제일 오른쪽 아래 칸인 [N-1][N-1]까지 이동해야 한다.
 * - 동굴의 각 칸마다 도둑루피가 있는데, 해당 도둑루피의 크기만큼 소지금을 잃게 된다.
 * - 링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며,
 * - 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.
 */
public class Main {
    static int N, cnt;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] map, dist;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 입력은 여러 개의 테스트 케이스로 이루어져 있다.
        while (true) {
            cnt++;

            // 각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다.
            // (2 ≤ N ≤ 125)
            N = Integer.parseInt(br.readLine());

            // N = 0인 입력이 주어지면 전체 입력이 종료된다.
            if (N == 0) break;

            map = new int[N][N];

            // dist 초기화
            dist = new int[N][N]; // 거리, 위치
            for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

            // 이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는
            // 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다.
            // 도둑루피의 크기가 k면 이 칸을 지나면 k 루피를 잃는다는 뜻이다.
            // 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            // 각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다.
            sb.append("Problem ")
                    .append(cnt + ": ")
                    .append(dist[N - 1][N - 1])
                    .append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        pq.offer(new int[]{map[0][0], 0});
        dist[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int cy = cur[1] / 1000;
            int cx = cur[1] % 1000;
            int cd = cur[0];

            if (dist[cy][cx] != cd) continue;

            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

                int nd = map[ny][nx];

                if (dist[ny][nx] > dist[cy][cx] + nd) {
                    dist[ny][nx] = dist[cy][cx] + nd;
                    pq.offer(new int[]{dist[ny][nx], ny * 1000 + nx});
                }
            }
        }
    }
}