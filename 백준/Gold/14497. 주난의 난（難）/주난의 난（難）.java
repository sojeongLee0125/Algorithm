import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 주난이에게 최소 점프 횟수를 알려줘서 교실을 지키자.
 * - 주난이는 N×M크기의 학교 교실 어딘가에서 뛰기 시작했다.
 * - 주난이의 파동은 상하좌우 4방향으로 친구들을 쓰러뜨릴 때까지 계속해서 퍼져나간다.
 * - 한 번의 점프는 한 겹의 친구들을 쓰러뜨린다.
 * - 주난이를 뜻하는 *, 초코바를 가진 학생 #, 0은 장애물이 없는 빈 공간임을 뜻하고, 1은 친구들이 서있음을 의미한다.
 */
public class Main {

    static int N, M, x1, y1, x2, y2, cnt, sw;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static char[][] map;
    static int[][] chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 주난이가 위치한 교실의 크기 N, M이 주어진다. (1 ≤ N, M ≤ 300)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        chk = new int[N][M];

        // 둘째 줄에 주난이의 위치 y1, x1과 범인의 위치 y2, x2가 주어진다. (1 ≤ x1, x2 ≤ N, 1 ≤ y1, y2 ≤ M)
        st = new StringTokenizer(br.readLine(), " ");
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;

        //이후 N×M 크기의 교실 정보가 주어진다. 0은 빈 공간, 1은 친구, *는 주난, #는 범인을 뜻한다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 주난이가 범인을 잡기 위해 최소 몇 번의 점프를 해야 하는지 출력한다.
        jump();
        System.out.println(chk[y2][x2]);
    }

    private static void jump() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y1, x1});
        chk[y1][x1] = 1;
        while (map[y2][x2] == '#') {
            cnt++;
            Queue<int[]> tmp = new LinkedList<>();
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int k = 0; k < 4; k++) {
                    int ny = cur[0] + dy[k];
                    int nx = cur[1] + dx[k];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M || chk[ny][nx] != 0) continue;
                    chk[ny][nx] = cnt;
                    if (map[ny][nx] != '0') {
                        map[ny][nx] = '0';
                        tmp.add(new int[]{ny, nx});
                    } else q.offer(new int[]{ny, nx});
                }
            }
            q = tmp;
        }
    }
}