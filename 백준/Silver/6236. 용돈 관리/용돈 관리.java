import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 현우가 필요한 최소 금액 K를 계산하는 프로그램을 작성하시오.
 * - 현우는 앞으로 N일 동안 자신이 사용할 금액을 계산하였고, 돈을 펑펑 쓰지 않기 위해 정확히 M번만 통장에서 돈을 빼서 쓰기로 하였다.
 * - 통장에서 K원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출한다.
 * - 현우는 M이라는 숫자를 좋아하기 때문에, 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다.
 * - 현우는 돈을 아끼기 위해 인출 금액 K를 최소화하기로 하였다.
 */
public class Main {

    static int N, M, lt, rt, K = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1번째 줄에는 N과 M이 공백으로 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ N)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        // 2번째 줄부터 총 N개의 줄에는 현우가 i번째 날에 이용할 금액이 주어진다. (1 ≤ 금액 ≤ 10000)
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            rt += arr[i];
        }

        lt = 1;
        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            int cnt = 1;
            int cur = mid;

            for (int i = 0; i < N; i++) {
                // 현재 남은 금액에서 해당 금액을 사용해도 0을 넘는지 체크
                if (cur - arr[i] >= 0) cur -= arr[i];
                else {
                    // 통장에 남은 금액을 다시 집어넣고 새로 인출해서 사용한다.
                    cur = mid;
                    cnt++;
                    if(arr[i] > mid) {
                        cnt = M + 10;
                        break;
                    }
                    cur -= arr[i];
                }
            }

            // 인출 횟수가 M보다 작을 경우 금액을 최소화 할 수 있다.
            if (cnt <= M) {
                rt = mid - 1;
                K = Math.min(K, mid);
            } else {
                lt = mid + 1;
            }
        }

        // 첫 번째 줄에 현우가 통장에서 인출해야 할 최소 금액 K를 출력한다.
        System.out.println(K);


    }

}