import java.io.*;
import java.util.*;

/**
 * Q. 달빛 여우가 달빛 늑대보다 먼저 도착할 수 있는 그루터기가 몇 개 ?
 * <p>
 * - 관악산에는 1번부터 N 번까지의 번호가 붙은 N개의 나무 그루터기가 있고,
 * - 그루터기들 사이에는 M개의 오솔길이 나 있다.
 * - 오솔길은 어떤 방향으로든 지나갈 수 있으며,
 * - 어떤 두 그루터기 사이에 두 개 이상의 오솔길이 나 있는 경우는 없다.
 * - 달빛 여우와 달빛 늑대는 1번 나무 그루터기에서 살고 있다.
 * <p>
 * - 보름달이 뜨면 나무 그루터기들 중 하나가 달빛을 받아 밝게 빛나게 된다.
 * - 달빛 여우와 달빛 늑대는 먼저 달빛을 독차지하기 위해
 * - 최대한 빨리 오솔길을 따라서 그 그루터기로 달려가야 한다.
 * - 달빛 여우는 늘 일정한 속도로 달려간다.
 * - 달빛 늑대는 출발할 때 오솔길 하나를 달빛 여우의 두 배의 속도로 달려가고,
 * - 그 뒤로는 오솔길 하나를 달빛 여우의 절반의 속도로 걸어가며 체력을 회복하고
 * - 다음 오솔길을 다시 달빛 여우의 두 배의 속도로 달려가는 것을 반복한다.
 * <p>
 * - 달빛 여우와 달빛 늑대는 각자 가장 빠르게
 * - 달빛이 비치는 그루터기까지 다다를 수 있는 경로로 이동한다.
 * - 따라서 둘의 이동 경로가 서로 다를 수도 있다.
 */
public class Main {
    static int N, M;
    final static int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> map;
    static int[] distFox;
    static int[][] distWolf; // 늑대의 속도에 따라 2차원 배열로 분리

    static class Node implements Comparable<Node> {
        int node;
        int dist;
        int flag;

        public Node(int node, int dist, int flag) {
            this.node = node;
            this.dist = dist;
            this.flag = flag;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에 나무 그루터기의 개수와 오솔길의 개수를 의미하는
        // 정수 N, M(2 ≤ N ≤ 4,000, 1 ≤ M ≤ 100,000)이 주어진다.
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distFox = new int[N + 1];
        distWolf = new int[2][N + 1];

        initMap();
        initDistArr();

        // 두 번째 줄부터 M개의 줄에 걸쳐 각 줄에
        // 세 개의 정수 a, b, d(1 ≤ a, b ≤ N, a ≠ b, 1 ≤ d ≤ 100,000)가 주어진다.
        // 이는 a번 그루터기와 b번 그루터기 사이에 길이가 d인 오솔길이 나 있음을 의미한다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 실수 연산을 하지 않기 위해 가중치를 처음부터 2배로 만든다.
            map.get(a).add(new Node(b, 2 * d, 1));
            map.get(b).add(new Node(a, 2 * d, 1));
        }

        dijkstraFox();
        dijkstraWolf();

        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            if (distFox[i] < Math.min(distWolf[0][i], distWolf[1][i])) cnt++;
        }
        System.out.println(cnt);
    }

    private static void initMap() {
        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }
    }

    private static void initDistArr() {
        Arrays.fill(distFox, INF);
        for (int[] arr : distWolf) Arrays.fill(arr, INF);
    }

    private static void dijkstraWolf() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 1));
        distWolf[1][1] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int curNode = node.node;
            int curDist = node.dist;
            int curFlag = node.flag;

            if (distWolf[curFlag][curNode] < curDist) continue;

            map.get(curNode).stream().forEach(nxt -> {
                int nxtNode = nxt.node;
                int nxtFlag = 1 - curFlag;
                int cost = distWolf[curFlag][curNode] + (nxtFlag == 0 ? (nxt.dist / 2) : (nxt.dist * 2));

                if (distWolf[nxtFlag][nxtNode] > cost) {
                    distWolf[nxtFlag][nxtNode] = cost;
                    pq.add(new Node(nxtNode, cost, nxtFlag));
                }
            });
        }
    }

    private static void dijkstraFox() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 1));
        distFox[1] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int curNode = node.node;
            int curDist = node.dist;

            if (distFox[curNode] < curDist) continue;

            map.get(curNode).stream().forEach(nxt -> {
                int nxtNode = nxt.node;
                int cost = distFox[curNode] + nxt.dist;

                if (distFox[nxtNode] > cost) {
                    distFox[nxtNode] = cost;
                    pq.add(new Node(nxtNode, cost, 1));
                }
            });
        }
    }
}