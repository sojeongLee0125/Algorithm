import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q.
 * - 거의 최단 경로란 최단 경로에 포함되지 않는 도로로만
 * - 이루어진 경로 중 가장 짧은 것을 말한다.
 * - 거의 최단 경로는 여러 개 존재할 수도 있다.
 * - 또, 거의 최단 경로가 없는 경우도 있다.
 */
public class Main {
    static final int INF = 999_999_999;
    static int N, M, S, D, U, V, P;
    static int[][] map = new int[505][505];
    static int[] dist = new int[505];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력은 여러 개의 테스트 케이스로 이루어져 있다.
        // 입력의 마지막 줄에는 0이 두 개 주어진다.
        while (true) {
            // 장소의 수 N (2 ≤ N ≤ 500)
            // 장소는 0부터 N-1번까지 번호가 매겨져 있다.
            // 도로의 수 M (1 ≤ M ≤ 104)
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            // 시작점 S와 도착점 D가 주어진다. (S ≠ D; 0 ≤ S, D < N)
            st = new StringTokenizer(br.readLine(), " ");
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            // 지도 초기화
            for (int i = 0; i < map.length; i++) Arrays.fill(map[i], -1);

            // 다음 M개 줄에는 도로의 정보 U, V, P가 주어진다.
            // (U ≠ V ; 0 ≤ U, V < N; 1 ≤ P ≤ 103)
            // U에서 V로 가는 도로의 길이가 P라는 뜻이다.
            // U에서 V로 가는 도로는 최대 한 개이다.
            // U에서 V로 가는 도로와 V에서 U로 가는 도로는 다른 도로이다.
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());
                map[U][V] = P;
            }

            // 최단경로 구하기
            dijkstra();

            // 최단경로 지우기
            removeShortest();

            // 거의 최단경로 구하기
            dijkstra();

            // 각 테스트 케이스에 대해서, 거의 최단 경로의 길이를 출력한다.
            // 만약, 거의 최단 경로가 없는 경우에는 -1을 출력한다.
            sb.append(dist[D] != INF ? dist[D] : -1).append("\n");
        }

        System.out.println(sb);
    }

    private static void removeShortest() {
        Queue<Integer> q = new LinkedList<>();

        // 도착점 입력, 도착점 부터 역순 추적
        q.offer(D);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < N; i++) {
                // i -> cur 가 최단 경로일 경우 해당 최단경로 길 막기
                if (dist[cur] == (dist[i] + map[i][cur]) && map[i][cur] != -1) {
                    map[i][cur] = -1;
                    q.offer(i);
                }
            }
        }
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        // dist 초기화
        Arrays.fill(dist, INF);

        // 시작점 입력
        pq.add(new int[]{0, S});
        dist[S] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[1];
            int curDist = cur[0];

            if (dist[curNode] != curDist) continue;

            for (int i = 0; i < N; i++) {

                // 현재 노드에서 갈 수 없는 경우
                if (map[curNode][i] == -1) continue;

                int distance = map[curNode][i];

                // dist 배열 최소거리 갱신
                if (dist[i] > dist[curNode] + distance) {
                    dist[i] = dist[curNode] + distance;
                    pq.add(new int[]{dist[i], i});
                }
            }
        }
    }
}