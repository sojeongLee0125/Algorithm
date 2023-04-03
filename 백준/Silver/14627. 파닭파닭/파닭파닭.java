import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 파닭을 만들고 남은 파를 라면에 넣어 먹으려고 한다. 이때 라면에 넣을 파의 양을 구하는 프로그램을 작성하시오.
 */
public class Main {

    static int S, C;
    static long max = -1;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 승균이가 시장에서 사 온 파의 개수 S(1 ≤ S ≤ 1,000,000), 그리고 주문받은 파닭의 수 C(1 ≤ C ≤ 1,000,000)가 입력된다.
        // 파의 개수는 항상 파닭의 수를 넘지 않는다. (S ≤ C)
        // 하지만 하나의 파닭에는 하나 이상의 파가 들어가면 안 된다
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new long[S];

        long lt = 1L;
        long rt = 0L;
        long sum = 0L;

        // 그 후, S줄에 걸쳐 파의 길이 L(1 ≤ L ≤ 1,000,000,000)이 정수로 입력된다.
        for (int i = 0; i < S; i++) {
            arr[i] = Long.parseLong(br.readLine());
            rt = Math.max(rt, arr[i]);
            sum += arr[i];
        }

        // 1개의 파닭에 들어가는 파의 최대길이를 이분탐색으로 구한다.
        // 범위는 1 ~ 파 중 최대길이

        while (lt <= rt) {
            long mid = (lt + rt) / 2;
            if (isOk(mid)) {
                // 해당 길이의 파로 가능할 경우 길이를 늘린다.
                max = Math.max(max, mid);
                lt = mid + 1;
            } else rt = mid - 1;
        }

        System.out.println(sum - (C * max));
    }

    private static boolean isOk(long mid) {
        long cnt = 0L; // 만든 파닭의 갯수

        for (int i = 0; i < arr.length; i++) {
            cnt += arr[i] / mid;
        }

        return cnt >= C;
    }
}