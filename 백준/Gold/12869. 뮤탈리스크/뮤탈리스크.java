import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 남아있는 SCV의 체력이 주어졌을 때, 모든 SCV를 파괴하기 위해 공격해야 하는 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 * - 수빈이는 뮤탈리스크 1개가 남아있고, 강호는 SCV N개가 남아있다.
 * - 뮤탈리스크가 공격을 할 때, 한 번에 세 개의 SCV를 공격할 수 있다.
 * - 첫 번째로 공격받는 SCV는 체력 9를 잃는다.
 * - 두 번째로 공격받는 SCV는 체력 3을 잃는다.
 * - 세 번째로 공격받는 SCV는 체력 1을 잃는다.
 * - SCV의 체력이 0 또는 그 이하가 되어버리면, SCV는 그 즉시 파괴된다. 한 번의 공격에서 같은 SCV를 여러 번 공격할 수는 없다.
 */
public class Main {

    static int N, min = Integer.MAX_VALUE;
    static int[] scv;
    static int[][][] chk = new int[65][65][65];
    static ArrayList<int[]> comb = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 SCV의 수 N (1 ≤ N ≤ 3)이 주어진다.
        N = Integer.parseInt(br.readLine());
        scv = new int[3];

        // 둘째 줄에는 SCV N개의 체력이 주어진다. 체력은 60보다 작거나 같은 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        // 총 6가지 경우의 수
        comb.add(new int[]{9, 3, 1});
        comb.add(new int[]{9, 1, 3});
        comb.add(new int[]{3, 9, 1});
        comb.add(new int[]{3, 1, 9});
        comb.add(new int[]{1, 9, 3});
        comb.add(new int[]{1, 3, 9});

        // 해결방법 - 완전 탐색
        // 차례대로 3개를 공격해서 모두 0이 될때까지
        go(scv[0], scv[1], scv[2]);

        // 첫째 줄에 모든 SCV를 파괴하기 위한 공격 횟수의 최솟값을 출력한다.
        System.out.println(chk[0][0][0] - 1);
    }

    private static void go(int c1, int c2, int c3) {
        chk[c1][c2][c3] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{c1, c2, c3});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < comb.size(); i++) {
                int n1 = Math.max(0, cur[0] - comb.get(i)[0]);
                int n2 = Math.max(0, cur[1] - comb.get(i)[1]);
                int n3 = Math.max(0, cur[2] - comb.get(i)[2]);
                if (chk[n1][n2][n3] != 0) continue;
                chk[n1][n2][n3] = chk[cur[0]][cur[1]][cur[2]] + 1;
                q.add(new int[]{n1, n2, n3});
            }
        }
    }


}