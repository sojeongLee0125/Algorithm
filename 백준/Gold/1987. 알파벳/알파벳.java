import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오.
 * - 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 * - 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
 * - 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데,
 * - 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 */
public class Main {

    static int R, C, max;
    static char[][] map;
    static boolean[] alphaChk = new boolean[30];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        // 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        alphaChk[map[0][0] - 'A'] = true;
        go(0, 0, 1);
        System.out.println(max);
    }

    private static void go(int cy, int cx, int cnt) {
        max = Math.max(max, cnt);
        for (int k = 0; k < 4; k++) {
            int ny = cy + dy[k];
            int nx = cx + dx[k];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
            if (alphaChk[map[ny][nx] - 'A']) continue;
            alphaChk[map[ny][nx] - 'A'] = true;
            go(ny, nx, cnt + 1);
            alphaChk[map[ny][nx] - 'A'] = false;
        }
    }

}