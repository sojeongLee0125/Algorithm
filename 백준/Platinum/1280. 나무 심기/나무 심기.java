import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 2번 나무부터 N번 나무까지를 심는 비용의 곱을 출력하는 프로그램 작성
 * - 1번부터 N번 까지 번호가 매겨져 있는 N개의 나무가 있다.
 * - i번 나무는 좌표 X[i]에 심어질 것이다.
 * - 나무를 1번 나무부터 차례대로 좌표 X[i]에 심으려고 한다.
 * - 1번 나무를 심는 비용은 없고, 각각의 나무를 심는데 드는 비용은
 * - 현재 심어져있는 모든 나무까지 거리의 합이다.
 * - 3번 나무 비용 = 1번 나무와의 거리 + 2번 나무와의 거리
 */
public class Main {
    static final int MAX = 200_000;
    static final int DIV = 1_000_000_007;

    static int N;
    static long answer = 1;

    static long[] fenwickSum = new long[MAX + 5];
    static long[] fenwickCnt = new long[MAX + 5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 나무의 개수 N (2 ≤ N ≤ 200,000)이 주어진다.
        N = Integer.parseInt(br.readLine());

        // 둘째 줄부터 N개의 줄에 1번 나무의 좌표부터 차례대로 주어진다.
        // 각각의 좌표는 200,000보다 작은 자연수 또는 0이다.
        for (int i = 1; i <= N; i++) {
            int point = Integer.parseInt(br.readLine()) + 1;

            // 1번 나무가 아닐 경우
            if (i != 1) {
                // 현재 나무보다 왼쪽에 있는 나무들의(거리가 작음) 거리 경우의 수 구하기
                // (현재 좌표 이전까지의 나무의 갯수 * 현재 좌표) - (현재 좌표까지의 거리 합)
                long lt = (point * getSum(fenwickCnt, 1, point - 1)) - getSum(fenwickSum, 1, point - 1);

                // 현재 나무보다 오른쪽에 있는 나무들의(거리가 큼) 거리 경우의 수 구하기
                // (현재 좌표까지의 거리 합) - (현재 좌표 이전까지의 나무의 갯수 * 현재 좌표)
                long rt = getSum(fenwickSum, point + 1, MAX) - (point * getSum(fenwickCnt, point + 1, MAX));

                answer *= (lt + rt) % DIV;
                answer %= DIV;
            }

            update(fenwickCnt, point, 1);
            update(fenwickSum, point, point);
        }

        // 문제의 정답을 1,000,000,007로 나눈 나머지를 출력한다.
        System.out.println(answer);
    }

    private static void update(long[] treeSum, int point, int val) {
        int idx = point;

        while (idx <= MAX) {
            treeSum[idx] += val;
            idx += (idx & -idx);
        }
    }

    private static long getSum(long[] tree, int start, int end) {
        if (start > end) return 0;
        return (sum(tree, end) - sum(tree, start - 1));
    }

    private static long sum(long[] tree, int val) {
        long sum = 0L;
        int idx = val;

        while (idx != 0) {
            sum += tree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }
}