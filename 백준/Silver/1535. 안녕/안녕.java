import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 세준이가 얻을 수 있는 최대 기쁨을 출력하는 프로그램을 작성하시오.
 * - 세준이를 생각해준 사람은 총 N명이 있다. 사람의 번호는 1번부터 N번 까지 있다.
 * - 세준이가 i번 사람에게 인사를 하면 L[i]만큼의 체력을 잃고, J[i]만큼의 기쁨을 얻는다.
 * - 세준이는 각각의 사람에게 최대 1번만 말할 수 있다.
 * - 세준이의 목표는 주어진 체력내에서 최대한의 기쁨을 느끼는 것이다.
 * - 세준이의 체력은 100이고, 기쁨은 0이다. 체력이 0이나 음수가 되면, 아무런 기쁨을 못 느낀 것이 된다.
 */
public class Main {
    static int N;
    static int[] health = new int[105];
    static int[] happy = new int[105];
    static int[] dp = new int[105];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 사람의 수 N(≤ 20)이 들어온다.
        N = Integer.parseInt(br.readLine());

        //  체력과 기쁨은 100보다 작거나 같은 자연수 또는 0이다.
        //  둘째 줄에는 각각의 사람에게 인사를 할 때, 잃는 체력이 1번 사람부터 순서대로 들어오고,
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            health[i] = Integer.parseInt(st.nextToken());
        }

        //  셋째 줄에는 각각의 사람에게 인사를 할 때, 얻는 기쁨이 1번 사람부터 순서대로 들어온다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 100; j > health[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - health[i]] + happy[i]);
            }
        }

        // 첫째 줄에 세준이가 얻을 수 있는 최대 기쁨을 출력한다.
        System.out.println(dp[100]);
    }
}