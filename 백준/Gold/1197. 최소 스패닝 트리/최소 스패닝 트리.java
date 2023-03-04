import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q.그 래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
 * - 최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
 * - 그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다.
 * - 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.
 *
 * 1. 아이디어
 * - MST : prim 알고리즘 및 Priority Queue 자료구조 사용 (비용, 노드번호)
 * - 노드정보는 인접 리스트 배열로 저장
 *- 시작접 pq에 넣기
 * -
 * 2. 시간복잡도
 * - edge 리스트에 저장 : O(E)
 * - pq 안 모든 엣지에 연결된 간선 확인 : O(E+E)
 * - 모든 간선 pq에 삽입 : O(ElogE)
 * - O(E + 2E + ElogE) = E(3 + logE) = ElogE
 * 
 * 3. 자료구조
 * - 노트 트리 : ArrayList<Node>[]
 * - 방문 체크 : int[]
 * - MST 비용 : PriorityQueue
 */
public class Main {

    static int rs;
    static int[] chk;
    static ArrayList<Node>[] tree;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int w;
        int num;

        public Node(int w, int num) {
            this.w = w;
            this.num = num;
        }

        // 비용 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        chk = new int[V + 1];
        tree = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            tree[i] = new ArrayList<>();
        }

        // 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
        // A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            tree[A].add(new Node(C, B));
            tree[B].add(new Node(C, A));
        }

        pq.add(new Node(0, 1));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (chk[node.num] != 1) {
                chk[node.num] = 1;
                rs += node.w;
                for (Node nxt : tree[node.num]) {
                    pq.add(nxt);
                }
            }
        }

        // 첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.
        System.out.println(rs);

    }

}