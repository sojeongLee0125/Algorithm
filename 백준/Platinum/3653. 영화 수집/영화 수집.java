import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Q. 각 영화의 위치를 기록하는 프로그램을 작성하시오.
 * - 상근이는 그의 DVD 콜렉션을 쌓아 보관한다.
 * - 보고 싶은 영화가 있을 때는, DVD의 위치를 찾은 다음
 * - 쌓아놓은 콜렉션이 무너지지 않게 조심스럽게 DVD를 뺀다.
 * - 영화를 다 본 이후에는 가장 위에 놓는다.
 * <p>
 * - 각 DVD의 위치는, 찾으려는 DVD의 위에 있는 영화의 개수만 알면 된다.
 * - 각 영화는 DVD 표지에 붙어있는 숫자로 쉽게 구별할 수 있다.
 * - 상근이가 영화를 한 편 볼 때마다 그 DVD의 위에 몇 개의 DVD가 있었는지 구하기
 */
public class Main {

    static final int MAX = 200_005;
    static int T, n, m;
    static int[] fenwickTree;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 테스트 케이스의 개수가 주어진다.
        // 테스트 케이스의 개수는 100개를 넘지 않는다.
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            // 첫째 줄에는 상근이가 가지고 있는 영화의 수 n과 보려고 하는 영화의 수 m
            // (1 ≤ n, m ≤ 100,000)
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 배열의 앞으로 값을 넣었을 때
            // 인덱스 음수를 방지하기 위해 배열 수평 이동 (x2)
            fenwickTree = new int[MAX];
            map = new HashMap<>();

            // 수평이동으로 생긴 새로운 시작 인덱스
            int newIdx = 100001;

            for (int i = 1; i <= n; i++) {
                // 펜윅트리 업데이트
                update(i + newIdx, 1);
                map.put(i, i + newIdx);
            }

            // 둘째 줄에는 보려고 하는 영화의 번호가 순서대로 주어진다.
            // 영화의 번호는 1부터 n까지 이며,
            // 가장 처음에 영화가 쌓여진 순서는 1부터 증가하는 순서이다.
            // 가장 위에 있는 영화의 번호는 1이다.
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                int idx = map.get(num);

                // 각 테스트 케이스에 대해서 한 줄에 m개의 정수를 출력해야 한다.
                // i번째 출력하는 수는 i번째로 영화를 볼 때 그 영화의 위에 있었던 DVD의 개수.
                sb.append(getSum(idx) - 1 + " ");

                update(idx, -1);
                update(--newIdx, 1);

                map.put(num, newIdx);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int getSum(int idx) {
        int sum = 0;

        while (idx > 0) {
            sum += fenwickTree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }

    private static void update(int idx, int val) {
        while (idx <= MAX) {
            fenwickTree[idx] += val;
            idx += (idx & -idx);
        }
    }

}