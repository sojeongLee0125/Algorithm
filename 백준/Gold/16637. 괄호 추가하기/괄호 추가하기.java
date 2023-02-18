import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 16637 마저 풀기
 * Q. 수식이 주어졌을 때, 괄호를 적절히 추가해 만들 수 있는 식의 결과의 최댓값을 구하는 프로그램을 작성하시오.
 * - 길이가 N인 수식이 있다. 수식은 0보다 크거나 같고, 9보다 작거나 같은 정수와 연산자(+, -, ×)로 이루어져 있다.
 * - 수식에 괄호를 추가하면, 괄호 안에 들어있는 식은 먼저 계산해야 한다. 단, 괄호 안에는 연산자가 하나만 들어 있어야 한다.
 * - 하지만, 중첩된 괄호는 사용할 수 없다.
 * - 추가하는 괄호 개수의 제한은 없으며, 추가하지 않아도 된다.
 */
public class Main {
    static int N, max = Integer.MIN_VALUE;
    static ArrayList<Integer> nums = new ArrayList<>();
    static ArrayList<Character> ops = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 수식의 길이 N(1 ≤ N ≤ 19)가 주어진다.
        N = Integer.parseInt(br.readLine());

        // 둘째 줄에는 수식이 주어진다. 문자열은 정수로 시작하고, 연산자와 정수가 번갈아가면서 나온다.
        // 항상 올바른 수식만 주어지기 때문에, N은 홀수이다.
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) nums.add(str.charAt(i) - '0');
            else ops.add(str.charAt(i));
        }

        calc(0, nums.get(0));

        // 첫째 줄에 괄호를 적절히 추가해서 얻을 수 있는 결과의 최댓값을 출력한다. 정답은 231보다 작고, -231보다 크다.
        System.out.println(max);
    }

    private static void calc(int idx, int sum) {
        if (idx == nums.size() - 1) {
            max = Math.max(max, sum);
        } else {
            // 괄호 없이 진행할 경우
            calc(idx + 1, cal(sum, nums.get(idx + 1), ops.get(idx)));

            // 괄호를 추가할 경우
            if (idx + 2 <= nums.size() - 1) {
                int tmp = cal(nums.get(idx + 1), nums.get(idx + 2), ops.get(idx + 1));
                calc(idx + 2, cal(sum, tmp, ops.get(idx)));
            }
        }
    }

    private static int cal(int n1, int n2, char op) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            default:
                return 0;
        }
    }

}