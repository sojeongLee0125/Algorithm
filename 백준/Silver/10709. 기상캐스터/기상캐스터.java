import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 각 구역에 대해서 지금부터 몇 분뒤 처음으로 하늘에 구름이 오는지를 구하여라.
 * - JOI시는 가로와 세로의 길이가 1킬로미터인 H × W 개의 작은 구역들로 나뉘어 있다.
 * - 북쪽으로부터 i 번째, 서쪽으로부터 j 번째에 있는 구역을 (i, j) 로 표시한다.
 * - 모든 구름은 1분이 지날 때마다 1킬로미터씩 동쪽으로 이동한다.
 * - 오늘은 날씨가 정말 좋기 때문에 JOI시의 외부에서 구름이 이동해 오는 경우는 없다.
 * -  단, 처음부터 구역 (i, j) 에 구름이 떠 있었던 경우에는 0을, 몇 분이 지나도 구름이 뜨지 않을 경우에는 -1을 출력한다.
 */
public class Main {
    static int H, W;
    static char[][] map;
    static int[][] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //  첫 번째 행에는 정수 H, W (1 ≦ H ≦ 100, 1 ≦ W ≦ 100) 가 공백을 사이에 주고 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        time = new int[H][W];

        // H 개의 행의 i번째 행 (1 ≦ i ≦ H) 에는 W문자의 문자열이 주어진다.
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            // 문자 중 j번째 문자 (1 ≦ j ≦ W)
            // 구름이 있는 경우에는 영어 소문자 'c' 가, 구름이 없는 경우에는 문자 '.' 가 주어진다.
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'c') time[i][j] = 0;
                else time[i][j] = -1;
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (time[i][j] == 0) {
                    int cnt = 1;
                    while ((j != W - 1) && time[i][j + 1] == -1) {
                        time[i][j + 1] = cnt++;
                        j++;
                    }
                }
            }
        }

        for (int[] nums : time) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }
}