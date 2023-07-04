import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q. 오민식은 도착 도시에 도착할 때, 가지고 있는 돈의 액수를 최대로 하려고 한다.
 * 이 최댓값을 구하는 프로그램을 작성하시오.
 * <p>
 * - N개의 도시가 있다. 도시는 0번부터 N-1번까지 번호 매겨져 있다.
 * - 오민식의 여행은 A도시에서 시작해서 B도시에서 끝난다.
 * - 오민식이 이용할 수 있는 교통수단은 여러 가지가 있다.
 * - 오민식은 모든 교통수단의 출발 도시와 도착 도시를 알고 있고, 비용도 알고 있다.
 * - 오민식은 각각의 도시를 방문할 때마다 벌 수 있는 돈을 알고있다.
 * - 이 값은 도시마다 다르며, 액수는 고정되어있다.
 * <p>
 * - 오민식이 버는 돈보다 쓰는 돈이 많다면,
 * - 도착 도시에 도착할 때 가지고 있는 돈의 액수가 음수가 될 수도 있다.
 * - 같은 도시를 여러 번 방문할 수 있으며, 그 도시를 방문할 때마다 돈을 벌게 된다.
 * - 모든 교통 수단은 주어진 방향으로만 이용할 수 있으며, 여러 번 이용할 수도 있다.
 */
public class Main {
    static final int INF = 999_999_999;
    static int N, S, E, M, chk;
    static int[] visited, cost;
    static long[] dist = new long[105];
    static ArrayList<ArrayList<int[]>> map = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 도시의 수 N과 시작 도시, 도착 도시
        // 그리고 교통 수단의 개수 M이 주어진다.
        // N과 M은 50보다 작거나 같다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new int[N];
        cost = new int[N];

        // ArrayList 초기화
        for (int i = 0; i < N; i++) map.add(new ArrayList<>());

        // 둘째 줄부터 M개의 줄에는 교통 수단의 정보가 주어진다.
        // 교통 수단의 정보는 “시작 끝 가격”과 같은 형식이다.
        // 돈의 최댓값과 교통 수단의 가격은 1,000,000보다 작거나 같은 음이 아닌 정수이다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            map.get(start).add(new int[]{end, price});
        }

        // 마지막 줄에는 오민식이 각 도시에서 벌 수 있는 돈의 최댓값이 0번 도시부터 차례대로 주어진다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // dist 초기화
        Arrays.fill(dist, -INF);
        dist[S] = cost[S];

        dijkstra();
        bfs();

        // 도착 도시에 도착할 때, 가지고 있는 돈의 액수의 최댓값을 출력한다.
        // 오민식이 도착 도시에 도착하는 것이 불가능할 때는 "gg"를 출력한다.
        // 오민식이 도착 도시에 도착했을 때 돈을 무한히 많이 가지고 있을 수 있다면 "Gee"를 출력한다.
        if (dist[E] == -INF) System.out.println("gg");
        else if (chk == 1) System.out.println("Gee");
        else System.out.println(dist[E]);
    }

    private static void dijkstra() {
        for (int i = 0; i < N; i++) {
            for (int cur = 0; cur < N; cur++) {
                for (int[] nxt : map.get(cur)) {
                    int nxtNode = nxt[0];
                    int nxtDist = nxt[1];

                    if (dist[cur] == -INF) continue;

                    if (dist[nxtNode] < dist[cur] + cost[nxtNode] - nxtDist) {
                        dist[nxtNode] = dist[cur] + cost[nxtNode] - nxtDist;

                        // 도착 지점이 아닐경우
                        if (i == N - 1) queue.offer(cur);
                    }
                }
            }
        }
    }

    private static void bfs() {
        outer:
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int[] nxt : map.get(cur)) {
                if (nxt[0] == E) {
                    chk = 1;
                    break outer;
                }
                if (visited[nxt[0]] == 0) {
                    visited[nxt[0]] = 1;
                    queue.offer(nxt[0]);
                }
            }
        }
    }
}