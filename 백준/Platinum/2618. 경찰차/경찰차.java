import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 처리해야 할 사건들이 순서대로 주어질 때, 
 *    두 대의 경찰차가 이동하는 거리의 합을 최소화 하도록
 *     사건들을 맡기는 프로그램을 작성하시오.
 *
 * - 어떤 도시의 중심가는 N개의 동서방향 도로와 N개의 남북방향 도로로 구성되어 있다.
 * - 남북방향 도로는 왼쪽부터 1에서 시작하여 N까지 번호가 할당되어 있고
 * - 동서방향 도로는 위부터 1에서 시작하여 N까지 번호가 할당되어 있다.
 * - 동서방향 도로 사이의 거리와 남북방향 도로 사이의 거리는 모두 1이다.
 * - 동서방향 도로와 남북방향 도로가 교차하는 교차로의 위치는
 * - 두 도로의 번호의 쌍인 (동서방향 도로 번호, 남북방향 도로 번호)로 나타낸다.
 * 
 * - 이 도시에는 두 대의 경찰차가 있으며 두 차를 경찰차1과 경찰차2로 부른다.
 * - 처음에는 항상 경찰차1은 (1, 1)의 위치에 있고 경찰차2는 (N, N)의 위치에 있다.
 * - 처리할 사건이 있으면 그 사건이 발생된 위치를 두 대의 경찰차 중 하나에 알려 주고,
 * - 연락 받은 경찰차는 그 위치로 가장 빠른 길을 통해 이동하여 사건을 처리한다.
 * - 사건을 처리 한 경찰차는 다음 연락이 올 때까지 처리한 사건이 발생한 위치에서 기다린다.
 * 
 * - 사건들을 나누어 두 대의 경찰차에 맡기되, 두 대의 경찰차들이 이동하는 거리의 합을 최소화
 */
public class Main {
    static int N, W;
    
    // 사건 번호 별 거리의 위치 기록
    static int[] dirY = new int[1005];
    static int[] dirX = new int[1005];

    // 경찰차1의 위치, 경찰차2의 위치
    static int[][] dp = new int[1005][1005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 동서방향 도로의 개수를 나타내는 정수 N(5 ≤ N ≤ 1,000)이 주어진다.
        N = Integer.parseInt(br.readLine());

        // 처리해야 하는 사건의 개수를 나타내는 정수 W(1 ≤ W ≤ 1,000)가 주어진다.
        W = Integer.parseInt(br.readLine());

        // 경찰차 배열과 사건의 위치를 같은 배열로 담는다.

        // 경찰차 1번 -> 0번 사건의 위치
        dirY[0] = 1;
        dirX[0] = 1;

        // 경찰차 2번 -> 1번 사건의 위치
        dirY[1] = N;
        dirX[1] = N;

        // 셋째 줄부터 사건이 발생된 위치가 한 줄에 하나씩 주어진다.
        // 두 정수 사이에는 빈칸이 하나 있다. 두 사건이 발생한 위치가 같을 수 있다.

        // 사건번호는 2부터 입려
        for (int i = 2; i < W + 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            dirY[i] = Integer.parseInt(st.nextToken());
            dirX[i] = Integer.parseInt(st.nextToken());
        }

        // 첫째 줄에 두 경찰차가 이동한 총 거리를 출력한다.
        System.out.println(getTotalDistance(0, 1));

        // 둘째 줄부터 시작하여 사건이 맡겨진 경찰차 번호 1 또는 2를 출력한다.
        trace();
    }

    private static int getTotalDistance(int car1, int car2) {
        // 모든 사건 완료
        if (car1 == W + 1 || car2 == W + 1) return 0;

        // 메모이제이션
        if (dp[car1][car2] != 0) return dp[car1][car2];

        // 다음 사건 번호
        int nxt = Math.max(car1, car2) + 1;

        return dp[car1][car2] = Math.min(
                // 경찰차 1이 해결
                getTotalDistance(nxt, car2) + getDistance(car1, nxt),
                // 경찰차 2가 해결
                getTotalDistance(car1, nxt) + getDistance(car2, nxt));
    }

    private static int getDistance(int from, int to) {
        return Math.abs(dirY[from] - dirY[to]) + Math.abs(dirX[from] - dirX[to]);
    }

    private static void trace() {
        int car1 = 0;
        int car2 = 1;

        for (int i = 2; i < W + 2; i++) {
            // 경찰차 1이 해결한 경우
            if (dp[i][car2] + getDistance(car1, i) < dp[car1][i] + getDistance(car2, i)) {
                System.out.println(1);
                car1 = i;
            } else {
                // 경찰차 2가 해결한 경우
                System.out.println(2);
                car2 = i;
            }
        }
    }

}