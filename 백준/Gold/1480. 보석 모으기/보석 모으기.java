import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. N개의 보석의 무게가 주어졌을 때, 세준이가 훔칠 수 있는 보석의 최대 개수를 구하기.
 * - 숌 보석상에 있는 모든 보석을 다 훔치려고 한다.
 * - 세준이는 보석을 다 가져올 수는 없다.
 * - 그 이유는 가방의 개수에 제한이 있고, 가방마다 넣을 수 있는 보석의 개수가 제한
 * - 세준이는 M개의 가방을 가지고 있다. 각각의 가방은 C그램의 보석을 담을 수 있다.
 * - 숌 보석상에는 보석이 N개 있다.
 */
public class Main {

    static int N, M, C;
    static int[] jewels;
    static int[][][] dp = new int[1 << 15][25][25];

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

        // 둘째 줄에 보석의 무게가 하나씩 주어진다.
        // 보석의 무게는 1보다 크거나 같고, 20보다 작거나 같은 자연수이다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            jewels[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(find(0, 0, C));
    }

    private static int find(int cur, int isGet, int capacity) {
        // 가방이 가득 찼을 경우
        if (cur == M) return 0;

        if (dp[isGet][cur][capacity] != 0) return dp[isGet][cur][capacity];

        // 현재 훔치지 않을 경우
        dp[isGet][cur][capacity] = Math.max(dp[isGet][cur][capacity], find(cur + 1, isGet, C));

        for (int i = 0; i < N; i++) {
            int beforeGet = (1 << i) & isGet;
            boolean canGet = (capacity - jewels[i]) >= 0;
            if (beforeGet == 0 && canGet) {
                dp[isGet][cur][capacity] = Math.max(
                        dp[isGet][cur][capacity],
                        find(cur, isGet | (1 << i), capacity - jewels[i]) + 1
                );
            }
        }

        return dp[isGet][cur][capacity];
    }


}