import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오.
 * - 다시 풀기 1068
 */
public class Main {
    static int N, rm, root;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다.
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        // 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else tree.get(parent).add(i);
        }

        // 셋째 줄에는 지울 노드의 번호가 주어진다.
        rm = Integer.parseInt(br.readLine());

        // 루트 노드를 지울 경우 0 출력
        if (rm == root) System.out.println("0");
        else {
            System.out.println(remove(root));
        }
    }

    private static int remove(int cur) {
        int rs = 0;
        int child = 0;
        for (int nx : tree.get(cur)) {
            if (nx == rm) continue;
            rs += remove(nx);
            child++;
        }
        if (child == 0) return 1;
        return rs;
    }
}
