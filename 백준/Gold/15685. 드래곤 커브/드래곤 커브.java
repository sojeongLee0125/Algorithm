import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 크기가 100×100인 격자 위에 드래곤 커브가 N개 있다.
 * 이때, 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구하는 프로그램을 작성하시오.
 * 격자의 좌표는 (x, y)로 나타내며, 0 ≤ x ≤ 100, 0 ≤ y ≤ 100만 유효한 좌표이다.
 * - 0세대 드래곤 커브는 길이가 1인 선분이다.
 * - 1세대 드래곤 커브는 0세대 드래곤 커브를 끝 점을 기준으로 시계 방향으로 90도 회전시킨 다음 0세대 드래곤 커브의 끝 점에 붙인 것이다.
 * - 끝 점이란 시작 점에서 선분을 타고 이동했을 때, 가장 먼 거리에 있는 점을 의미한다.
 * - 2세대 드래곤 커브도 1세대를 만든 방법을 이용해서 만들 수 있다.
 * - 3세대 드래곤 커브도 2세대 드래곤 커브를 이용해 만들 수 있다.
 * - K(K > 1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것이다.
 */
public class Main {

    static int N;
    static int[][] chk = new int[101][101];
    static ArrayList<Integer>[][] dragons = new ArrayList[4][11];

    // 방향은 0, 1, 2, 3 중 하나이고, 다음을 의미한다.
    //                 오  위  왼  아
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 드래곤 커브의 개수 N(1 ≤ N ≤ 20)이 주어진다.
        N = Integer.parseInt(br.readLine());

        // 방향의 규칙
        // 0
        // 1
        // 2(1+1)-1
        // 2(1+1) - 3(2+1) - 2 - 1

        getDragons();

        // 둘째 줄부터 N개의 줄에는 드래곤 커브의 정보가 주어진다.
        // 드래곤 커브의 정보는 네 정수 x, y, d, g로 이루어져 있다.
        // x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대이다. (0 ≤ x, y ≤ 100, 0 ≤ d ≤ 3, 0 ≤ g ≤ 10)
        // 입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다. 드래곤 커브는 서로 겹칠 수 있다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            go(y, x, d, g);
        }

        // 첫째 줄에 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 출력한다.
        System.out.println(get());


    }

    private static int get() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (chk[i][j] == 1 && chk[i + 1][j] == 1 && chk[i][j + 1] == 1 && chk[i + 1][j + 1] == 1) cnt++;
            }
        }

        return cnt;
    }

    private static void go(int y, int x, int d, int g) {
        chk[y][x] = 1;
        for (int i = 0; i <= g; i++) {
            for (int dir : dragons[d][i]) {
                y += dy[dir];
                x += dx[dir];
                chk[y][x] = 1;
            }
        }
    }

    // 1세대 부터 10세대 드래곤 방향 미리 생성
    private static void getDragons() {
        // 4군데 방향 모두 작성
        for (int i = 0; i < 4; i++) {
            dragons[i] = new ArrayList[11];
            for (int l = 0; l < 11; l++) dragons[i][l] = new ArrayList<>();
            dragons[i][0].add(i); // 0세대
            dragons[i][1].add((i + 1) % 4); // 1 세대
            for (int j = 2; j <= 10; j++) { // 2 ~ 10 세대
                int n = dragons[i][j - 1].size();
                // 이전 세대 역순 + 1 방향 저장
                for (int k = n - 1; k >= 0; k--) {
                    dragons[i][j].add((dragons[i][j - 1].get(k) + 1) % 4);
                }
                // 이전 세대 정보 그대로 저장
                for (int k = 0; k < n; k++) {
                    dragons[i][j].add(dragons[i][j - 1].get(k));
                }
            }
        }
    }

}