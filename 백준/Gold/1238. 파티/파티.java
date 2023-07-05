import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q. N명의 학생 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.
 * - N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
 * - N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다.
 * - 마을 사이에는 총 M개의 단방향 도로들이 있고
 * - i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
 * <p>
 * - 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다.
 * - 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
 * - 이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다.
 */
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, X;
    static int[] dist, reverseDist;
    static ArrayList<ArrayList<int[]>> cityMap = new ArrayList<>();
    static ArrayList<ArrayList<int[]>> reverseCityMap = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        initDist();
        initCityMap();

        // 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점,
        // 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다.
        // 시작점과 끝점이 같은 도로는 없으며,
        // 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 가는 방향 오는 방향 2가지를 모두 생각한다.
            cityMap.get(start).add(new int[]{cost, end});
            reverseCityMap.get(end).add(new int[]{cost, start});
        }

        dijkstra(dist, cityMap);
        dijkstra(reverseDist, reverseCityMap);

        // N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            answer = Math.max(answer, dist[i] + reverseDist[i]);
        }

        System.out.println(answer);
    }

    private static void initCityMap() {
        for (int i = 0; i <= N; i++) {
            cityMap.add(new ArrayList<>());
            reverseCityMap.add(new ArrayList<>());
        }
    }

    private static void initDist() {
        dist = new int[N + 5];
        reverseDist = new int[N + 5];
        Arrays.fill(dist, INF);
        Arrays.fill(reverseDist, INF);
    }

    private static void dijkstra(int[] dist, ArrayList<ArrayList<int[]>> cityMap) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        // 시작지점 입력
        pq.add(new int[]{0, X});
        dist[X] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDist = cur[0];
            int curNode = cur[1];

            if (dist[curNode] != curDist) continue;

            for (int[] nxt : cityMap.get(curNode)) {
                int nxtDist = nxt[0];
                int nxtNode = nxt[1];
                if (dist[nxtNode] > dist[curNode] + nxtDist) {
                    dist[nxtNode] = dist[curNode] + nxtDist;
                    pq.offer(new int[]{dist[nxtNode], nxtNode});
                }
            }
        }
    }

}