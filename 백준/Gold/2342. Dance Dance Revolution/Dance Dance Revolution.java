import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 모든 지시 사항을 만족하는 데 사용되는 최소의 힘 구하기.
 * - 발판은 하나의 중점을 기준으로 위, 아래, 왼쪽, 오른쪽으로 연결되어 있다.
 * - 편의상 중점을 0, 위를 1, 왼쪽을 2, 아래를 3, 오른쪽을 4라고 정하자.
 * - 처음에 게이머는 두 발을 중앙에 모으고 있다.(0의 위치)
 * - 게임이 시작하면, 지시에 따라 왼쪽 또는 오른쪽 발을 움직인다.
 * - 하지만 그의 두 발이 동시에 움직이지는 않는다.
 * - 두 발이 같은 지점에 있는 것이 허락되지 않는다. (게임 시작시에는 예외)
 * - 발이 움직이는 위치에 따라서 드는 힘이 다르다는 것을 알게 되었다.
 * - 중앙에 있던 발이 다른 지점으로 움직일 때, 2의 힘을 사용하게 된다.
 * - 그리고 다른 지점에서 인접한 지점으로 움직일 때는 3의 힘을 사용하게 된다.
 * - 반대편으로 움직일때는 4의 힘을 사용하게 된다.
 * - 만약 같은 지점을 한번 더 누른다면, 그때는 1의 힘을 사용하게 된다.
 */
public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][][] dp;

    static ArrayList<Integer> command = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력은 지시 사항으로 이루어진다. 각각의 지시 사항은 하나의 수열로 이루어진다.
        // 각각의 수열은 1, 2, 3, 4의 숫자들로 이루어지고, 이 숫자들은 각각의 방향을 나타낸다. 그리고 0은 수열의 마지막을 의미한다.
        // 즉, 입력 파일의 마지막에는 0이 입력된다. 입력되는 수열의 길이는 100,000을 넘지 않는다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int num;
        while ((num = Integer.parseInt(st.nextToken())) != 0) {
            command.add(num);
        }

        // dp 초기화
        dp = new int[5][5][command.size() + 5];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // 한 줄에 모든 지시 사항을 만족하는 데 사용되는 최소의 힘을 출력한다.
        System.out.println(move(0, 0, 0));
    }

    private static int move(int lt, int rt, int cnt) {
        if (cnt == command.size()) return 0;

        // 메모이제이션
        if (dp[lt][rt][cnt] != -1) return dp[lt][rt][cnt];

        // 왼쪽 발을 움직일 경우
        int left = move(command.get(cnt), rt, cnt + 1) + getScore(lt, command.get(cnt));

        // 오른쪽 발을 움직일 경우
        int right = move(lt, command.get(cnt), cnt + 1) + getScore(rt, command.get(cnt));

        return dp[lt][rt][cnt] = Math.min(left, right);
    }

    private static int getScore(int pre, int nxt) {
        // 이전과 위치가 같을 경우
        if (pre == nxt) return 1;

        // 원점에서 움직일 경우
        if (pre == 0) return 2;

        // 반대편으로 움직일 경우
        if (Math.abs(nxt - pre) == 2) return 4;

        // 그 외 - 인접한 곳으로 움직일 경우
        return 3;
    }
}