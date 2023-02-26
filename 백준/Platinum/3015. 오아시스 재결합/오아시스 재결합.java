import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Q. 줄에 서있는 사람의 키가 주어졌을 때, 서로 볼 수 있는 쌍의 수를 구하는 프로그램을 작성하시오.
 * - N명이 한 줄로 서서 기다리고 있다.
 * - 두 사람 A와 B가 서로 볼 수 있으려면, 두 사람 사이에 A 또는 B보다 키가 큰 사람이 없어야 한다.
 */
public class Main {
    static Stack<int[]> stack = new Stack<>();
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 줄에서 기다리고 있는 사람의 수 N이 주어진다. (1 ≤ N ≤ 500,000)
        int N = Integer.parseInt(br.readLine());

        // 둘째 줄부터 N개의 줄에는 각 사람의 키가 나노미터 단위로 주어진다. 모든 사람의 키는 2^31 나노미터 보다 작다.
        // 두번째 부터 역순으로 자기보다 큰 사람이 나올 때까지의 쌍을 계산한다.
        while (N-- > 0) {
            int tmp = Integer.parseInt(br.readLine());
            int cnt = 1;

            // 마지막보다 크거나 같은 높이가 들어왔을 경우
            while (!stack.isEmpty() && stack.peek()[0] <= tmp) {
                // 이전의 cnt 값들을 결과값에 누적한다.
                res += stack.peek()[1];
                // 같은 높이가 들어온 경우 cnt 누적한다.
                if (stack.peek()[0] == tmp) {
                    cnt = stack.peek()[1] + 1;
                } else {
                    // 이후의 큰 것들은 생각할 필요가 없다. 분기점
                    cnt = 1;
                }
                stack.pop();
            }

            // 작은 높이가 들어왔을 경우 바로 옆과의 경우의 수만 추가한다.
            if (!stack.isEmpty()) res++;

            stack.push(new int[]{tmp, cnt});
        }

        System.out.println(res);
    }

}