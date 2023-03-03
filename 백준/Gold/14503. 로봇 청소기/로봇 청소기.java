import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q.로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.
 * - 방은 N x M 크기의 직사각형으로 나타낼 수 있으며, 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다.
 * - 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북 중 하나이다.
 * - 처음에 빈 칸은 전부 청소되지 않은 상태이다.
 * <p>
 * 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
 * 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
 * 1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
 * 2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
 * 3. 청소되지 않은 빈 칸이 있는 경우,
 * 1. 반시계 방향으로 90도 회전한다.
 * 2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
 * 3. 1번으로 돌아간다
 */
public class Main {

    static int N, M, sy, sx, sd, cnt;
    static int[][] map; // 0 빈칸, 1 벽, 2 청소완료

    // 북 동 남 서
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 방의 크기 N과 M이 입력된다(3 이상 50 이하)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        //둘째 줄에 처음에 로봇 청소기가 있는 칸의 좌표 (r, c)와 처음에 로봇 청소기가 바라보는 방향 d가 입력된다.
        // d: 북, 동, 남 ,서 (0/1/2/3)
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        sd = Integer.parseInt(st.nextToken());

        // 0 : 청소되지 않음 , 1: 벽
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt++;
        dfs(sy, sx, sd);

        //로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
        System.out.println(cnt);
    }

    private static void dfs(int y, int x, int d) {
        map[y][x] = 2; // 청소

        for (int k = 0; k < 4; k++) {
            d--; // 왼쪽 방향으로 회전
            if (d == -1) d = 3;

            int ny = y + dy[d];
            int nx = x + dx[d];

            // 청소가 가능할 경우
            if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 0) {
                cnt++;
                dfs(ny, nx, d);
                // 다시 되돌아와서 해당 위치부터 계산하면 안된다.
                // => 후진할 때만 이전 길을 확인할 수 있으므로 return
                return;
            }
        }

        // 주변에 더 이상 청소할 공간이 없는 경우
        int nd = (d + 2) % 4; // 반대방향 후진
        int ny = y + dy[nd];
        int nx = x + dx[nd];

        // 후진이 가능할 경우
        if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] != 1) {
            dfs(ny, nx, d); // 후진할 때 본래 방향
        }
    }

}