import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q.
 * - 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.
 * - 지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.
 * - 불은 각 지점에서 네 방향으로 확산된다.
 * - 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 * - 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
 */
public class Main {
    static int R, C, time, jy, jx;
    static char[][] map;
    static int[][] fireMap, esMap;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static Queue<int[]> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다.
        // 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fireMap = new int[R][C];
        esMap = new int[R][C];

        // 맞왜틀 방지 : 최대값으로 화재 맵을 채운다.
        for (int i = 0; i < R; i++) {
            Arrays.fill(fireMap[i], Integer.MAX_VALUE);
        }

        // 각각의 문자들은 다음을 뜻한다.
        // #: 벽
        // .: 지나갈 수 있는 공간
        // J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
        // F: 불이 난 공간
        // J는 입력에서 하나만 주어진다.

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    esMap[i][j] = 1;
                    jy = i;
                    jx = j;
                } else if (map[i][j] == 'F') {
                    fireMap[i][j] = 1;
                    q.add(new int[]{i, j});
                }
            }
        }

        // 1. 불을 퍼트린다. 시간별로
        goFire();

        // 2. 지훈이가 이동하는데 지훈이 이동시간이 불보다 먼저일경우 진행한다.
        escape();

        // 지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.
        // 지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.
        if (time != 0) System.out.println(time);
        else System.out.println("IMPOSSIBLE");

    }

    private static void goFire() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == '#' || fireMap[ny][nx] != Integer.MAX_VALUE)
                    continue;
                fireMap[ny][nx] = fireMap[cur[0]][cur[1]] + 1;
                q.add(new int[]{ny, nx});
            }
        }
    }

    private static void escape() {
        // 큐로 돌리면서 탈출 진행을 기록한다.
        q = new LinkedList<>();
        q.add(new int[]{jy, jx});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == 0 || cur[1] == 0 || cur[0] == R - 1 || cur[1] == C - 1) {
                // 탈출
                time = esMap[cur[0]][cur[1]];
                break;
            }
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (fireMap[ny][nx] <= esMap[cur[0]][cur[1]] + 1 || map[ny][nx] == '#' || esMap[ny][nx] != 0) continue;
                esMap[ny][nx] = esMap[cur[0]][cur[1]] + 1;
                q.add(new int[]{ny, nx});
            }
        }
    }

}