import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수 구하기
 * - 치즈를 공기 중에 놓으면 녹게 되는데 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
 * - 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다.
 * - 치즈의 구멍을 둘러싼 치즈는 녹지 않고 ‘c’로 표시된 부분만 한 시간 후에 녹아 없어진다.
 */
public class Main {
    static int N, M, ans, time, curSize;
    static int[][] map;
    static int[][] chk;
    static ArrayList<int[]> melt;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 세로와 가로의 길이가 양의 정수로 주어진다. 세로와 가로의 길이는 최대 100이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            if (isClear()) {
                ans = curSize;
                break;
            }
            time++;
            curSize = 0;

            // 녹을 치즈 리스트 구하기
            chk = new int[N][M];
            melt = new ArrayList<>();
            getMelt(0, 0);
            goMelt();
        }

        // 첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고,
        System.out.println(time);
        // 둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.
        System.out.println(ans);
    }

    private static void goMelt() {
        for (int[] p : melt) {
            map[p[0]][p[1]] = 0;
            curSize++;
        }
    }

    private static void getMelt(int y, int x) {
        chk[y][x] = 1;
        if (map[y][x] == 1) {
            melt.add(new int[]{y, x});
            return;
        }
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M || chk[ny][nx] == 1) continue;
            getMelt(ny, nx);
        }
    }

    private static boolean isClear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }

}