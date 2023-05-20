import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 매 초마다 어느 나무에서 떨어질지에 대한 정보가 주어졌을 때, 자두가 받을 수 있는 자두의 개수 구하기.
 * - 매 초마다, 두 개의 나무 중 하나의 나무에서 열매가 떨어지게 된다.
 * - 만약 열매가 떨어지는 순간, 자두가 그 나무의 아래에 서 있으면 자두는 그 열매를 받아먹을 수 있다.
 * - 두 개의 나무는 그다지 멀리 떨어져 있지 않기 때문에, 자두는 하나의 나무 아래에 서 있다가
 * - 다른 나무 아래로 빠르게(1초보다 훨씬 짧은 시간에) 움직일 수 있다.
 * - 하지만 자두는 체력이 그다지 좋지 못해서 많이 움직일 수는 없다.
 * - 자두는 T(1≤T≤1,000)초 동안 떨어지게 된다. 자두는 최대 W(1≤W≤30)번만 움직이고 싶어 한다.
 * - 자두는 1번 자두나무 아래에 위치해 있다고 한다.
 */
public class Main {
    static int T, W, MIN = -35;
    static int[] arr;
    static int[][][] dp = new int[1005][35][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 두 정수 T, W가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        // 다음 T개의 줄에는 각 순간에 자두가 떨어지는 나무의 번호가 1 또는 2로 주어진다.
        arr = new int[T];

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }

        // 첫째 줄에 자두가 받을 수 있는 자두의 최대 개수를 출력한다.
        System.out.println(Math.max(move(0, 1, W - 1), move(0, 0, W)));
    }

    private static int move(int time, int tree, int cnt) {
        // 이동 횟수가 초과된 경우
        if (cnt < 0) return MIN;

        // T초에 도달한 경우
        if (time == T) return cnt == 0 ? 0 : MIN;
        if (dp[time][cnt][tree] != 0) return dp[time][cnt][tree];

        int ret = Math.max(move(time + 1, tree ^ 1, cnt - 1), move(time + 1, tree, cnt));
        if (tree == arr[time]) ret++;

        return dp[time][cnt][tree] = ret;
    }
}