import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.
 * -  r행 c열에 있는 나라에는 A[r][c]명이 살고 있다.
 * - 인접한 나라 사이에는 국경선이 존재한다.
 * - 인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
 * - 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
 * - 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 * - 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합
 * - 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
 * - 연합을 해체하고, 모든 국경선을 닫는다.
 */
public class Main {

    static boolean isMove;
    static int N, L, R, day;
    static int[][] pop, union;
    static int[] uniCnt, popCnt;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        pop = new int[N][N];

        // 둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                pop[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 해결 방식
        // 1. 0,0 부터 dfs로 모든 연합을 찾는다.
        // 2. 같은 연합들끼리 인구수를 구한다.
        // 3. 무한 반복문 안에서 이동이 가능한지 체크하고 불가능할 때까지 진행한다.
        while (true) {
            union = new int[N][N];
            uniCnt = new int[2505];
            popCnt = new int[2505];
            isMove = false;

            // 연합을 형성한다.
            int idx = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (union[i][j] == 0) {
                        makeUnion(i, j, idx);
                        idx++;
                    }
                }
            }

            // 연합이 형성되지 않았으면 반복문을 종료한다.
            if (!isMove) break;

            // 인구이동을 진행한다.
            day++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 2개 이상 연합이 형성되었을 경우
                    if (uniCnt[union[i][j]] > 1) {
                        pop[i][j] = popCnt[union[i][j]] / uniCnt[union[i][j]];
                    }
                }
            }
        }

        System.out.println(day);

    }


    private static void makeUnion(int i, int j, int idx) {
        union[i][j] = idx;
        uniCnt[idx]++;
        popCnt[idx] += pop[i][j];
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || union[ny][nx] != 0) continue;
            int diff = Math.abs(pop[i][j] - pop[ny][nx]);
            if (L <= diff && diff <= R) {
                makeUnion(ny, nx, idx);
                isMove = true;
            }
        }
    }


}