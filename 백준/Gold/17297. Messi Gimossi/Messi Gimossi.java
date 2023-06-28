import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

/**
 * Q. N이 충분히 클 때, messi(N)의 M번째 글자
 * - 메시는 축구 선수이다. 메시는 기분이 좋다.
 * - 메시의 외침은 피보나치 수열과 유사하게 정의된다.
 * - messi(N)은 messi(N-1), 공백, messi(N-2)을 차례로 이어붙여서 만든 문자열이다.
 */
public class Main {

    static final int MAX = 39;

    static int[] dp = new int[MAX + 5];
    static int M;

    static String str = "Messi Gimossi";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정수 M이 주어진다. (1 ≤ M ≤ 2^30-1)
        M = Integer.parseInt(br.readLine());
        M--;

        // 초기값 세팅
        dp[0] = 0;
        dp[1] = 5;
        dp[2] = 13;

        // 글자수로 dp 배열 생성
        for (int i = 3; i <= MAX; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + 1;
        }

        for (int i = MAX; i >= 2; i--) {
            if (M >= dp[i]) M -= (dp[i] + 1);
        }

        // N이 충분히 클 때, messi(N)의 M번째 글자가 공백(' ')이 아닐 경우에는 그 글자를 출력한다.
        // M번째 글자가 공백(' ')일 경우에는 Messi Messi Gimossi를 출력한다.

        if (M == 5 || M == -1) System.out.println("Messi Messi Gimossi");
        else System.out.println(str.charAt(M));
    }
}