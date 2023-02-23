import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. X가 주어졌을 때, 위의 과정을 거친다면, 몇 개의 막대를 풀로 붙여서 Xcm를 만들 수 있는지 구하는 프로그램을 작성하시오.
 * - 지민이는 길이가 64cm인 막대를 가지고 있다
 * - 원래 가지고 있던 막대를 더 작은 막대로 자른다음에, 풀로 붙여서 길이가 Xcm인 막대를 만들려고 한다.
 * - 지민이가 가지고 있는 막대의 길이를 모두 더한다. 처음에는 64cm 막대 하나만 가지고 있다. 이때, 합이 X보다 크다면, 아래와 같은 과정을 반복한다.
 * - 가지고 있는 막대 중 길이가 가장 짧은 것을 절반으로 자른다.
 * - 만약, 위에서 자른 막대의 절반 중 하나를 버리고 남아있는 막대의 길이의 합이 X보다 크거나 같다면, 위에서 자른 막대의 절반 중 하나를 버린다.
 * - 남아있는 모든 막대를 풀로 붙여서 Xcm를 만든다.
 */
public class Main {

    static int X, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 X가 주어진다. X는 64보다 작거나 같은 자연수이다.
        X = Integer.parseInt(br.readLine());

        // 2진수로 변환해서 1이 몇개인지 구하는 프로그램
        String s = Integer.toBinaryString(X);
        for (char c : s.toCharArray()) {
            if (c == '1') cnt++;
        }

        System.out.println(cnt);
    }

}