import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 방의 정보가 주어졌을 때, T초가 지난 후 구사과의 방에 남아있는 미세먼지의 양을 구해보자.
 * - 공기청정기는 항상 1번 열에 설치되어 있고, 크기는 두 행을 차지한다.
 * - 공기청정기가 설치되어 있지 않은 칸에는 미세먼지가 있고, (r, c)에 있는 미세먼지의 양은 Ar,c이다.
 */
public class Main {

    static int R, C, T, x1, x2, y1, y2, ans;
    static int[][] map;
    static int[][] tmp;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 R, C, T (6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000) 가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        // 둘째 줄부터 R개의 줄에 Ar,c (-1 ≤ Ar,c ≤ 1,000)가 주어진다.
        // 공기청정기가 설치된 곳은 Ar,c가 -1이고, 나머지 값은 미세먼지의 양이다.
        // -1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    y2 = i;
                }
            }
        }

        y1 = y2 - 1;

        while (T-- > 0) {
            // 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
            // 1. (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다. 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
            // 2. 확산되는 양은 Ar,c/5이고 소수점은 버린다.
            // 3. (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.

            // 모든 칸을 반복문으로 돌면서 미세먼지를 확산한다. -> 확산된 먼지는 새로운 멥에 저장한다.

            tmp = new int[R][C];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == -1) continue;
                    go(i, j);
                }
            }

            // 모든 반복문이 다 돌고 난 후 미세먼지 맵의 값과 기존 맵의 값을 더한다.
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == -1) continue;
                    map[i][j] += tmp[i][j];
                }
            }

            // 공기청정기가 작동한다
            // 1. 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
            // 2. 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
            // 3. 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
            int upY = y1;
            int upX = x1;

            int doY = y2;
            int doX = x2;

            // 상단 공기청정기 왼쪽 세로
            for (int i = upY - 1; i > 0; i--) {
                map[i][0] = map[i - 1][0];
            }

            // 상단 공기청정기 위쪽 가로
            for (int i = 0; i < C - 1; i++) {
                map[0][i] = map[0][i + 1];
            }

            // 상단 공기청정기 오른쪽 세로
            for (int i = 0; i < y1; i++) {
                map[i][C - 1] = map[i + 1][C - 1];
            }

            // 상단 공기청정기 아래쪽 가로
            for (int i = C - 1; i > 0; i--) {
                map[y1][i] = map[y1][i - 1];
            }

            // 공기청정기에서 나온 바람은 0
            map[y1][1] = 0;


            // 하단 공기청정기 왼쪽 세로
            for (int i = doY + 1; i < R - 1; i++) {
                map[i][0] = map[i + 1][0];
            }

            // 하단 공기청정기 아래쪽 가로
            for (int i = 0; i < C - 1; i++) {
                map[R - 1][i] = map[R - 1][i + 1];
            }

            // 하단 공기청정기 오른쪽 세로
            for (int i = R - 1; i > y2; i--) {
                map[i][C - 1] = map[i - 1][C - 1];
            }

            // 하단 공기청정기 위쪽 가로
            for (int i = C - 1; i > 0; i--) {
                map[y2][i] = map[y2][i - 1];
            }

            // 공기청정기에서 나온 바람은 0
            map[y2][1] = 0;
        }

        // 첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) continue;
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    private static void go(int i, int j) {
        int cur = map[i][j];
        int div = cur / 5;
        int cnt = 0;
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == -1) continue;
            tmp[ny][nx] += div;
            cnt++;
        }
        map[i][j] -= (div * cnt);
    }

}