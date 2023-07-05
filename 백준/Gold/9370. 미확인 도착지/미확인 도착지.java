import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q. 듀오는 대체 어디로 가고 있는 것일까?
 * - 서커스 예술가 한 쌍이 한 도시의 거리들을 이동하고 있다.
 * - 임무는 그들이 어디로 가고 있는지 알아내는 것이다.
 * - 그들은 s 지점에서 출발했다. 목적지 후보들 중 하나가 그들의 목적지이다.
 * - 목적지까지 우회하지 않고 최단거리로 갈 것이다.
 * - 당신은 후각이 개만큼 뛰어나다.
 * - 이 후각으로 그들이 g와 h 교차로 사이에 있는 도로를 지나갔다는 것을 알아냈다.
 */
public class Main {
    static int n, m, t, s, g, h;
    static int[] dist;
    static int[] candidate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에는 테스트 케이스의 T(1 ≤ T ≤ 100)가 주어진다.
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            // 첫 번째 줄에 3개의 정수 n, m, t
            // (2 ≤ n ≤ 2 000, 1 ≤ m ≤ 50 000 and 1 ≤ t ≤ 100)가 주어진다.
            // 각각 교차로, 도로, 목적지 후보의 개수이다.
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            dist = new int[n + 5];
            // 짝수 큰수로 초기화
            Arrays.fill(dist, 999_999_992);

            ArrayList<int[]>[] city = new ArrayList[n + 5];
            for (int i = 0; i <= n; i++) city[i] = new ArrayList<>();

            // 두 번째 줄에 3개의 정수 s, g, h (1 ≤ s, g, h ≤ n)가 주어진다.
            // s는 예술가들의 출발지이고, g, h는 문제 설명에 나와 있다. (g ≠ h)
            st = new StringTokenizer(br.readLine(), " ");
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 그 다음 m개의 각 줄마다 3개의 정수 a, b, d
            // (1 ≤ a < b ≤ n and 1 ≤ d ≤ 1 000)가 주어진다.
            // a와 b 사이에 길이 d의 양방향 도로가 있다는 뜻이다.
            // 교차로 사이에는 도로가 많아봐야 1개이다.

            // m개의 줄 중에서 g와 h 사이의 도로를 나타낸 것이 존재한다.
            // 또한 이 도로는 목적지 후보들 중 적어도 1개로 향하는 최단 경로의 일부이다.
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                // 모든 간선을 짝수로 만든다
                d *= 2;

                // g-h 교차로 사리 도로일 경우 홀수로 만든다.
                if ((a == g && b == h) || (a == h && b == g)) d--;

                city[a].add(new int[]{d, b});
                city[b].add(new int[]{d, a});
            }

            // 그 다음 t개의 각 줄마다 정수 x가 주어지는데,
            // t개의 목적지 후보들을 의미한다.
            // 이 t개의 지점들은 서로 다른 위치이며 모두 s와 같지 않다.

            candidate = new int[t + 1];

            for (int i = 0; i < t; i++) {
                candidate[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(candidate);
            dijkstra(city);

            // 입력에서 주어진 목적지 후보들 중 불가능한 경우들을 제외한
            // 목적지들을 공백으로 분리시킨 오름차순의 정수들로 출력한다.
            for (int i = 0; i <= t; i++) {
                // g-h 사이 도로를 지나게 되면 반드시 홀수가 된다.
                if (dist[candidate[i]] % 2 == 1) {
                    sb.append(candidate[i]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(ArrayList<int[]>[] city) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        // 시작점 입력
        pq.offer(new int[]{0, s});
        dist[s] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[1];
            int curDist = cur[0];

            if (dist[curNode] != curDist) continue;

            for (int[] nxt : city[curNode]) {
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