import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Q. 원판을 T번 회전시킨 후 원판에 적힌 수의 합을 구해보자.
 * - 반지름이 1, 2, ..., N인 원판이 크기가 작아지는 순으로 바닥에 놓여있고, 원판의 중심은 모두 같다.
 * - 원판의 반지름이 i이면, 그 원판을 i번째 원판이라고 한다. 각각의 원판에는 M개의 정수가 적혀있고, i번째 원판에 적힌 j번째 수의 위치는 (i, j)로 표현한다.
 * - (i, 1)은 (i, 2), (i, M)과 인접하다.
 * - (i, M)은 (i, M-1), (i, 1)과 인접하다.
 * - (i, j)는 (i, j-1), (i, j+1)과 인접하다. (2 ≤ j ≤ M-1)
 * <p>
 * - (1, j)는 (2, j)와 인접하다.
 * - (N, j)는 (N-1, j)와 인접하다.
 * - (i, j)는 (i-1, j), (i+1, j)와 인접하다. (2 ≤ i ≤ N-1)
 * <p>
 * - 원판의 회전은 독립적으로 이루어진다.
 * - 원판을 아래와 같은 방법으로 총 T번 회전시키려고 한다.
 * - 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향
 * - 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다
 * - 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다
 * - 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
 */
public class Main {

    static int N, M, T;
    static boolean flag;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] disk; // 원판
    static int[][] chk; // 방문 체크

    private static void rotate(int i, int d, int k) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int j = 0; j < M; j++) {
            list.add(disk[i][j]);
        }

        if (d == 1) {
            Collections.rotate(list, -k);
        } else {
            Collections.rotate(list, k);
        }

        for (int j = 0; j < M; j++) {
            disk[i][j] = list.get(j);
        }
    }

    private static void findAdj() {
        flag = false;
        chk = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disk[i][j] == 0) continue;
                if (chk[i][j] == 1) continue;
                dfs(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (chk[i][j] == 1) disk[i][j] = 0;
            }
        }
    }

    private static void dfs(int y, int x) {
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = (x + dx[k] + M) % M;
            if (ny < 0 || ny >= N || chk[ny][nx] == 1) continue;
            if (disk[y][x] == disk[ny][nx]) {
                chk[ny][nx] = 1;
                chk[y][x] = 1;
                flag = true;
                dfs(ny, nx);
            }
        }
    }

    private static double getMid() {
        double sum = 0;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (disk[i][j] == 0) continue;
                sum += disk[i][j];
                cnt++;
            }
        }

        return sum / cnt;
    }

    private static void change(double mid) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                double num = disk[i][j];
                if (num == 0) continue;
                if (num > mid) {
                    disk[i][j]--;
                } else if (num < mid) {
                    disk[i][j]++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N, M, T이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 원판의 반지름이자 원판의 총 갯수
        M = Integer.parseInt(st.nextToken()); // 원판에 있는 정수들의 갯수
        T = Integer.parseInt(st.nextToken()); // 총 회전수

        // 둘째 줄부터 N개의 줄에 원판에 적힌 수가 주어진다. i번째 줄의 j번째 수는 (i, j)에 적힌 수를 의미한다.
        disk = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                disk[i][j] = num;
            }
        }

        // 다음 T개의 줄에 xi, di, ki가 주어진다.
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // 번호가 x의 배수인 원판
            int d = Integer.parseInt(st.nextToken()); // 0일 경우 시계방향, 1일 경우 반시계 방향
            int k = Integer.parseInt(st.nextToken()); // k칸 회전

            // 1. x-1 의 배수인 원판을 회전시킨다.
            for (int i = x - 1; i < N; i += x) {
                // d 방향으로 k칸 회전시킨다.
                rotate(i, d, k);
            }

            // 2. 인접하면서 수가 같은 것을 모두 찾는다.
            //  인접하면서 같은 수는 모두 0으로 만든다.
            findAdj();

            //  인접하면서 같은 수가 없는 경우 평균을 구해서 평균보다 큰수 -1 평균보다 작은 수 +1을 한다.
            if (!flag) {
                double mid = getMid();
                change(mid);
            }
        }

        //  원판을 T번 회전시킨 후 원판에 적힌 수의 합을 구해보자.
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += disk[i][j];
            }
        }

        System.out.println(answer);
    }
}