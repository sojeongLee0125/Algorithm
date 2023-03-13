import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. n개의 정수로 이루어진 임의의 수열이 주어진다. 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다.
 * - 단, 수는 한 개 이상 선택해야 한다.
 */
public class Main {

    static int n, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다.
        // 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
        n = Integer.parseInt(br.readLine());

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int n = Integer.parseInt(st.nextToken());
            sum += n;
            max = Math.max(sum, max);
            if (sum < 0) sum = 0;
        }

        System.out.println(max);
    }

}