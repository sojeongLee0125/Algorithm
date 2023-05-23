import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 종이가 주어졌을 때, 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수를 구해보자.
 * - 정사각형 모양을 한 다섯 종류의 색종이가 있다.
 * - 색종이의 크기는 1×1, 2×2, 3×3, 4×4, 5×5로 총 다섯 종류, 각 종류의 색종이는 5개씩
 * - 색종이를 크기가 10×10인 종이 위에 붙이려고 한다.
 * - 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 0 또는 1이 적혀 있다.
 * - 1이 적힌 칸은 모두 색종이로 덮여져야 한다. 0이 적힌 칸에는 색종이가 있으면 안 된다.
 * - 색종이를 붙일 때는 종이의 경계 밖으로 나가서는 안되고, 겹쳐도 안 된다.
 * - 또, 칸의 경계와 일치하게 붙여야 한다.
 */
public class Main {
    static int answer = Integer.MAX_VALUE;
    static int[][] board = new int[10][10];
    static int[] paper = new int[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 10개의 줄에 종이의 각 칸에 적힌 수가 주어진다.
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0, 0, 0); // y, x, 색종이 사용 수

        // 모든 1을 덮는데 필요한 색종이의 최소 개수를 출력한다.
        // 1을 모두 덮는 것이 불가능한 경우에는 -1을 출력한다.
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void find(int y, int x, int cnt) {
        // 최소값을 넘은 경우
        if (cnt >= answer) return;

        // 열이 끝에 도달한 경우 행을 증가
        if (x == 10) {
            find(y + 1, 0, cnt);
            return;
        }

        // 행의 끝에 도달한 경우 결과 계산
        if (y == 10) {
            answer = Math.min(answer, cnt);
            return;
        }

        // 색종일를 덮을 구역이 아닌 경우
        if (board[y][x] == 0) {
            find(y, x + 1, cnt);
            return;
        }

        // 5사이즈부터 하나씩 검사하며 붙여나가기
        for (int size = 5; size > 0; size--) {
            if (paper[size] == 5) continue;
            if (isOk(y, x, size)) {
                // 색종이 덮기
                paper[size]++;
                putOrRemove(y, x, size, 0);
                // dfs
                find(y, x + size, cnt + 1);
                // 원상 복구
                putOrRemove(y, x, size, 1);
                paper[size]--;
            }
        }
    }

    private static boolean isOk(int y, int x, int size) {
        if (y + size > 10 || x + size > 10) return false;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (board[i][j] == 0) return false;
            }
        }

        return true;
    }

    private static void putOrRemove(int y, int x, int size, int value) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                board[i][j] = value;
            }
        }
    }

}