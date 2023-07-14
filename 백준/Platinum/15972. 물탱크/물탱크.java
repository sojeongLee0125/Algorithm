import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q. 물탱크에 남아 있는 물의 총량(부피)을 계산하는 프로그램을 작성하시오.
 * <p>
 * - 세로 길이가 N, 가로 길이가 M, 높이가 H인 물탱크가 있다.
 * - N, M, H는 모두 양의 정수이다.
 * - 물탱크 내부는 가로와 세로로 벽이 설치되어 있는데,
 * - 내부 각 칸(즉 사각기둥 모양)의 세로와 가로 길이는 1이고
 * - 높이는 H가 되도록 벽이 설치되어 있다.
 * - 이 물탱크를 위에서 내려다보면 각 칸이 정사각형인 격자 모양이 된다.
 * <p>
 * - 물탱크 각 칸의 벽에는 물 높이 조정을 위해 구멍이 뚫려 있을 수 있다.
 * - 각 칸에는 네 개의 벽이 있는데, 각 벽 내부에는 최대 한 개의 구멍이 뚫려 있을 수 있다.
 * - 단, 모서리엔 구멍이 없다.
 * <p>
 * - 물탱크에 물을 채울 땐, 모든 구멍을 마개로 막아 물이 새지 못하도록 한 후,
 * - 격자의 각 칸 위에 설치된 급수 장치를 통해 물탱크 전체를 물로 채운다.
 * - 물이 꽉 찬 후에 구멍을 막고 있는 모든 마개를 제거하면
 * - 물이 구멍을 통해 인접한 방이나 외부로 흘러나가게 된다.
 * - 어느 정도 시간이 지나면 물이 더 이상 흘러 나가지 않게 되고,
 * - 그 때 물탱크 격자의 각 칸에 남아 있는 물의 높이는 서로 다를 수 있다.
 * - 벽의 두께를 무시한다.
 */
public class Main {
    static final int MOD = 1005;
    static int N, M, H;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][][] hole = new int[4][1005][1005];
    static int[][] height = new int[1005][1005];
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 물탱크의 세로 길이, 가로 길이, 높이를 나타내는 세 양의 정수 N, M, H가 차례로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 첫 번째 가로 벽에 설치된 구멍 정보를 나타내는 M개의 정수가 주어진다
        // 각 정수는 왼쪽부터 시작하여 순서대로 각 칸의 구멍 높이를 의미한다.
        // 구멍의 높이는 0 이상 H 미만의 정수이다.
        // 해당 벽에 구멍이 없는 경우는 –1로 표시한다.
        // N+1 줄에 걸쳐 가로 벽에 설치된 모든 구멍에 대한 정보가 주어진다.
        for (int i = 1; i <= N + 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; j++) {
                int holeHeight = Integer.parseInt(st.nextToken());
                // 위 아래 부분 홀 높이 입력
                hole[2][i - 1][j] = holeHeight;
                hole[0][i][j] = holeHeight;
            }
        }

        // 첫 번째 가로 벽을 공유하는 칸들의 세로 벽에 설치된
        // 구멍 정보를 나타내는 M+1개의 정수가 주어진다.
        // 각 정수는 첫 번째 세로 벽부터 시작하여 차례로 각 세로 벽의 구멍 높이를 나타낸다.
        // N줄에 걸쳐 세로 벽에 설치된 모든 구멍 정보가 주어진다.
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M + 1; j++) {
                int holeHeight = Integer.parseInt(st.nextToken());
                // 왼쪽 오른쪽 홀 높이 입력
                hole[1][i][j - 1] = holeHeight;
                hole[3][i][j] = holeHeight;
            }
        }

        // 초기 높이 입력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                height[i][j] = H;
            }
        }

        // 가장자리 hole -> 최소값
        for (int i = 0; i <= N + 1; i++) {
            //가장 왼쪽, 오른쪽 확인
            if (hole[1][i][0] != -1) push(i, 1, hole[1][i][0]);
            if (hole[3][i][M + 1] != -1) push(i, M, hole[3][i][M + 1]);
        }

        // 가장자리 hole -> 최소값
        for (int i = 0; i <= M + 1; i++) {
            //가장 위쪽, 아래쪽 확인
            if (hole[2][0][i] != -1) push(1, i, hole[2][0][i]);
            if (hole[0][N + 1][i] != -1) push(N, i, hole[0][N + 1][i]);
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int ch = cur[0];
            int cy = cur[1] / MOD;
            int cx = cur[1] % MOD;

            if (height[cy][cx] != ch) continue;

            for (int k = 0; k < 4; k++) {
                if (hole[k][cy][cx] == -1) continue;
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if (ny < 1 || ny > N || nx < 1 || nx > M) continue;

                int nh = Math.max(height[cy][cx], Math.min(hole[k][cy][cx], height[ny][nx]));

                if (height[ny][nx] > nh) {
                    height[ny][nx] = nh;
                    pq.offer(new int[]{nh, MOD * ny + nx});
                }
            }
        }

        // 물이 더 이상 흘러 나가지 않는 시점에 
        // 물탱크에 남아 있는 물의 총량(부피)을 표준 출력으로 출력한다.
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                answer += height[i][j];
            }
        }

        System.out.println(answer);

    }

    private static void push(int y, int x, int h) {
        if (height[y][x] > h) {
            height[y][x] = h;
            pq.offer(new int[]{h, MOD * y + x});
        }
    }
}