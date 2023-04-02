import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.
 * - 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다.
 * - 블루레이의 개수를 가급적 줄이려고 한다. M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다.
 * - 이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다.
 */
public class Main {

    static int N, M, lt, rt, min = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 강의의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        // 강토의 기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로 주어진다.
        // 각 강의의 길이는 10,000분을 넘지 않는다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            lt = Math.max(arr[i], lt);
            rt += arr[i];
        }

        while (lt <= rt) {
            int mid = (rt + lt) / 2; // 블루레이 길이 값을 구한다.

            // 해당 값을 기준으로 블루레이에 하나씩 담아서 블루레이 총 갯수를 구한다.
            int cnt = 0;
            int cur = 0;

            for (int i = 0; i < N; i++) {
                if (cur + arr[i] <= mid) cur += arr[i];
                else {
                    cnt++;
                    cur = arr[i];
                }
            }

            // 마지막 처리
            if (cur != 0) cnt++;

            // 갯수를 만족하면 블루레이 크기 값을 줄여본다.
            if (cnt <= M) {
                min = Math.min(min, mid);
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }

        // 첫째 줄에 가능한 블루레이 크기중 최소를 출력한다.
        System.out.println(min);
    }

}