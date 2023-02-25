import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Q. 주어진 괄호 문자열에서 위의 조건을 만족하는 가장 긴 부분 문자열의 길이를 계산하는 프로그램을 작성해보자.
 * - () 는 올바른 괄호 문자열이다
 * - 어떤 문자열 x가 올바른 괄호 문자열이라면, (x)도 올바른 괄호 문자열이다.
 * - 어떤 문자열 x와 y가 올바른 괄호 문자열이라면, xy도 올바른 괄호 문자열이다.
 * - 괄호 문자열에서 연속한 일부분을 잘라서 올바른 괄호 문자열을 만들려고 한다.
 * - 그리고 이왕이면 긴 문자열이 좋으니 현욱은 부분 구간을 최대한 길게 잘라내려고 한다.
 */
public class Main {

    static int n, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에 문자열의 길이 n (1 ≤ n ≤ 200,000)이 주어진다.
        n = Integer.parseInt(br.readLine());

        // 둘째 줄에 괄호로만 구성된 길이 n짜리 문자열이 주어진다.
        String str = br.readLine();
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }

        // 주어진 문자열에서 길이가 가장 길면서 올바른 괄호 문자열인 부분 문자열의 길이를 출력한다.
        // 올바른 괄호 문자열인 부분 문자열을 찾을 수 없는 경우 0을 출력한다.
        if (max == Integer.MIN_VALUE) System.out.println(0);
        else System.out.println(max);
    }

}