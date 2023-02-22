import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q.한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업들을 수행하여 뒷면이 위를 향하는 동전 개수를 최소로 하려 한다.
 * 최소 개수를 구하는 프로그램을 작성하시오.
 * - N^2개의 동전이 N행 N열을 이루어 탁자 위에 놓여 있다.
 * - 그 중 일부는 앞면(H)이 위를 향하도록 놓여 있고, 나머지는 뒷면(T)이 위를 향하도록 놓여 있다.
 * - 이들 N^2개의 동전에 대하여 임의의 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업을 수행할 수 있다.
 * - 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업을 계속 수행할 때 뒷면이 위를 향하도록 놓인 동전의 개수를 2개보다 작게 만들 수는 없다.
 */
public class Main {

    static int N, min = Integer.MAX_VALUE;
    static int[] row;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 20이하의 자연수 N이 주어진다.
        N = Integer.parseInt(br.readLine());
        row = new int[N];

        // 둘째 줄부터 N줄에 걸쳐 N개씩 동전들의 초기 상태가 주어진다.
        // 앞면이 위를 향하도록 놓인 경우 H(0), 뒷면이 위를 향하도록 놓인 경우 T(1)로 표시되며 이들 사이에 공백은 없다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int idx = 1;
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == 'T') row[i] += idx;
                idx *= 2;
            }
        }

        // 행만 뒤집으면 열은 자동으로 결정된다.
        game(1);

        // 뒷면이 위를 향하여 놓일 수 있는 동전의 최소 개수를 출력한다.
        System.out.println(min);
    }

    private static void game(int cnt) {
        if (cnt >= N) {
            int sum = 0;
            for (int i = 1; i < (1 << N); i *= 2) {
                int c = 0;
                for (int j = 0; j < N; j++) {
                    // 뒷면의 갯수 구하기
                    if ((row[j] & i) != 0) c++;
                    // 열의 경우는 자동으로 정해지니 둘 중 작은 쪽을 선택
                }
                sum += Math.min(c, N - c);
            }
            min = Math.min(min, sum);
        } else {
            // 그대로 진행할 경우
            game(cnt + 1);
            // 현재 행을 뒤집어서 진행
            row[cnt] = ~row[cnt];
            game(cnt + 1);
        }
    }

}