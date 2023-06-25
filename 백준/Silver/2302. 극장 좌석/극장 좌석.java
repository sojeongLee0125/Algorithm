import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q. VIP 회원들의 좌석 번호들이 주어졌을 때, 사람들이 좌석에 앉는 서로 다른 방법 구하기
 * - 어떤 극장의 좌석은 한 줄로 되어 있으며 왼쪽부터 차례대로 1번부터 N번 까지 번호가 매겨져 있다.
 * - 공연을 보러 온 사람들은 자기의 입장권에 표시되어 있는 좌석에 앉아야 한다.
 * - 단, 자기의 바로 왼쪽 좌석 또는 바로 오른쪽 좌석으로는 자리를 옮길 수 있다.
 * - 이 극장에는 “VIP 회원”들이 있다.
 * - 이 사람들은 반드시 자기 좌석에만 앉아야 하며 옆 좌석으로 자리를 옮길 수 없다.
 * - 1번 좌석부터 N번 좌석까지 모든 좌석이 다 팔렸다.
 */
public class Main {
    static int N, M;
    static int[] seats, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에는 좌석의 개수 N이 입력된다. N은 1 이상 40 이하이다.
        N = Integer.parseInt(br.readLine());

        seats = new int[N + 1];
        dp = new int[N + 1];

        // 둘째 줄에는 고정석의 개수 M이 입력된다. M은 0 이상 N 이하이다.
        M = Integer.parseInt(br.readLine());

        // 다음 M 개의 줄에는 고정석의 번호가 작은 수부터 큰 수의 순서로 한 줄에 하나씩 입력된다.
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            seats[num - 1] = 1;
        }

        // dp 배열 초기화
        Arrays.fill(dp, -1);

        // 주어진 조건을 만족하면서 사람들이 좌석에 앉을 수 있는 방법의 가짓수를 출력한다.
        // 방법의 가짓수는 2,000,000,000을 넘지 않는다. (2,000,000,000 < 231-1)
        System.out.println(findCount(0));
    }

    private static int findCount(int idx) {
        // 전체 좌석을 다 채운 경우
        if (idx >= N - 1) return 1;

        // 이미 해당 좌석이 채워진 경우(vip) 다음 좌석 탐색
        if (seats[idx] == 1) return findCount(idx + 1);

        // 메모이제이션
        if (dp[idx] != -1) return dp[idx];
        dp[idx] = 0;

        // 다음 좌석과 바꿀 수 있는 경우
        if (seats[idx + 1] == 0) dp[idx] += (findCount(idx + 2) + findCount(idx + 1));
        // 다음 좌석과 바꿀 수 없는 경우 -> 그대로 다음 인덱스 탐색
        else dp[idx] += findCount(idx + 1);

        return dp[idx];
    }
}