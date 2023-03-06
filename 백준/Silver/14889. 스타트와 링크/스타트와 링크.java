import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다.
 * - 축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다. 이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.
 * - 사람에게 번호를 1부터 N까지로 배정했고, 능력치 Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
 * - 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합이다. Sij는 Sji와 다를 수도 있으며,
 * - i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.
 */
public class Main {

    static int N, diff = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N(4 ≤ N ≤ 20, N은 짝수)이 주어진다.
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 둘째 줄부터 N개의 줄에 S가 주어진다.
        // Sii는 항상 0이고, 나머지 Sij는 1보다 크거나 같고, 100보다 작거나 같은 정수이다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 해결방안
        // 비트마스킹으로 각각의 경우의 수를 구한다 (완전탐색)
        // 이때 정확히 반으로 나뉜 경우에 해당 경우의 수에서 각 팀의 능력치를 구한뒤 차이의 최솟값을 갱신한다.
        for (int i = 0; i < (1 << N); i++) {
            ArrayList<Integer> start = new ArrayList<>();
            ArrayList<Integer> link = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    start.add(j);
                } else {
                    link.add(j);
                }
            }
            if (start.size() != link.size()) continue;
            else {
                int cnt1 = 0;
                int cnt2 = 0;
                for (int y = 0; y < N / 2; y++) {
                    for (int x = 0; x < N / 2; x++) {
                        if (y == x) continue;
                        cnt1 += map[start.get(y)][start.get(x)];
                        cnt2 += map[link.get(y)][link.get(x)];

                    }
                }
                diff = Math.min(diff, Math.abs(cnt2 - cnt1));
            }
        }

        // 첫째 줄에 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력한다.
        System.out.println(diff);

    }


}

