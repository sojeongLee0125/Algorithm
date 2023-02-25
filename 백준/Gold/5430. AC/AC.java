import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Q. 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.
 * - AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
 * - 함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
            String p = br.readLine();

            // 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
            br.readLine();

            // 다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
            String[] strs = br.readLine().replace("[", "")
                    .replace("]", "")
                    .split(",");

            Deque<Integer> deque = new ArrayDeque<>();

            for (String s : strs) {
                if (s.equals("")) break;
                deque.offer(Integer.parseInt(s));
            }

            boolean isRv = false;
            boolean isEr = false;

            for (char c : p.toCharArray()) {
                if (c == 'R') {
                    isRv = !isRv;
                } else {
                    if (deque.isEmpty()) {
                        isEr = true;
                        break;
                    }
                    if (isRv) deque.removeLast();
                    else deque.removeFirst();
                }
            }

            // 각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다
            if (isEr) System.out.println("error");
            else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                while (!deque.isEmpty()) {
                    sb.append(isRv ? deque.removeLast() : deque.removeFirst());
                    if (!deque.isEmpty()) sb.append(",");
                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }

}