import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 종이를 적절히 잘라서 조각의 합을 최대로 하는 프로그램을 작성하시오.
 * - 영선이는 숫자가 쓰여 있는 직사각형 종이를 가지고 있다.
 * - 영선이는 직사각형을 겹치지 않는 조각으로 자르려고 한다. 각 조각은 크기가 세로나 가로 크기가 1인 직사각형 모양이다.
 * - 길이가 N인 조각은 N자리 수로 나타낼 수 있다.
 * - 가로 조각은 왼쪽부터 오른쪽까지 수를 이어 붙인 것이고, 세로 조각은 위에서부터 아래까지 수를 이어붙인 것이다.
 * -
 */
public class Main {

    static int N, M, max;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 첫째 줄에 종이 조각의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 4)
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 둘째 줄부터 종이 조각이 주어진다. 각 칸에 쓰여 있는 숫자는 0부터 9까지 중 하나이다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 해결 방안
        // N X M 자리수의 이진수를 만든다.
        // 각 자리수마다 1일 경우 가로 0일 경우 세로로 계산한다.
        // 조각의 합을 구하고 갱신한다.
        for (int i = 0; i < (1 << (N * M)); i++) {
            int sum = 0;
            // 가로로 된 수들 합 계산
            for (int y = 0; y < N; y++) {
                int num = 0;
                for (int x = 0; x < M; x++) {
                    int idx = (y * M) + x;
                    if ((i & (1 << idx)) != 0) {
                        num = num * 10 + map[y][x];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }

            //세로로된 수들 합 계산
            for (int x = 0; x < M; x++) {
                int num = 0;
                for (int y = 0; y < N; y++) {
                    int idx = (y * M) + x;
                    if ((i & (1 << idx)) == 0) {
                        num = num * 10 + map[y][x];
                    } else {
                        sum += num;
                        num = 0;
                    }
                }
                sum += num;
            }

            max = Math.max(max, sum);
        }

        // 영선이가 얻을 수 있는 점수의 최댓값을 출력한다.
        System.out.println(max);
    }

}