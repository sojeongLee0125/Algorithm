import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 톱니바퀴 T개의 초기 상태와 톱니바퀴를 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴의 상태를 구하는 프로그램을 작성하시오.
 * - 총 8개의 톱니를 가지고 있는 톱니바퀴 T개, 가장 왼쪽 톱니바퀴가 1번, 그 오른쪽은 2번, ..., 가장 오른쪽 톱니바퀴는 T번이다.
 * - 이때, 톱니바퀴를 총 K번 회전시키려고 한다. 톱니바퀴의 회전은 한 칸을 기준으로 한다. 회전은 시계 방향과 반시계 방향
 * - 톱니바퀴가 회전할 때, 서로 맞닿은 극에 따라서 옆에 있는 톱니바퀴를 회전시킬 수도 있고, 회전시키지 않을 수도 있다.
 * - 서로 맞닿은 톱니의 극이 다르다면, 회전한 방향과 반대방향으로 회전하게 된다.
 */
public class Main {

    static int T, K, cnt;
    static int[][] tire;
    static ArrayList<int[]> rotate = new ArrayList<>();
    static ArrayList<int[]> tmp = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 톱니바퀴의 개수 T (1 ≤ T ≤ 1,000)가 주어진다.
        T = Integer.parseInt(br.readLine());
        tire = new int[T + 1][8];

        // 톱니바퀴의 상태가 가장 왼쪽 톱니바퀴부터 순서대로 주어진다.
        // 상태는 8개의 정수로 이루어져 있고, 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로 나타나있다.
        for (int i = 1; i <= T; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                tire[i][j] = str.charAt(j) - '0';
            }
        }

        // 다음 줄에는 회전 횟수 K(1 ≤ K ≤ 1,000)가 주어진다.
        K = Integer.parseInt(br.readLine());

        // 다음 K개 줄에는 회전시킨 방법이 순서대로 주어진다.
        // 각 방법은 두 개의 정수로 이루어져 있고, 첫 번째 정수는 회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향이다.
        // 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            rotate.add(new int[]{num, dir});
        }

        for (int i = 0; i < K; i++) {
            // 1. 회전을 시킬바퀴와 회전 방향을 구한다.
            calc(rotate.get(i));
            // 2. 회전 시킨다.
            go(tmp);
        }

        // 총 K번 회전시킨 이후에 12시방향이 S극인 톱니바퀴의 개수를 출력한다.
        System.out.println(find());

    }

    private static int find() {
        int cnt = 0;
        for (int i = 1; i <= T; i++) {
            if (tire[i][0] == 1) cnt++;
        }
        return cnt;
    }

    private static void go(ArrayList<int[]> tmp) {
        for (int[] arr : tmp) {
            int num = arr[0];
            int dir = arr[1];

            if (dir == 1) {
                // 시계방향 회전
                int zero = tire[num][7];
                for (int i = 7; i > 0; i--) {
                    tire[num][i] = tire[num][i - 1];
                }
                tire[num][0] = zero;
            } else {
                // 반시계방향 회전
                int zero = tire[num][0];
                for (int i = 0; i < 7; i++) {
                    tire[num][i] = tire[num][i + 1];
                }
                tire[num][7] = zero;
            }
        }
    }

    private static void calc(int[] rot) {
        tmp = new ArrayList<>();

        int num = rot[0];
        int dir = rot[1];
        tmp.add(new int[]{num, dir});

        // 왼쪽 체크
        for (int i = num - 1; i >= 1; i--) {
            if (tire[i][2] == tire[i + 1][6]) break;
            dir = -dir;
            tmp.add(new int[]{i, dir});
        }

        num = rot[0];
        dir = rot[1];

        // 오른쪽 체크
        for (int i = num + 1; i <= T; i++) {
            if (tire[i][6] == tire[i - 1][2]) break;
            dir = -dir;
            tmp.add(new int[]{i, dir});
        }
    }

}