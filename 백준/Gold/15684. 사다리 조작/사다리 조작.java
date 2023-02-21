import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 15684 사다리에 가로선을 추가해서, 사다리 게임의 결과를 조작하려고 한다. 이때, i번 세로선의 결과가 i번이 나와야 한다.
 * 그렇게 하기 위해서 추가해야 하는 가로선 개수의 최솟값을 구하는 프로그램을 작성하시오.
 * - 사다리 게임은 N개의 세로선과 M개의 가로선으로 이루어져 있다.
 * - 인접한 세로선 사이에는 가로선을 놓을 수 있는데, 각각의 세로선마다 가로선을 놓을 수 있는 위치의 개수는 H이고, 모든 세로선이 같은 위치를 갖는다.
 * - 가로선은 인접한 두 세로선을 연결해야 한다. 단, 두 가로선이 연속하거나 서로 접하면 안 된다.
 * - 사다리 게임은 각각의 세로선마다 게임을 진행하고, 세로선의 가장 위에서부터 아래 방향으로 내려가야 한다.
 */
public class Main {
    static int N, M, H, min = Integer.MAX_VALUE;
    static int[][] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 세로선의 개수 N, 가로선의 개수 M, 세로선마다 가로선을 놓을 수 있는 위치의 개수 H가 주어진다.
        // (2 ≤ N ≤ 10, 1 ≤ H ≤ 30, 0 ≤ M ≤ (N-1)×H)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        line = new int[H + 2][N + 2];

        // 둘째 줄부터 M개의 줄에는 가로선의 정보가 한 줄에 하나씩 주어진다.
        // 가로선의 정보는 두 정수 a과 b로 나타낸다. (1 ≤ a ≤ H, 1 ≤ b ≤ N-1) b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결했다는 의미이다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            line[a][b] = 1;
        }

        // 해결방안
        // int[][] 에 연결되는 선을 기록한다.
        go(1, 0);

        // i번 세로선의 결과가 i번이 나오도록 사다리 게임을 조작하려면, 추가해야 하는 가로선 개수의 최솟값을 출력한다.
        // 만약, 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static void go(int cur, int cnt) {
        if (cnt > 3 || cnt >= min) return;
        if (isOk()) {
            min = Math.min(min, cnt);
        } else {
            for (int i = cur; i <= H; i++) {
                for (int j = 1; j <= N; j++) {
                    if (line[i][j] == 1 ||
                            (line[i][j - 1] == 1) ||
                            (line[i][j + 1] == 1)) continue;
                    line[i][j] = 1;
                    go(i, cnt + 1);
                    line[i][j] = 0;
                }
            }
        }
    }

    private static boolean isOk() {
        for (int i = 1; i <= N; i++) {
            int start = i;
            for (int j = 1; j <= H; j++) {
                if (line[j][start] == 1) start++;
                else if (line[j][start - 1] == 1) start--;
            }
            if (start != i) return false;
        }
        return true;
    }

}