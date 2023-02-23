import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 굵은 선은 벽을 나타내고, 점선은 벽이 없어서 지나다닐 수 있는 통로를 나타낸다.
 * - 이러한 형태의 성의 지도를 입력받아서 다음을 계산하는 프로그램을 작성하시오.
 * - 이 성에 있는 방의 개수
 * - 가장 넓은 방의 넓이
 * - 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
 * - 성은 M × N(1 ≤ M, N ≤ 50)개의 정사각형 칸으로 이루어진다.
 * - 성에는 최소 두 개의 방이 있어서, 항상 하나의 벽을 제거하여 두 방을 합치는 경우가 있다.
 */
public class Main {

    static int N, M, max;
    static int[][] map;
    static int[][] chk;
    static int[] size = new int[2505];

    //                서  북  동 남
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫째 줄에 두 정수 N, M이 주어진다.
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        chk = new int[N][M];

        // 서쪽에 벽이 있을 때는 1을, 북쪽에 벽이 있을 때는 2를, 동쪽에 벽이 있을 때는 4를, 남쪽에 벽이 있을 때는 8을 더한 값이 주어진다.
        // 참고로 이진수의 각 비트를 생각하면 쉽다. 따라서 이 값은 0부터 15까지의 범위 안에 있다.
        // 1  1  1 1
        // 남 동 북 서

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫째 줄에 1의 답을, 둘째 줄에 2의 답을, 셋째 줄에 3의 답을 출력한다.
        // 1. 이 성에 있는 방의 개수
        // 2. 가장 넓은 방의 넓이
        // 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

        // 1) 방의 갯수 구하기 => dfs 도는 갯수를 구한다.
        // 2) 가장 넒은 방 => dfs 리턴 값으로 구한다.
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (chk[i][j] == 0) {
                    cnt++;
                    size[cnt] = dfs(i, j, cnt);
                    max = Math.max(max, size[cnt]);
                }
            }
        }

        // 3) 벽제거
        int rm = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i + 1 < N) {
                    int a = chk[i][j];
                    int b = chk[i + 1][j];
                    if (a != b) {
                        rm = Math.max(size[a] + size[b], rm);
                    }
                }
                if (j + 1 < M) {
                    int a = chk[i][j];
                    int b = chk[i][j + 1];
                    if (a != b) {
                        rm = Math.max(size[a] + size[b], rm);
                    }
                }
            }
        }

        System.out.println(cnt);
        System.out.println(max);
        System.out.println(rm);


    }

    private static int dfs(int cy, int cx, int flag) {
        int rs = 1;
        chk[cy][cx] = flag;
        for (int k = 0; k < 4; k++) {
            int ny = cy + dy[k];
            int nx = cx + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M || chk[ny][nx] != 0) continue;
            if ((map[cy][cx] & (1 << k)) != 0) continue;
            rs += dfs(ny, nx, flag);
        }
        return rs;
    }

}