import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성
 * - 상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고,
 * - 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다.
 * - 상근이는 올바른 등식을 만들려고 한다.
 * - 상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다.
 * - 따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다.
 */
public class Main {
    static int N;
    static int[] nums = new int[105];
    static long[][] dp = new long[105][25];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100)
        N = Integer.parseInt(br.readLine());

        // 둘째 줄에는 0 이상 9 이하의 정수 N개가 공백으로 구분해 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 첫째 줄에 상근이가 만들 수 있는 올바른 등식의 개수를 출력한다.
        // 이 값은 2^63-1 이하이다.
        System.out.println(find(0, nums[0]));
    }

    private static long find(int cnt, int sum) {
        // 음수이거나 20을 넘을 경우
        if (sum < 0 || sum > 20) return 0;

        // 메모이제이션
        if (dp[cnt][sum] != 0L) return dp[cnt][sum];

        // 뒤에 1개의 숫자만 남았을 경우 (최종 계산 결과)
        if (cnt == N - 2) {
            // 올바른 등식일 경우 (계산 결과 == 마지막 숫자)
            if (sum == nums[N - 1]) return 1;
            else return 0;
        }

        dp[cnt][sum] += find(cnt + 1, sum + nums[cnt + 1]);
        dp[cnt][sum] += find(cnt + 1, sum - nums[cnt + 1]);

        return dp[cnt][sum];
    }
}