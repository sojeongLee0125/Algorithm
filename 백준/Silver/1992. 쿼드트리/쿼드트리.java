import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. N×N 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하는 프로그램을 작성
 * - 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고,
 * - 모두 1로만 되어 있으면 압축 결과는 "1"이 된다.
 * - 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래,
 * - 이렇게 4개의 영상으로 나누어 압축하게 되며, 이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다
 */
public class Main {
    static char[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(sol(0, 0, N));
    }

    private static String sol(int y, int x, int size) {
        // 사이즈가 1일 경우는 나누지 못하므로 바로 해당 숫자를 리턴한다.
        if (size == 1) return String.valueOf(map[y][x]);

        // 해당범위 내를 검사 한다. 모두 같으면 같은 숫자로 통일한다.
        String ans = "";
        char cur = map[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                // 다른 숫자가 섞여 있으면 사이즈를 반으로 4등분으로 나눈다.
                if (cur != map[i][j]) {
                    ans += "(";
                    ans += sol(y, x, size / 2);
                    ans += sol(y, x + size / 2, size / 2);
                    ans += sol(y + size / 2, x, size / 2);
                    ans += sol(y + size / 2, x + size / 2, size / 2);
                    ans += ")";
                    return ans;
                }
            }
        }
        return String.valueOf(map[y][x]);
    }

}