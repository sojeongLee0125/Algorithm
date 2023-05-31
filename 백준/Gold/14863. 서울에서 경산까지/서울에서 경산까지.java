import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q.제한시간 이내로 서울에서 경산까지 여행하면서 모금할 수 있는 최대 금액을 찾는 프로그램을 작성하시오.
 * (제한시간 이내에 여행하는 방법은 항상 존재한다.)
 * - 서울에서 경산까지 자선 여행을 하면서 모금 활동을 진행할 계획이다.
 * - 거쳐 가게 될 도시의 개수와 순서는 미리 정해져 있으며,
 * - 자선 여행은 서울에서 시작하여 각 도시를 정해진 순서대로 단 한 번씩 방문한 후 경산에서 끝난다.
 * - 서울을 제외한 도시의 개수를 N 이라 하자.
 * - 서울에서 두 번째 도시까지 가는 구간을 구간 1, 두 번째 도시부터 세 번째 도시까지 가는 구간을 구간 2,
 * - 마지막 목적지인 경산에 도착하는 구간을 구간 N 이라 하자. 즉, 구간의 전체 개수는 N이다.
 * - 구간 사이의 이동은 도보 혹은 자전거 어느 한 쪽을 이용하게 되는데,
 * - 각 구간에는 도보로 이동할 때 걸리는 시간(분), 이때 얻게 되는 모금액(원),
 * - 자전거로 이동할 때 걸리는 시간(분), 이때 얻게 되는 모금액(원)이 정해져 있다.
 * - 전체 모금액을 가능한 많이 얻는 방법을 찾고 싶어 한다.
 * - 자선 여행을 위해 보낼 수 있는 시간이 K분(K는 자연수)으로 한정되어 있다.
 */
public class Main {

    static int N, K;
    static int[][] dp;

    static ArrayList<int[]> walk = new ArrayList<>();
    static ArrayList<int[]> bike = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에는 두 자연수 N과 K가 공백으로 분리되어 주어진다(3 ≤ N ≤ 100, 0 < K ≤ 100,000).
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        dp = new int[N + 5][K + 5];

        // 두 번째 줄부터
        // 구간을 도보로 이동할 때 걸리는 시간(분), 이때 얻게 되는 모금액(원),
        // 자전거로 이동할 때 걸리는 시간(분), 이때 얻게 되는 모금액(원)을 나타내는
        // 네 개의 자연수가 차례로 공백으로 분리되어 주어진다.
        // 시간을 나타내는 숫자(각 줄의 첫 번째, 세 번째 숫자)는 10,000 이하의 자연수,
        // 모금액을 나타내는 숫자(각 줄의 두 번째, 네 번째 숫자)는 1,000,000 이하의 자연수들이다.

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t1 = Integer.parseInt(st.nextToken());
            int m1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            walk.add(new int[]{t1, m1});
            bike.add(new int[]{t2, m2});
        }

        // 표준 출력으로 K분 이내로 여행하면서 모금할 수 있는 최대 금액을 출력한다.
        // (K분 이내에 여행하는 방법은 항상 존재한다.)
        System.out.println(go(0, K));
    }

    private static int go(int cur, int timeSum) {
        
        // 순회 완료한 경우
        if (cur == N) return 0;

        // 메모이제이션
        if (dp[cur][timeSum] != 0) return dp[cur][timeSum];
        dp[cur][timeSum] = Integer.MIN_VALUE;

        // 도보로 갈 수 있는 경우
        if (timeSum - walk.get(cur)[0] >= 0) {
            dp[cur][timeSum] = Math.max(dp[cur][timeSum], go(cur + 1, timeSum - walk.get(cur)[0]) + walk.get(cur)[1]);
        }

        // 자전거로 갈 수 있는 경우
        if (timeSum - bike.get(cur)[0] >= 0) {
            dp[cur][timeSum] = Math.max(dp[cur][timeSum], go(cur + 1, timeSum - bike.get(cur)[0]) + bike.get(cur)[1]);
        }

        return dp[cur][timeSum];
    }
}