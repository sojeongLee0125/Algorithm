import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 17471 : 공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다. 인구 차이의 최솟값을 구해보자.
 * - 최대한 공평하게 선거구를 획정하려고 한다.
 * - 백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다.
 * - 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다.
 * - 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다.
 * - 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다.
 */
public class Main {

    static int N, team0, team1, min = Integer.MAX_VALUE;
    static int[] pop, team, chk;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 구역의 개수 N이 주어진다. (2 ≤ N ≤ 10)
        N = Integer.parseInt(br.readLine());
        pop = new int[N];
        map = new ArrayList[N];

        //둘째 줄에 구역의 인구가 1번 구역부터 N번 구역까지 순서대로 주어진다. 인구는 공백으로 구분되어져 있다. 1 ≤ 구역의 인구 수 ≤ 100
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            pop[i] = Integer.parseInt(st.nextToken());
            map[i] = new ArrayList<>();
        }

        // 셋째 줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어진다.
        // 첫 번째 정수는 그 구역과 인접한 구역의 수이고, 이후 인접한 구역의 번호가 주어진다. 모든 값은 정수로 구분되어져 있다.
        // 구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접하다. 인접한 구역이 없을 수도 있다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cnt = Integer.parseInt(st.nextToken());
            while (cnt-- > 0) {
                map[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        // 해결과정
        // 1. N개의 구역을 2개로 나눈다. => 비트마스킹 활용
        // 2. 각 구역이 서로 인접해있는지 확인한다.
        // 3. 인구수의 차이 최소값을 갱신한다.
        for (int i = 1; i < (1 << N); i++) {
            team = new int[N];
            chk = new int[N];
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    team[j] = 1;
                    team1 = j;
                } else {
                    team0 = j;
                }
            }
            int[] ans0 = dfs(team0, 0);
            int[] ans1 = dfs(team1, 1);
            if (ans0[0] + ans1[0] == N) {
                min = Math.min(min, Math.abs(ans0[1] - ans1[1]));
            }
        }

        // 백준시를 두 선거구로 나누었을 때, 두 선거구의 인구 차이의 최솟값을 출력한다. 두 선거구로 나눌 수 없는 경우에는 -1을 출력한다.
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static int[] dfs(int cur, int val) {
        chk[cur] = 1;
        int[] rs = {1, pop[cur]};
        for (int nx : map[cur]) {
            if (team[nx] != val || chk[nx] != 0) continue;
            int[] tmp = dfs(nx, val);
            rs[0] += tmp[0];
            rs[1] += tmp[1];

        }
        return rs;
    }

}