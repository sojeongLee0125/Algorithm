import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 2와 5로 나누어 떨어지지 않는 정수 n(1 ≤ n ≤ 10000)가 주어졌을 때, 1로만 이루어진 n의 배수
 * 1로 이루어진 n의 배수 중 가장 작은 수의 자리수를 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while ((input = br.readLine()) != null) {
            int cnt = 1;
            int res = 1;
            Long num = Long.parseLong(input);
            while (true) {
                if (cnt % num == 0) {
                    System.out.println(res);
                    break;
                } else {
                    cnt = (cnt * 10) + 1;
                    cnt %= num;
                    res++;
                }
            }
        }
    }
}