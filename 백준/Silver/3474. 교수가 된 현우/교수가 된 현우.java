import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 자연수 N이 주어지면 N!의 오른쪽 끝에 있는 0의 개수를 알려주기로 하였다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 테스트 케이스의 개수 T가 주어지고,
        int T = Integer.parseInt(br.readLine());

        // 이어서 T개의 줄에 정수 N이 주어진다(1 <= N <= 1000000000).
        // 각 줄마다 N!의 오른쪽 끝에 있는 0의 개수를 출력한다.
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int five = 0;

            // 2와 5의 쌍의 갯수만큼 0을 가진다.
            for (int i = 5; i <= N; i *= 5) {
                five += N / i;
            }

            System.out.println(five);

        }
    }

}