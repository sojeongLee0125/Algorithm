import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q. 두 물통이 비어 있는 상태에서 시작하여 최종 상태에 도달하기 위한 최소 작업 수 구하기
 * - 용량이 다른 두 개의 빈 물통 A, B가 있다.
 * - 이 물통들에 물을 채우고 비우는 일을 반복하여 두 물통을 원하는 상태가 되도록 만들고자 한다.
 * - 물통 이외에는 물의 양을 정확히 잴 수 있는 방법이 없으며, 가능한 작업은 다음과 같은 세 종류가 전부이다.
 * - [F(x): Fill x]: 물통 x에 물을 가득 채운다.
 * - (물을 채우기 전에 물통 x가 비어있는지 여부는 관계없음. 다른 물통은 그대로 둠)
 * - [E(x): Empty x]: 물통 x의 물을 모두 버린다. (다른 물통은 그대로 둠)
 * - [M(x,y): Move water from x to y)]: 물통 x의 물을 물통 y에 붓는다.
 * - 물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 적거나 같다면 물통 x의 물을 모두 붓는다.
 * - 물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 많다면 부을 수 있는 만큼 최대로 부어
 * 물통 y를 꽉 채우고 나머지는 물통 x에 남긴다.
 * -
 */
public class Main {

    static int a, b, c, d;
    static int answer = -1;
    static int fullSize = 10_005;

    static int[][] dp = new int[fullSize][fullSize];
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 물통 A의 용량을 나타내는 정수 a(1 ≤ a < 100,000),
        // 물통 B의 용량을 나타내는 정수 b(a < b ≤ 100,000),
        // 최종 상태에서 물통 A에 남겨야 하는 물의 용량을 나타내는 정수 c(0 ≤ c ≤ a),
        // 최종 상태에서 물통 B에 남겨야 하는 물의 용량을 나타내는 정수 d(0 ≤ d ≤ b)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        find(0, 0);

        // 목표 상태에 도달하는 최소 작업 수를 나타내는 정수를 표준 출력으로 출력한다.
        // 만약 목표 상태에 도달하는 방법이 없다면 –1을 출력한다.
        System.out.println(answer);
    }

    private static void find(int b1, int b2) {
        // 현재 상태값 입력
        dp[b1][b2] = 1;

        // 현재값 큐에 입력
        q.add(new int[]{b1, b2});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cur1 = cur[0];
            int cur2 = cur[1];

            // 물통 A에 물을 가득 채운다.
            push(a, cur2, dp[cur1][cur2]);

            // 물통 B에 물을 가득 채운다.
            push(cur1, b, dp[cur1][cur2]);

            // 물통 A를 비운다.
            push(0, cur2, dp[cur1][cur2]);

            // 물통 B를 비운다.
            push(cur1, 0, dp[cur1][cur2]);

            // 물통 B에 있는 물을 전부 뭁통 A로 넣는다.
            push(Math.min(cur1 + cur2, a), Math.max(0, cur1 + cur2 - a), dp[cur1][cur2]);

            // 물통 A에 있는 물을 전부 뭁통 B로 넣는다.
            push(Math.max(0, cur1 + cur2 - b), Math.min(cur1 + cur2, b), dp[cur1][cur2]);
        }

        if (dp[c][d] != 0) answer = dp[c][d] - 1;
    }

    private static void push(int b1, int b2, int cnt) {
        if (dp[b1][b2] != 0) return;
        dp[b1][b2] = cnt + 1;
        q.add(new int[]{b1, b2});
    }
}