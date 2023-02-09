import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q.자연수 A를 B번 곱한 수를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Long A = Long.parseLong(st.nextToken());
        Long B = Long.parseLong(st.nextToken());
        Long C = Long.parseLong(st.nextToken());

        // 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
        System.out.println(sol(A, B, C));
    }

    private static Long sol(Long a, Long b, Long c) {
        if (b == 1) return a % c;
        Long res = sol(a, b / 2, c);
        res = (res * res) % c;
        if (b % 2 != 0) res = (res * a) % c;
        return res;
    }

}