import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Q. 단어 위로 아치형 곡선을 그어 같은 글자끼리(A는 A끼리, B는 B끼리) 쌍을 짓기로 하였다.
 * 만약 선끼리 교차하지 않으면서 각 글자를 정확히 한 개의 다른 위치에 있는 같은 글자와 짝 지을수 있다면, '좋은 단어'이다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 단어의 수 N이 주어진다. (1 ≤ N ≤ 100)
        int N = Integer.parseInt(br.readLine());

        // N개 줄에는 A와 B로만 이루어진 단어가 한 줄에 하나씩 주어진다.
        // 단어의 길이는 2와 100,000사이이며,
        // 모든 단어 길이의 합은 1,000,000을 넘지 않는다.

        // 해결 아이디어 : 스택
        int cnt = 0;
        while (N-- > 0) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            for (char c : str.toCharArray()) {
                if (!stack.isEmpty() && stack.peek() == c) stack.pop();
                else {
                    stack.push(c);
                }
            }
            if (stack.isEmpty()) cnt++;
        }

        // 첫째 줄에 좋은 단어의 수를 출력한다.
        System.out.println(cnt);

    }
}