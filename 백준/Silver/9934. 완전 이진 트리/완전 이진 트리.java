import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Q. 상근이가 종이에 적은 순서가 모두 주어졌을 때, 각 레벨에 있는 빌딩의 번호를 구하는 프로그램을 작성하시오.
 * - 깊이가 K인 완전 이진 트리는 총 2^K-1개의 노드로 이루어져 있다.
 * - 각 노드에는 그 곳에 위치한 빌딩의 번호가 붙여져 있다. 가장 마지막 레벨을 제외한 모든 집은 왼쪽 자식과 오른쪽 자식을 갖는다.
 * - 상근이는 도시에 있는 모든 빌딩에 들어갔고, 들어간 순서대로 번호를 종이에 적어 놓았다.
 * - 가장 처음에 상근이는 트리의 루트에 있는 빌딩 앞에 서있다.
 * - 현재 빌딩의 왼쪽 자식에 있는 빌딩에 아직 들어가지 않았다면, 왼쪽 자식으로 이동한다.
 * - 현재 있는 노드가 왼쪽 자식을 가지고 있지 않거나 왼쪽 자식에 있는 빌딩을 이미 들어갔다면, 현재 노드에 있는 빌딩을 들어가고 종이에 번호를 적는다.
 * - 현재 빌딩을 이미 들어갔다 온 상태이고, 오른쪽 자식을 가지고 있는 경우에는 오른쪽 자식으로 이동한다.
 * - 현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문했다면, 부모 노드로 이동한다.
 */
public class Main {

    static int K;
    static ArrayList<Integer> list = new ArrayList<>();
    static ArrayList<Integer>[] tree;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 K (1 ≤ K ≤ 10)가 주어진다.
        K = Integer.parseInt(br.readLine());
        tree = new ArrayList[K];
        for (int i = 0; i < K; i++) {
            tree[i] = new ArrayList<>();
        }

        // 둘째 줄에는 상근이가 방문한 빌딩의 번호가 들어간 순서대로 주어진다. 모든 빌딩의 번호는 중복되지 않으며, 구간 [1,2K)에 포함된다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));

        // 해결방안
        // 정 가운데 요소를 레벨에 하나씩 넣는다.
        // 왼쪽 오른쪽 자른다.
        // 각각에서 정 가운데 요소를 두번째 레벨에 추가한다.
        // 왼쪽 오른쪽 자른다.
        // 요소가 2개 남으면 왼쪽부터 레벨에 넣는다.
        go(0, list);

        // 총 K개의 줄에 걸쳐서 정답을 출력한다. i번째 줄에는 레벨이 i인 빌딩의 번호를 출력한다.
        // 출력은 왼쪽에서부터 오른쪽 순서대로 출력한다.

        for (int i = 0; i < K; i++) {
            for (Integer num : tree[i]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static void go(int lev, List<Integer> list) {
        if (list.size() <= 2) {
            for (int i = 0; i < list.size(); i++) tree[lev].add(list.get(i));
        } else {
            tree[lev].add(list.get(list.size() / 2));
            go(lev + 1, list.subList(0, list.size() / 2));
            go(lev + 1, list.subList(list.size() / 2 + 1, list.size()));
        }
    }

}