import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 표에 채워져 있는 수와 변경하는 연산과 합을 구하는 연산이 주어졌을 때,
 * 이를 처리하는 프로그램을 작성하시오.
 * - N×N개의 수가 N×N 크기의 표에 채워져 있다.
 * - 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다.
 * - 표의 i행 j열은 (i, j)로 나타낸다.
 * - (x1, y1)부터 (x2, y2)까지 합이란
 * - x1 ≤ x ≤ x2, y1 ≤ y ≤ y2를 만족하는 모든 (x, y)에 있는 수의 합이다.
 */
public class Main {
    static int N, M;
    static int[][] nums;
    static int[][] fenwickTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 첫째 줄에 표의 크기 N과 수행해야 하는 연산의 수 M이 주어진다.
        // (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N + 1][N + 1];
        fenwickTree = new int[N + 1][N + 1];

        // 둘째 줄부터 N개의 줄에는 표에 채워져있는 수가 1행부터 차례대로 주어진다.
        // 다음 M개의 줄에는 네 개의 정수 w, x, y, c 또는 다섯 개의 정수 w, x1, y1, x2, y2가 주어진다.
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
                update(i, j, nums[i][j]);
            }
        }

        for (int i = 0; i < M; i++) {
            //  w = 0인 경우는 (x, y)를 c (1 ≤ c ≤ 1,000)로 바꾸는 연산이고,
            //  w = 1인 경우는 (x1, y1)부터 (x2, y2)의 합을 구해 출력하는 연산이다.
            //  (1 ≤ x1 ≤ x2 ≤ N, 1 ≤ y1 ≤ y2 ≤ N)
            //  표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다.
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            if (w == 0) {
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(y, x, c - nums[y][x]);
                nums[y][x] = c;
            } else {
                int y1 = Integer.parseInt(st.nextToken());
                int x1 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                // w = 1인 입력마다 구한 합을 순서대로 한 줄에 하나씩 출력한다.
                sb.append(getSum(y2, x2)
                        - getSum(y1 - 1, x2)
                        - getSum(y2, x1 - 1)
                        + getSum(y1 - 1, x1 - 1) // 공통 제거 부분 다시 더해주기
                );
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void update(int y, int x, int val) {
        while (y <= N) {
            int idx = x;
            while (idx <= N) {
                fenwickTree[y][idx] += val;
                idx += (idx & -idx);
            }
            y += (y & -y);
        }
    }

    private static int getSum(int y, int x) {
        int sum = 0;
        while (y > 0) {
            int idx = x;
            while (0 < idx) {
                sum += fenwickTree[y][idx];
                idx -= (idx & -idx);
            }
            y -= (y & -y);
        }
        return sum;
    }

}
