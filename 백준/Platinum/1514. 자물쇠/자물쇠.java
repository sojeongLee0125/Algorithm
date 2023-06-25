import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q. 현재 자물쇠의 상태와 세준이의 비밀번호가 주어질 때,
 * 자물쇠를 최소 몇 번 돌려야 풀 수 있는지 구하는 프로그램을 작성하시오.
 * - 자물쇠는 동그란 디스크 N개로 구성되어 있다.
 * - 각 디스크에는 숫자가 0부터 9까지 숫자가 표시되어 있다.
 * - 디스크는 원형이기 때문에, 0과 9는 인접해 있다.
 * - 한 번에 최대 세 칸을 시계 방향 또는 반시계 방향으로 돌릴 수 있다.
 * - 최대 세 개의 인접한 디스크를 한 번에 돌릴 수 있다.
 */
public class Main {
    static int INF = 987654321;
    static int N;
    static int[] cur, pass;

    // 인덱스, 동시에 움직일 수 있는 3개 숫자, 시계방향/반시계방향
    static int[][][][][] dp = new int[105][10][10][10][2];

    private static int check(int num) {
        // 시계 방향으로 돌려서 9를 초과할 경우 대비 % 연산
        // 반시계 방향으로 돌려서 음수가 되었을 경우 대비 +10
        return (num < 0) ? num + 10 : num % 10;
    }

    private static int find(int pos, int x, int y, int z, int flag) {

        // 비밀번호 전체 자리 다 계산된 경우
        if (pos == N) return 0;

        // 메모이제이션
        if (dp[pos][x][y][z][flag] != INF) return dp[pos][x][y][z][flag];

        // 첫번째 자리수가 일치할 경우
        if (check(x + cur[pos]) == check(pass[pos])) {
            return dp[pos][x][y][z][flag] = Math.min(
                    find(pos + 1, y, z, 0, 0), // 시계 방향
                    find(pos + 1, y, z, 0, 1)); // 반시계 방향
        }

        // 회전 방향
        int dir = (flag == 1) ? 1 : -1;

        // 3가지 경우의 수 (1 ~ 3칸)
        for (int i = 1; i <= 3; i++) {
            // 1개 이동
            dp[pos][x][y][z][flag] = Math.min(dp[pos][x][y][z][flag],
                    1 + find(pos, check(x + (i * dir)), y, z, flag)
            );

            // 2개 이동
            dp[pos][x][y][z][flag] = Math.min(dp[pos][x][y][z][flag],
                    1 + find(pos, check(x + (i * dir)),
                            check(y + (i * dir)), z, flag)
            );

            // 3개 이동
            dp[pos][x][y][z][flag] = Math.min(dp[pos][x][y][z][flag],
                    1 + find(pos, check(x + (i * dir)),
                            check(y + (i * dir)),
                            check(z + (i * dir)), flag)
            );
        }

        return dp[pos][x][y][z][flag];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initDp();

        // 첫째 줄에 세준이의 비밀번호의 길이 (자물쇠의 크기) N이 주어진다.
        // N은 100보다 작거나 같다.
        N = Integer.parseInt(br.readLine());
        cur = new int[N];
        pass = new int[N];

        // 둘째 줄에 현재 자물쇠의 상태가 주어지고,
        // 셋째 줄에 세준이의 비밀번호가 주어진다.
        String status = br.readLine();
        String password = br.readLine();

        for (int i = 0; i < N; i++) {
            cur[i] = status.charAt(i) - '0';
            pass[i] = password.charAt(i) - '0';
        }

        // 첫째 줄에 최소 몇 번을 돌려야 풀 수 있는지 구하는 프로그램을 작성하시오.
        System.out.println(
                Math.min(
                // 시계방향
                find(0, 0, 0, 0, 0),
                // 반시계방향
                find(0, 0, 0, 0, 1)
                )
        );
    }

    private static void initDp() {
        for (int[][][][] arr1 : dp) {
            for (int[][][] arr2 : arr1) {
                for (int[][] arr3 : arr2) {
                    for (int[] arr4 : arr3) {
                        Arrays.fill(arr4, INF);
                    }
                }
            }
        }
    }
}