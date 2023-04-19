import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 숌이 만든 N번째 영화의 제목에 들어간 수를 출력하는 프로그램을 작성하시오.
 * - 종말의 수란 어떤 수에 6이 적어도 3개 이상 연속으로 들어가는 수를 말한다.
 * - N번째 영화의 제목은 세상의 종말 (N번째로 작은 종말의 수)와 같다.
 * - 숌은 이 시리즈를 항상 차례대로 만들고, 다른 영화는 만들지 않는다.
 */
public class Main {
    static int N, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫째 줄에 N이 주어진다. N은 10,000보다 작거나 같은 자연수이다.
        N = Integer.parseInt(br.readLine());
        
        for (int i = 666; ; i++) {
            if (String.valueOf(i).contains("666")) {
                N--;
                ans = i;
            }
            if (N == 0) break;
        }

        // 첫째 줄에 N번째 영화의 제목에 들어간 수를 출력한다.
        System.out.println(ans);
    }
}