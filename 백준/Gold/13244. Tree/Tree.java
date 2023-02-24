import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. Your task is to decide if a given graph is a tree or not.
 * - Trees are the subset of graphs that have the following 3 properties:
 * - It is connected: for every node you can reach every other node following edges.
 * - If an edge is removed, the graph is no longer connected. That is, some nodes cannot be reached anymore.
 * - When an edge is added between two existing nodes A and B, a cycle is created.
 * - There is a cycle if there is more than one way to go from A to B.
 */
public class Main {

    static int T, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // number of graphs to check.
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // number of nodes in the graph.  between 1 and 1,000. 1 ~ N번 노드
            N = Integer.parseInt(br.readLine());
            // number of edges in the graph. There will be at most 106 edges.
            M = Integer.parseInt(br.readLine());

            ArrayList<Integer>[] tree = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            // 2 integers A and B each. These are the two nodes connected by an edge.
            // The total sum of M in all test cases is at most 106.
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                tree[a].add(b);
                tree[b].add(a);
            }

            // For each graph, a single line with “tree” if the graph represents a tree or “graph“ otherwise.
            if (checkTreeConnect(tree) && M + 1 == N) System.out.println("tree");
            else System.out.println("graph");
        }

    }

    private static boolean checkTreeConnect(ArrayList<Integer>[] tree) {
        int[] chk = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        chk[1] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nx : tree[cur]) {
                if (chk[nx] != 0) continue;
                chk[nx] = 1;
                q.add(nx);
            }
        }
        for (int i = 1; i <= N; i++) {
            if (chk[i] == 0) return false;
        }
        return true;
    }

}