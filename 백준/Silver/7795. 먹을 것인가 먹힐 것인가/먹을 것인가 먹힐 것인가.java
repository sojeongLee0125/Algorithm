import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 두 생명체 A와 B의 크기가 주어졌을 때, A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하는 프로그램을 작성하시오.
 * - 심해에는 두 종류의 생명체 A와 B가 존재한다. A는 B를 먹는다.
 * - A는 자기보다 크기가 작은 먹이만 먹을 수 있다.
 */
public class Main {

    static int T, N, M, cnt;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
        T = Integer.parseInt(br.readLine());


        while (T-- > 0) {
            cnt = 0;
            // 각 테스트 케이스의 첫째 줄에는 A의 수 N과 B의 수 M이 주어진다.
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            a = new int[N];
            b = new int[M];

            //  둘째 줄에는 A의 크기가 모두 주어지며, 셋째 줄에는 B의 크기가 모두 주어진다.
            // 크기는 양의 정수이다. (1 ≤ N, M ≤ 20,000)
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            // 1. a와 b배열을 정렬한다.
            // 2. a를 순회하면서 b에서의 upperbound를 찾는다.
            // 3. upperbound - 0 을 정답에 더해준다.

            Arrays.sort(a);
            Arrays.sort(b);

            for (int i = 0; i < a.length; i++) {
                int n = lowerBound(b, a[i]) - 0;
                cnt += n;
            }

            // 각 테스트 케이스마다, A가 B보다 큰 쌍의 개수를 출력한다.
            System.out.println(cnt);
        }
    }

    public static int lowerBound(int[] arr, int target) {
        int st = 0;
        int ed = arr.length;

        while (st < ed) {
            int mid = (st + ed) / 2;

            if (arr[mid] >= target) ed = mid;
            else st = mid + 1;
        }

        return ed;
    }

}