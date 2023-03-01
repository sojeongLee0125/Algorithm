import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 길이가 N인 수열이 주어질 때, 수열에서 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수를 구하는 프로그램을 작성하여라.
 */
public class Main {

    static int rt, N;
    static long cnt;
    static int[] arr;
    static int[] chk = new int[100005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에는 수열의 길이 N이 주어진다. (1 ≤ N ≤ 100,000)
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        // 두 번째 줄에는 수열을 나타내는 N개의 정수가 주어진다. 수열에 나타나는 수는 모두 1 이상 100,000 이하이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int lt = 1; lt <= N; lt++) {
            // 중복이거나 끝에 도달하기 전까지 rt 전진
            while (rt + 1 <= N && chk[arr[rt + 1]] == 0) {
                chk[arr[++rt]] = 1;
            }

            // 정답 카운팅
            cnt += (rt - lt + 1);
            chk[arr[lt]] = 0;
        }

        System.out.println(cnt);
    }

}