import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q. 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.
 * - 단, 모든 간선의 가중치는 10 이하의 자연수이다.
 * <p>
 * 1. 아이디어
 * - 다익스트라 : 한 노드에서 다른 모든 노드까지 가는데 최소비용
 * - 거리 배열 무한대 값 초기화 & 시작점 거리 0 설정 및 pq 추가
 * - pq 에서 하나씩 빼면서 현재 거리 값이 간선을 통할 때 더 거리가 짧아진다면 거리 갱신 및 pq에 추가
 * <p>
 * 2. 시간복잡도
 * - ElogE -> ElogV^2 -> ElogV
 * <p>
 * 3. 자료구조
 * - 노드 클래스 : (비용, 노드번호)
 * - 노드 트리 저장 : ArrayList<Integer>[]
 * - pa : PriorityQueue<Node>
 * - 거리 배열 저장 : int[] (초기값 무한대)
 */
public class Main {

    static int[] dist;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<Node>[] list;

    static class Node implements Comparable<Node> {
        int w;
        int n;

        public Node(int w, int n) {
            this.w = w;
            this.n = n;
        }

        // 비용 순 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000)
        // 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 인접리스트 배열 생성
        list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        // 거리배열 무한대로 초기화
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다.
        int start = Integer.parseInt(br.readLine());
        dist[start] = 0;

        // 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다.
        // 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다.
        // u와 v는 서로 다르며 w는 10 이하의 자연수이다.
        // 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
        while (E-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(w, v));
        }

        pq.add(new Node(0, start));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int w = node.w;
            int n = node.n;
            if (dist[n] != w) continue;
            for (Node nxt : list[n]) {
                if (dist[nxt.n] > w + nxt.w) {
                    dist[nxt.n] = w + nxt.w;
                    pq.add(new Node(dist[nxt.n], nxt.n));
                }
            }
        }

        // 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
        // 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(dist[i]).append("\n");
        }

        System.out.println(sb);


    }

}