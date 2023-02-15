import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 도시에 있는 치킨집 중에서 최대 M개를 고르고, 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성하시오.
 * - 크기가 N×N인 도시가 있다.
 * - 도시의 각 칸은 빈 칸, 치킨집, 집 중 하나이다. 0은 빈 칸, 1은 집, 2는 치킨집이다.
 * - 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리
 * - 치킨 거리는 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
 * - 임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|로 구한다.
 */
public class Main {
    static int N, M, min = Integer.MAX_VALUE;
    static int[][] map;
    static int[] chk;
    static ArrayList<int[]> hm = new ArrayList<>();
    static ArrayList<int[]> ck = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        // 0은 빈 칸, 1은 집, 2는 치킨집을 의미한다.
        // 집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다. 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) hm.add(new int[]{i, j});
                else if (map[i][j] == 2) ck.add(new int[]{i, j});
            }
        }

        chk = new int[ck.size()];
        // 해결 방식
        // 1. 치킨집 M개를 고른다. (조합)
        // 2. 해당 치킨집과 집들의 치킨거리를 구한다.
        // 3. 도시의 치킨거리를 구한뒤 최소값을 업데이트 한다.
        sol(0, 0);

        //  폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력한다.
        System.out.println(min);
    }

    private static void sol(int idx, int cnt) {
        if (cnt == M) {
            int ans = 0;
            for (int i = 0; i < hm.size(); i++) {
                int dis = Integer.MAX_VALUE;
                for (int j = 0; j < ck.size(); j++) {
                    if (chk[j] == 0) continue;
                    else
                        dis = Math.min(dis, Math.abs(ck.get(j)[0] - hm.get(i)[0]) + Math.abs(ck.get(j)[1] - hm.get(i)[1]));
                }
                ans += dis;
            }
            min = Math.min(ans, min);
        } else {
            for (int i = idx; i < ck.size(); i++) {
                if (chk[i] == 1) continue;
                chk[i] = 1;
                sol(i + 1, cnt + 1);
                chk[i] = 0;
            }
        }
    }
}