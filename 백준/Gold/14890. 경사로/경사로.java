import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q.지도가 주어졌을 때, 지나갈 수 있는 길의 개수를 구하는 프로그램을 작성하시오.
 * - 크기가 N×N인 지도가 있다. 지도의 각 칸에는 그 곳의 높이가 적혀져 있다.
 * - 이 지도에서 지나갈 수 있는 길이 몇 개 있는지 알아보려고 한다. 길이란 한 행 또는 한 열 전부를 나타내며, 한쪽 끝에서 다른쪽 끝까지 지나가는 것이다.
 * - 길을 지나갈 수 있으려면 길에 속한 모든 칸의 높이가 모두 같아야 한다. 또는, 경사로를 놓아서 지나갈 수 있는 길을 만들 수 있다.
 * - 경사로는 높이가 항상 1이며, 길이는 L이다. 또, 개수는 매우 많아 부족할 일이 없다.
 * - 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
 * - 낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
 * - 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
 */
public class Main {

    static int N, L, cnt;
    static int[][] map;
    static int[][] reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N (2 ≤ N ≤ 100)과 L (1 ≤ L ≤ N)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        reverse = new int[N][N];

        // 둘째 줄부터 N개의 줄에 지도가 주어진다. 각 칸의 높이는 10보다 작거나 같은 자연수이다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                reverse[j][i] = map[i][j];
            }
        }

        // 행 검사
        checkMap(map);
        // 열 검사
        checkMap(reverse);
        // 첫째 줄에 지나갈 수 있는 길의 개수를 출력한다.
        System.out.println(cnt);
    }

    private static void checkMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            int idx = 1;
            int j;
            for (j = 0; j < N - 1; j++) {
                if (map[i][j] == map[i][j + 1]) idx++;
                else if (map[i][j] + 1 == map[i][j + 1] && idx >= L) idx = 1;
                else if (map[i][j] - 1 == map[i][j + 1] && idx >= 0) idx = -L + 1;
                else break;
            }
            if (j == N - 1 && idx >= 0) cnt++;
        }
    }
}