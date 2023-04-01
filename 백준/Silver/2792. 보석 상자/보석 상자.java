import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 상자 안의 보석 정보와 학생의 수가 주어졌을 때, 질투심이 최소가 되게 보석을 나누어주는 방법을 알아내는 프로그램을 작성하시오.
 * - 각각의 보석은 M가지 서로 다른 색상 중 한 색상이다.
 * - 모든 보석을 N명의 학생들에게 나누어 주려고 한다.
 * - 이때, 보석을 받지 못하는 학생이 있어도 된다. 하지만, 학생은 항상 같은 색상의 보석만 가져간다.
 * - 질투심은 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수이다. 원장 선생님은 질투심이 최소가 되게 보석을 나누어 주려고 한다.
 */
public class Main {
    static int N, M, lt, rt, ans = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 아이들의 수 N과 색상의 수 M이 주어진다. (1 ≤ N ≤ 109, 1 ≤ M ≤ 300,000, M ≤ N)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 다음 M개 줄에는 구간 [1, 109]에 포함되는 양의 정수가 하나씩 주어진다. K번째 줄에 주어지는 숫자는 K번 색상 보석의 개수이다.
        arr = new int[M];

        // 1. 질투심의 최솟값은 1 ~ 최댓값은 보석갯수의 최대값
        lt = 1;
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            rt = Math.max(rt, arr[i]);
        }

        // 2. 질투심의 mid값을 기준으로 학생들에게 남김없이 나눌 수 있으면 값을 갱신한다.
        // 3. 최소값을 찾는다.
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (isOk(mid)) {
                ans = Math.min(ans, mid);
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }

        // 첫째 줄에 질투심의 최솟값을 출력한다.
        System.out.println(ans);
    }

    private static boolean isOk(int mid) {
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            cnt += arr[i] / mid;
            if (arr[i] % mid > 0) cnt++;
        }

        return cnt <= N;
    }

}