import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Q. 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.
 * - 준규가 가지고 있는 동전은 총 N 종류, 각각의 동전을 매우 많이 가지고 있다.
 * - 동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> coins = new ArrayList<>();

        // 첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다. (1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)
        while (N-- > 0) {
            int coin = Integer.parseInt(br.readLine());
            if (coin <= K) coins.add(coin);
        }

        coins.sort(Comparator.reverseOrder());

        // 해결방안
        // 동전을 내림차순 정렬한다.
        // 큰 단위 가격부터 K로 나누고 그 나머지를 K 로 리정한다.
        int cnt = 0;
        while (K != 0) {
            for (int coin : coins) {
                cnt += (K / coin);
                K = K % coin;
            }
        }

        // 첫째 줄에 K원을 만드는데 필요한 동전 개수의 최솟값을 출력한다.
        System.out.println(cnt);
    }

}