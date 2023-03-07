import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Q. 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
 * - 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
 * - 게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다.
 * - 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.
 * - 뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.
 * - 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
 * - 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
 * - 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
 */
public class Main {

    static int N, K, L;
    static int[][] map;
    static int[][] chk;
    static char[] time = new char[10005];

    //                 위  오  아  왼
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100)
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        chk = new int[N][N];
        Arrays.fill(time, '0');

        // 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
        K = Integer.parseInt(br.readLine());

        // 다음 K개의 줄에는 사과의 위치가 주어지는데,
        // 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = 2;
        }

        // 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
        L = Integer.parseInt(br.readLine());

        // 다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있으며.
        // 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
        // X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            time[X] = C;
        }

        // 풀이 방법
        // 뱀은 0,0 에서 오른쪽으로 이동하면서 시작한다. dy[1] dx[1] - 1
        // 현재 위치에서 다음칸을 탐색한다.
        // 1. 다음 칸에 사과가 있을 경우 해당 칸을 차지한다. - 1
        // 2. 다음 칸에 사과가 없을 경우 해당 칸은 차지하고 맨 뒷 칸은 지운다.
        // 뱀이 이동할 때 한 칸씩 나갈수록 맨 뒷칸은 지워줘야 한다.
        // 다음 칸이 자기 몸이거나 벽이면 게임은 끝난다.

        // deque에 뱀의 현재 위치를 저장한다.
        // 다음에 사과가 있으면 그 위치를 앞에 저장한다.
        // 다음에 사과가 없으면 그 위치는 앞에 저장하고 맨 뒷 좌표는 뺀다.

        Deque<int[]> deque = new ArrayDeque<>();

        int cnt = 0;
        int dir = 1;
        deque.addFirst(new int[]{0, 0});
        chk[0][0] = 1;

        while (true) {
            cnt++;
            int[] cur = deque.peekFirst();
            int ny = cur[0] + dy[dir];
            int nx = cur[1] + dx[dir];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) break;
            if (chk[ny][nx] == 1) break;

            // 사과가 있는 경우
            if (map[ny][nx] == 2) {
                map[ny][nx] = 0;
            } else {
                //사과가 없는 경우
                int[] back = deque.pollLast();
                chk[back[0]][back[1]] = 0;
            }

            chk[ny][nx] = 1;
            deque.addFirst(new int[]{ny, nx});

            if (time[cnt] == 'L') {
                // 왼쪽으로 회전하는 경우 -> dir - 1
                dir = dir == 0 ? 3 : dir - 1;
            } else if (time[cnt] == 'D') {
                // 오른쪽으로 회전하는 경우 -> dir + 1
                dir = dir == 3 ? 0 : dir + 1;
            }
        }

        System.out.println(cnt);
    }

}