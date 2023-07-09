import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Q. 모두 돌린 다음에 가장 윗 면의 색상을 구하는 프로그램을 작성하시오.
 * - 루빅스 큐브는 3×3×3개의 작은 정육면체로 이루어져 있다.
 * - 퍼즐을 풀려면 각 면에 있는 아홉 개의 작은 정육면체의 색이 동일해야 한다.
 * - 큐브는 각 면을 양방향으로 90도 만큼 돌릴 수 있도록 만들어져 있다.
 * - 회전이 마친 이후에는, 다른 면을 돌릴 수 있다.
 * - 이렇게 큐브의 서로 다른 면을 돌리다 보면, 색을 섞을 수 있다.
 * <p>
 * - 루빅스 큐브가 모두 풀린 상태에서 시작한다.
 * - 윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색,
 * - 왼쪽 면은 초록색, 오른쪽 면은 파란색이다.
 * - 루빅스 큐브를 돌린 방법이 순서대로 주어진다.
 */
public class Main {
    static int T, n, d, e;

    // 각 블록 0-8 / 10-18 / 20-28 / 30-38 / 40-48 / 50-58 라벨링
    static int[] cube = new int[60];

    static int[][] change = {
            {0, 2, 8, 6, 1, 5, 7, 3, 51, 31, 41, 21, 50, 30, 40, 20, 52, 32, 42, 22}, // 윗면 회전 시 변화하는 값들
            {10, 12, 18, 16, 11, 15, 17, 13, 56, 26, 46, 36, 57, 27, 47, 37, 58, 28, 48, 38}, // 아랫면
            {20, 22, 28, 26, 21, 25, 27, 23, 0, 40, 18, 58, 3, 43, 15, 55, 6, 46, 12, 52}, // 옆면1
            {30, 32, 38, 36, 31, 35, 37, 33, 8, 50, 10, 48, 5, 53, 13, 45, 2, 56, 16, 42}, // 옆면2
            {40, 42, 48, 46, 41, 45, 47, 43, 6, 30, 16, 28, 7, 33, 17, 25, 8, 36, 18, 22}, // 옆면3
            {50, 52, 58, 56, 51, 55, 57, 53, 2, 20, 12, 38, 1, 23, 11, 35, 0, 26, 10, 32} // 옆면 4
    };

    static char[] dir = {'U', 'D', 'L', 'R', 'F', 'B'};
    static char[] color = {'w', 'y', 'g', 'b', 'r', 'o'};
    static Map<Character, Integer> map = new HashMap<>();

    private static void initMap() {
        for (int i = 0; i < 6; i++) {
            map.put(dir[i], i);
        }
    }

    private static void initNode() {
        for (int i = 0; i < 60; i++) {
            cube[i] = i / 10;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 문자로 된 방향 -> 숫자로 변경
        initMap();

        // 첫째 줄에 테스트 케이스의 개수가 주어진다.
        // 테스트 케이스는 최대 100개이다.
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 첫째 줄에 큐브를 돌린 횟수 n이 주어진다. (1 ≤ n ≤ 1000)
            n = Integer.parseInt(br.readLine());

            // 각 큐브 번호가 속한 면의 번호를 라벨링
            initNode();

            // 둘째 줄에는 큐브를 돌린 방법이 주어진다.
            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < n; i++) {
                String str = st.nextToken();

                // 각 방법은 공백으로 구분되어져 있으며, 첫 번째 문자는 돌린 면이다.
                // U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면.
                char flat = str.charAt(0);
                int num = map.get(flat);

                // 두 번째 문자는 돌린 방향이다.
                // +인 경우에는 시계 방향 (그 면을 바라봤을 때가 기준),
                // -인 경우에는 반시계 방향이다.
                char dir = str.charAt(1);

                // 시계 방향일 경우 오른쪽으로 3칸씩 rotate
                if (dir == '+') {
                    d = 3;
                    e = 1;
                } else {
                    // 반시계 방향일 경우 왼쪽으로 3칸씩 rotate
                    d = 1;
                    e = 3;
                }

                for (int j = 0; j < 20; j += 4) {
                    int tmp = cube[change[num][j]];
                    cube[change[num][j]] = cube[change[num][j + d]];
                    cube[change[num][j + d]] = cube[change[num][j + 2]];
                    cube[change[num][j + 2]] = cube[change[num][j + e]];
                    cube[change[num][j + e]] = tmp;
                }
            }


            // 각 테스트 케이스에 대해서 큐브를 모두 돌린 후의 윗 면의 색상을 출력한다.
            // 첫 번째 줄에는 뒷 면과 접하는 칸의 색을 출력하고,
            // 두 번째, 세 번째 줄은 순서대로 출력하면 된다.
            // 흰색은 w, 노란색은 y, 빨간색은 r, 오렌지색은 o, 초록색은 g, 파란색은 b.
            for (int i = 0; i < 9; i++) {
                sb.append(color[cube[i]]);
                if (i == 2 || i == 5 || i == 8) sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
