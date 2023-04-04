import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Q. 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 */
public class Main {

    static int N;
    static int[] arr, idxArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        idxArr = new int[N];

        //둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && idxArr[j] > max) {
                    max = idxArr[j];
                }
            }
            idxArr[i] = max + 1;
            ans = Math.max(ans, idxArr[i]);
        }
        System.out.println(ans);

        int ed = ans;
        Stack<Integer> s = new Stack<>();
        for (int i = N-1; i >= 0; i--) {
            if (idxArr[i] == ed) {
                s.push(arr[i]);
                ed--;
            }
        }

        while (!s.isEmpty()) System.out.print(s.pop() + " ");

    }

}