import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Q. 크기가 N인 수열 A = A1, A2, ..., AN이 있다. 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다.
 * - 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다. 그러한 수가 없는 경우에 오큰수는 -1이다.
 */
public class Main {
    static int N;
    static int[] ans;
    static int[] rs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
        N = Integer.parseInt(br.readLine());
        ans = new int[N];
        rs = new int[N];

        Arrays.fill(rs, -1);

        // 둘째 줄에 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && ans[stack.peek()] < ans[i]) {
                rs[stack.pop()] = ans[i];
            }
            stack.push(i);
        }

        // 총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.
        for (int i = 0; i < N; i++) {
            bw.write(rs[i] + " ");
        }

        bw.flush();
        bw.close();
    }

}