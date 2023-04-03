import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. X와 Y가 주어졌을 때, 형택이가 게임을 최소 몇 번 더 해야 Z가 변하는지 구하는 프로그램을 작성하시오.
 */
public class Main {
    static int X, Y, Z, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 각 줄에 정수 X와 Y가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        X = Integer.parseInt(st.nextToken()); // 게임 횟수
        Y = Integer.parseInt(st.nextToken()); // 이긴 게임 횟수

        Z = (int) (((long) Y * 100 ) / X); // 승률

        // 앞으로의 모든 게임에서 지지 않는다.
        // 1 -  1,000,000,000 의 게임을 이분탐색하면서 Z가 변하는 지점을 찾는다.
        int lt = 0;
        int rt = 1000000000;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if (isOk(mid)) {
                min = mid;
                // 더 범위를 작게 만들어 본다.
                rt = mid - 1;
            } else {
                // 게임을 더 해야 한다.
                lt = mid + 1;
            }
        }

        // 첫째 줄에 형택이가 게임을 최소 몇 판 더 해야하는지 출력한다. 만약 Z가 절대 변하지 않는다면 -1을 출력한다.
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static boolean isOk(int cnt) {
        int y = Y + cnt;
        int x = X + cnt;
        int nz = (int) ((long) y * 100 / x);

        return nz > Z;
    }

}