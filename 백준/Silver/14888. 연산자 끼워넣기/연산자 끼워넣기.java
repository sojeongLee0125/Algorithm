import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 * - N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다.
 * - 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
 * - 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.
 * - 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다.
 * - 음수를 양수로 나눌 때는 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다.
 */
public class Main {

    static int N;
    static int[] nums, ops;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다.
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        ops = new int[4];

        // 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 배치 경우의 수 : N - 1
        // 연산자 0-4 중 하나를 선택하는데 ops[i] == 0 일경우는 continue
        // 재귀함수로 돌아가는데 cnt == N-1이 되면 해당 값을 갱신한다.
        // 재귀함수 (cnt, sum)

        go(0, nums[0]);

        // 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
        System.out.println(max);
        System.out.println(min);
    }

    private static void go(int cnt, int sum) {
        if (cnt == N - 1) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (ops[i] == 0) continue;
            ops[i]--;
            go(cnt + 1, getResult(sum, nums[cnt + 1], i));
            ops[i]++;
        }
    }

    private static int getResult(int sum, int nxt, int op) {
        switch (op) {
            case 0:
                return sum + nxt;
            case 1:
                return sum - nxt;
            case 2:
                return sum * nxt;
            case 3:
                if (sum < 0) return -(Math.abs(sum) / nxt);
                else return sum / nxt;
        }
        return 0;
    }

}