import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Q. 피자가게에서 손님이 원하는 크기의 피자를 판매하는 모든 방법의 가지 수를 계산하는 프로그램을 작성하시오
 * - 두 종류의 피자 A와 B를 취급하는 피자가게에서 피자를 주문하고자 한다.
 * - 각 종류의 피자는 다양한 크기의 여러 개의 피자조각으로 나누어져 있다. 각 조각에 쓰여진 숫자는 피자조각의 크기를 나타낸다.
 * - 고객이 원하는 피자의 크기를 이야기하면, 피자가게에서는 한 종류의 피자를 2 조각 이상 판매할 때는 반드시 연속된 조각들을 잘라서 판매한다.
 * - 이때 판매한 피자조각의 크기 합이 주문한 크기가 되어야 한다.
 * - 판매한 피자조각은 모두 A종류이거나, 모두 B종류이거나, 또는 A와 B 종류가 혼합될 수 있다.
 */
public class Main {

    static int size, m, n, cnt;
    static int[] A, B, pSum_a, pSum_b;
    static HashMap<Integer, Integer> cnt_a = new HashMap<>();
    static HashMap<Integer, Integer> cnt_b = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에는 손님이 구매하고자 하는 피자크기를 나타내는 2,000,000 이하의 자연수가 주어진다.
        size = Integer.parseInt(br.readLine());

        // 두 번째 줄에는 A, B 피자의 피자조각의 개수를 나타내 는 정수 m, n 이 차례로 주어진다 (3 ≤ m, n ≤ 1000).
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        A = new int[m + 1];
        B = new int[n + 1];
        pSum_a = new int[2 * m + 1];
        pSum_b = new int[2 * n + 1];

        // 각 종류의 피자조각의 크기는 시계방향으로 차례로 주어지며, 각 피자 조각의 크기는 1000 이하의 자연수이다.
        // 세 번째 줄부터 차례로 m 개의 줄에는 피자 A의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다.
        for (int i = 1; i <= m; i++) {
            A[i] = Integer.parseInt(br.readLine());
            // 누적합 구하기
            pSum_a[i] = pSum_a[i - 1] + A[i];
        }

        // 이어 붙여서 원형 구조를 선형으로 만들기
        for (int i = m + 1; i <= m * 2; i++) {
            pSum_a[i] = pSum_a[i - 1] + A[i - m];
        }

        // 그 다음 n 개의 줄에는 차례로 피자 B의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다.
        for (int i = 1; i <= n; i++) {
            B[i] = Integer.parseInt(br.readLine());
            // 누적합 구하기
            pSum_b[i] = pSum_b[i - 1] + B[i];
        }

        // 이어 붙여서 원형 구조를 선형으로 만들기
        for (int i = n + 1; i <= n * 2; i++) {
            pSum_b[i] = pSum_b[i - 1] + B[i - n];
        }

        go(m, pSum_a, cnt_a);
        go(n, pSum_b, cnt_b);

        // A 피자에서의 경우의 수
        cnt += cnt_a.getOrDefault(size, 0);

        // B 피자에서의 경우의 수
        cnt += cnt_b.getOrDefault(size, 0);

        // A + B 피자에서의 경우의 수
        for (int i = 1; i < size; i++) {
            cnt += cnt_a.getOrDefault(size - i, 0) * cnt_b.getOrDefault(i, 0);
        }

        // 첫째 줄에는 피자를 판매하는 방법의 가지 수를 나타내는 정수를 출력한다. 피자를 판매하는 방법이 없는 경우에는 숫자 0을 출력한다.
        System.out.println(cnt);
    }

    private static void go(int n, int[] pSum, HashMap<Integer, Integer> cnt) {
        for (int interval = 1; interval <= n; interval++) {
            for (int st = interval; st <= n + interval - 1; st++) {
                int sum = pSum[st] - pSum[st - interval];
                cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
                if (interval == n) break;
            }
        }
    }
}