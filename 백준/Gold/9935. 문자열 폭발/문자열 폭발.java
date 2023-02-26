import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Q. 모든 폭발이 끝난 후에 어떤 문자열이 남는지 구해보려고 한다. 남아있는 문자가 없는 경우가 있다. 이때는 "FRULA"를 출력한다.
 * - 문자열에 폭발 문자열을 심어 놓았다. 폭발 문자열이 폭발하면 그 문자는 문자열에서 사라지며, 남은 문자열은 합쳐지게 된다.
 * - 문자열이 폭발 문자열을 포함하고 있는 경우에, 모든 폭발 문자열이 폭발하게 된다. 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.
 * - 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
 * - 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.
 * - 폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();

        // 첫째 줄에 문자열이 주어진다. 문자열의 길이는 1보다 크거나 같고, 1,000,000보다 작거나 같다.
        String str = br.readLine();

        // 둘째 줄에 폭발 문자열이 주어진다. 길이는 1보다 크거나 같고, 36보다 작거나 같다.
        // 두 문자열은 모두 알파벳 소문자와 대문자, 숫자 0, 1, ..., 9로만 이루어져 있다.
        String bomb = br.readLine();
        char last = bomb.charAt(bomb.length() - 1);

        // 문자열을 하나씩 스택에 담는다.
        // 폭발문자열의 마지막 단어가 나오면 앞의 스택을 차례대로 뽑아서 비교한다.
        // 폭파한다.
        // 아닐 경우 다시 스택에 넣는다.
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                StringBuilder sb = new StringBuilder();
                sb.append(last);
                int cnt = bomb.length() - 1;
                while (!stack.isEmpty() && cnt-- > 0) {
                    sb.append(stack.pop());
                }
                if(!bomb.equals(sb.reverse().toString())){
                    for(char c : sb.toString().toCharArray()){
                        stack.push(c);
                    }
                }
            }else{
                stack.push(str.charAt(i));
            }
        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder ans = new StringBuilder();
            while(!stack.isEmpty()){
                ans.append(stack.pop());
            }
            System.out.println(ans.reverse());
        }

    }

}