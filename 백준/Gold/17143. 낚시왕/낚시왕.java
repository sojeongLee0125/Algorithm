import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.StringTokenizer;

/**
 * Q. 낚시왕이 상어 낚시를 하는 격자판의 상태가 주어졌을 때, 낚시왕이 잡은 상어 크기의 합을 구해보자.
 * - (R, C)는 아래 그림에서 가장 오른쪽 아래에 있는 칸이다.
 * - 칸에는 상어가 최대 한 마리 들어있을 수 있다. 상어는 크기와 속도를 가지고 있다.
 * - 낚시왕은 처음에 1번 열의 한 칸 왼쪽에 있다. 다음은 1초 동안 일어나는 일이며, 아래 적힌 순서대로 일어난다.
 * - 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
 * - 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
 * - 3. 상어가 이동한다.
 * - 낚시왕은 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.
 * - 상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸/초이다.
 * - 상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.
 * - 상어가 보고 있는 방향이 속도의 방향, 왼쪽 아래에 적힌 정수는 속력이다. 왼쪽 위에 상어를 구분하기 위해 문자를 적었다.
 * - 상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다. 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
 */
public class Main {
    static int R, C, M, sum;
    static Shark[] sharks;
    static int[][] tmp;
    static int[][] map;

    //                 위  아  오 왼
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static class Shark {
        int r;
        int c;
        int s;
        int d;
        int z;
        boolean dead = false;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 격자판의 크기 R, C와 상어의 수 M이 주어진다. (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        sharks = new Shark[M + 1];

        // 둘째 줄부터 M개의 줄에 상어의 정보가 주어진다.
        // 상어의 정보는 다섯 정수 r, c, s, d, z (1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000) 로 이루어져 있다.
        // (r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기이다.
        // d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            // 위 아래의 경우(왕복 한 세트를 나눈다.)
            if (d <= 1) s %= 2 * (R - 1);
            else {
                // 오른쪽 왼쪽의 경우 (왕복 한 세트를 나눈다.)
                s %= 2 * (C - 1);
            }

            sharks[i] = new Shark(r, c, s, d, z);
            map[r][c] = i;
        }

        // 순서
        // 1. 낚시왕이 오른쪽으로 이동한다.
        // 2. 땅에서 가장 가까운 상어를 잡는다.
        // 3. 상어들이 이동한다. (경계를 만날 경우 방향전환해서 진행)
        // 4. 한 칸에 두개 이상 상어가 있으면 잡아먹는다.

        int time = 0;
        while (time < C) {
            fishing(time);
            moving();
            time++;
        }

        // 낚시왕이 잡은 상어 크기의 합을 출력한다.
        System.out.println(sum);
    }

    private static void fishing(int time) {
        for (int y = 0; y < R; y++) {
            if (map[y][time] != 0) {
                sharks[map[y][time]].dead = true;
                sum += sharks[map[y][time]].z;
                map[y][time] = 0;
                break;
            }
        }
    }

    private static void moving() {
        tmp = new int[R][C];

        for (int i = 1; i <= M; i++) {
            Shark s = sharks[i];
            if (s.dead) continue;

            int y = s.r;
            int x = s.c;
            int sok = s.s;
            int dir = s.d;
            int ny = 0;
            int nx = 0;

            while (true) {
                ny = y + dy[dir] * sok;
                nx = x + dx[dir] * sok;
                if (ny >= 0 && nx >= 0 && ny < R && nx < C) break;
                if (dir <= 1) {
                    // 위아래
                    // 원점에서 출발하는 방식으로 만들기
                    if (ny < 0) {
                        sok = sok - y;
                        y = 0;
                    } else {
                        sok = sok - (R - 1 - y);
                        y = R - 1;
                    }
                } else {
                    // 오른쪽 왼쪽
                    // 끝점 출발방식으로 바꾸기
                    if (nx < 0) {
                        sok = sok - x;
                        x = 0;
                    } else {
                        sok = sok - (C - 1 - x);
                        x = C - 1;
                    }
                }

                dir ^= 1; // 위 아래 - 오 왼 방향 전환
            }

            if (tmp[ny][nx] != 0) {
                if (sharks[tmp[ny][nx]].z < s.z) {
                    sharks[tmp[ny][nx]].dead = true;
                    tmp[ny][nx] = i;
                } else sharks[i].dead = true;
            } else tmp[ny][nx] = i;

            sharks[i].r = ny;
            sharks[i].c = nx;
            sharks[i].d = dir;
        }

        for (int i = 0; i < tmp.length; i++) {
            System.arraycopy(tmp[i], 0, map[i], 0, tmp[i].length);
        }
    }


}