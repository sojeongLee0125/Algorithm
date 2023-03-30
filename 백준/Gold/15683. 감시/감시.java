import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.
 * - 사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다.
 * - 1번 CCTV는 한 쪽 방향만 감시할 수 있다.
 * - 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다.
 * - 4번은 세 방향, 5번은 네 방향을 감시할 수 있다.
 * - CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다.
 * - 사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.
 * - CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.
 * - CCTV는 CCTV를 통과할 수 있다.
 */
public class Main {

    static int N, M, min = Integer.MAX_VALUE;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] map;
    static ArrayList<int[]> cctv = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다.
        // 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다.
        // CCTV의 최대 개수는 8개를 넘지 않는다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) cctv.add(new int[]{i, j});
            }
        }

        dfs(0);

        // 첫째 줄에 사각 지대의 최소 크기를 출력한다.
        System.out.println(min);
    }

    public static void dfs(int cnt) {
        if (cnt == cctv.size()) {
            updateCnt(); // 사각지대 갯수 갱신
            return;
        }

        ArrayList<int[]> changeList;

        // 방향 회전
        for (int k = 0; k < 4; k++) {
            changeList = install(cnt, k);
            dfs(cnt + 1);
            for (int[] arr : changeList) {
                map[arr[0]][arr[1]] = 0;
            }
        }
    }

    public static ArrayList<int[]> install(int idx, int dir) {
        ArrayList<int[]> change = new ArrayList<>();

        int y = cctv.get(idx)[0];
        int x = cctv.get(idx)[1];

        // 1번 카메라의 경우
        if (map[y][x] == 1) {
            while (true) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (inNotRange(ny, nx)) break;
                if (map[ny][nx] == 0) {
                    map[ny][nx] = 9;
                    change.add(new int[]{ny, nx});
                }
                y = ny;
                x = nx;
            }
        }
        // 2번 카메라의 경우
        else if (map[y][x] == 2) {
            for (int i = 0; i <= 2; i += 2) {
                int ty = y;
                int tx = x;
                while (true) {
                    int ny = ty + dy[(dir + i) % 4];
                    int nx = tx + dx[(dir + i) % 4];
                    if (inNotRange(ny, nx)) break;
                    if (map[ny][nx] == 0) {
                        map[ny][nx] = 9;
                        change.add(new int[]{ny, nx});
                    }
                    ty = ny;
                    tx = nx;
                }
            }
        }
        // 3번 카메라의 경우
        else if (map[y][x] == 3) {
            for (int i = 0; i < 2; i++) {
                int ty = y;
                int tx = x;
                while (true) {
                    int ny = ty + dy[(dir + i) % 4];
                    int nx = tx + dx[(dir + i) % 4];
                    if (inNotRange(ny, nx)) break;
                    if (map[ny][nx] == 0) {
                        map[ny][nx] = 9;
                        change.add(new int[]{ny, nx});
                    }
                    ty = ny;
                    tx = nx;
                }
            }
        }
        // 4번 카메라의 경우
        else if (map[y][x] == 4) {
            for (int i = 0; i < 3; i++) {
                int ty = y;
                int tx = x;
                while (true) {
                    int ny = ty + dy[(dir + i) % 4];
                    int nx = tx + dx[(dir + i) % 4];
                    if (inNotRange(ny, nx)) break;
                    if (map[ny][nx] == 0) {
                        map[ny][nx] = 9;
                        change.add(new int[]{ny, nx});
                    }
                    ty = ny;
                    tx = nx;
                }
            }
        }
        // 5번 카메라의 경우
        else {
            for (int i = 0; i < 4; i++) {
                int ty = y;
                int tx = x;
                while (true) {
                    int ny = ty + dy[(dir + i) % 4];
                    int nx = tx + dx[(dir + i) % 4];
                    if (inNotRange(ny, nx)) break;
                    if (map[ny][nx] == 0) {
                        map[ny][nx] = 9;
                        change.add(new int[]{ny, nx});
                    }
                    ty = ny;
                    tx = nx;
                }
            }
        }
        return change;
    }

    private static boolean inNotRange(int ny, int nx) {
        return ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6;
    }

    private static void updateCnt() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }

        min = Math.min(min, cnt);
    }

}