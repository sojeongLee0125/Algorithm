import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 배열 A와 사용 가능한 회전 연산이 주어졌을 때, 배열 A의 값의 최솟값을 구해보자. 회전 연산은 모두 한 번씩 사용해야 하며, 순서는 임의로 정해도 된다.
 * - 크기가 N×M 크기인 배열 A가 있을때, 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값을 의미한다.
 * - 배열은 회전 연산을 수행할 수 있다. 회전 연산은 세 정수 (r, c, s)로 이루어져 있고,
 * - 가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을 시계 방향으로 한 칸씩 돌린다는 의미이다
 * - 회전 연산이 두 개 이상이면, 연산을 수행한 순서에 따라 최종 배열이 다르다.
 */
public class Main {

    static int N, M, K, min = Integer.MAX_VALUE;
    static int[][] arr;
    static ArrayList<int[]> rotate = new ArrayList<>();
    static int[] chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 배열 A의 크기 N, M, 회전 연산의 개수 K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        chk = new int[K];

        // 둘째 줄부터 N개의 줄에 배열 A에 들어있는 수 A[i][j]가 주어지고,
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 다음 K개의 줄에 회전 연산의 정보 r, c, s가 주어진다.
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            rotate.add(new int[]{r, c, s});
        }

        // 해결 방안
        // 1. K 번의 완전탐색을 통해 회전연산을 수행한다. 이때 기저 조건은 CNT == K, 이때 각 행의 합의 최소값을 갱신한다.
        // 2. 회전 연산 (r-s, c-s ~ r+s,c+s의 회전연산을 수행하면서 제자리로 돌아오면 r-s + 1, c-s + 1을 원점으로 시작한다.)
        // 3. 만약 원점이 최소 / 2 보다 작거나 같을때 까지만 수행한다.
        rot(0, arr);
        System.out.println(min);
    }

    private static void rot(int cnt, int[][] arr) {
        if (cnt == K) {
            // 각 행마다의 합을 구하고 최소값을 갱신한다.
            calc(arr);
            return;
        }
        // 회전 연산 순열 실시
        for (int i = 0; i < K; i++) {
            if (chk[i] == 0) {
                chk[i] = 1;
                rot(cnt + 1, change(rotate.get(i), arr));
                chk[i] = 0;
            }
        }

    }

    private static int[][] change(int[] rot, int[][] arr) {
        int[][] tmp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = arr[i][j];
            }
        }

        int r = rot[0];
        int c = rot[1];
        int s = rot[2];

        int y = r - s;
        int x = c - s;

        int lastY = ((r - s) + (r + s)) / 2;
        int lastX = ((c - s) + (c + s)) / 2;

        int cnt = 0;
        while (true) {
            // 윗 쪽 가로 진행
            while (true) {
                tmp[y][x + 1] = arr[y][x++];
                if (x == (c + s) - cnt) break;
            }
            // 오른쪽 세로 진행
            while (true) {
                tmp[y + 1][x] = arr[y++][x];
                if (y == (r + s) - cnt) break;
            }
            // 아래 쪽 가로 진행
            while (true) {
                tmp[y][x - 1] = arr[y][x--];
                if (x == (c - s) + cnt) break;
            }
            // 왼쪽 세로 진행
            while (true) {
                tmp[y - 1][x] = arr[y--][x];
                if (y == (r - s) + cnt) break;
            }
            y++;
            x++;
            cnt++;
            if (y >= lastY && x >= lastX) break;
        }
        return tmp;
    }

    private static void calc(int[][] arr) {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += arr[i][j];
            }
            min = Math.min(min, sum);
        }
    }

}