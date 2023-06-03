import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. N개의 보석의 무게가 주어졌을 때, 세준이가 훔칠 수 있는 보석의 최대 개수를 구하기.
 * - 숌 보석상에 있는 모든 보석을 다 훔치려고 한다.
 * - 세준이는 보석을 다 가져올 수는 없다.
 * - 그 이유는 가방의 개수에 제한이 있고, 가방마다 넣을 수 있는 보석의 개수가 제한
 * - 세준이는 M개의 가방을 가지고 있다. 각각의 가방은 C 그램의 보석을 담을 수 있다.
 * - 숌 보석상에는 보석이 N개 있다.
 */
public class Main {
    static int N, M, C;
    static int[] jewels;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 보석의 개수 N, 가방의 개수 M, 가방의 최대 한도 C가 주어진다.
        // N은 1보다 크거나 같고, 13보다 작거나 같은 자연수이고,
        // M은 1보다 크거나 같고, 10보다 작거나 같은 자연수이다.
        // C는 1보다 크고, 20보다 작거나 같은 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        jewels = new int[N];
        dp = new int[1 << N][M + 2][C + 2];

        // 둘째 줄에 보석의 무게가 하나씩 주어진다.
        // 보석의 무게는 1보다 크거나 같고, 20보다 작거나 같은 자연수이다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            jewels[i] = Integer.parseInt(st.nextToken());
        }

        // 첫째 줄에 세준이가 가져갈 수 있는 최대 보석의 개수를 출력한다.
        System.out.println(find(0, 0, C));
    }

    private static int find(int isGet, int bagIdx, int capacity) {
        // 가방을 전부 순회할 경우
        if (bagIdx == M) return 0;

        // 메모이제이션
        if (dp[isGet][bagIdx][capacity] != 0) return dp[isGet][bagIdx][capacity];

        // 새로운 가방으로 넘어가는 경우
        dp[isGet][bagIdx][capacity] = Math.max(dp[isGet][bagIdx][capacity],
                find(isGet, bagIdx + 1, C));

        // 현재 가방에 보석을 훔칠 경우
        for (int i = 0; i < N; i++) {
            boolean isAlreadyGet = ((1 << i) & isGet) != 0; // 이미 가지고 있는지 여부
            boolean canGet = (capacity - jewels[i]) >= 0; // 무게 체크

            if (!isAlreadyGet && canGet) {
                dp[isGet][bagIdx][capacity] = Math.max(
                        dp[isGet][bagIdx][capacity],
                        find(isGet | (1 << i), bagIdx, capacity - jewels[i]) + 1
                );
            }
        }

        return dp[isGet][bagIdx][capacity];
    }
}