import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. R x C 맵에 못가는 부분이 주어지고 거리 K가 주어지면 한수가 집까지도 도착하는 경우 중 거리가 K인 가짓수를 구하는 것이다.
 * - 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다.
 * - 그리고 한수는 집에 돌아가는 방법이 다양하다. 단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다.
 * - T로 표시된 부분은 가지 못하는 부분이다.
 */
public class Main {

    static char[][] map;
    static int[][] chk;
    static int R, C, K, count;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에 정수 R(1 ≤ R ≤ 5), C(1 ≤ C ≤ 5), K(1 ≤ K ≤ R×C)가 공백으로 구분되어 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        chk = new int[R][C];

        // 두 번째부터 R+1번째 줄까지는 R×C 맵의 정보를 나타내는 '.'과 'T'로 구성된 길이가 C인 문자열이 주어진다.
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        chk[R - 1][0] = 1;
        go(R - 1, 0, 1);

        // 첫 줄에 거리가 K인 가짓수를 출력한다.
        System.out.println(count);
    }

    private static void go(int cy, int cx, int cnt) {
        if (cnt > K) return;
        if (cy == 0 && cx == C - 1 && cnt == K) {
            count++;
        } else {
            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || chk[ny][nx] != 0 || map[ny][nx] == 'T') continue;
                chk[ny][nx] = 1;
                go(ny, nx, cnt + 1);
                chk[ny][nx] = 0;
            }
        }
    }

}