import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
 * - n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다.
 * - 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 * <p>
 * 1. 아이디어
 * - 모든 노드에서 다른 모든 노드 까지 최소비용 : 플로이드 (V^3) <-> 다익스트라 V * ElogV (V^3logV)
 * - 간선의 값을 비용 배열에 반형한 뒤 모든 노드에 대해 해당 노드를 거칠 경우 비용이 작아지면 값을 갱신한다.
 * - 3중 FOR문
 * - 거치는 값(K)
 * - 시작점(I)
 * - 끝점(J)
 * <p>
 * 2. 시간 복잡도
 * - O(V ^ 3)
 * <p>
 * 3. 자료구조
 * - int[][] 간선 가중치 저장
 * - int[][] 정답값 저장 (INF 초기화, 자기자신은 0으로 설정)
 */
public class Main {
    static int[][] map;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다.
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 지도 무한대 초기화, 자기 자신 0 초기화
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                map[i][j] = INF;
            }
        }

        // 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
        for (int i = 0; i < m; i++) {
            // 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다.
            // 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
            map[a][b] = Math.min(map[a][b], c);
        }

        for (int k = 1; k <= n; k++) { // 거치는 도시
            for (int i = 1; i <= n; i++) { // 출발 도시
                for (int j = 1; j <= n; j++) { // 도착 도시
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // n개의 줄을 출력해야 한다.
        // i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다.
        // 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.

        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] >= INF) sb.append("0").append(" ");
                else sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}