import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Q. 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다.
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            for (char c : str.toCharArray()) {
                if (c == '(') stack.push(c);
                else if (c == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                    else stack.push(c);
                }
            }

            if (stack.isEmpty()) System.out.println("YES");
            else System.out.println("NO");

        }


    }

}