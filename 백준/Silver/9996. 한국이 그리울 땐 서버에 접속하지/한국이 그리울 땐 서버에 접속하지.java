import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 패턴과 파일 이름이 모두 주어졌을 때, 각각의 파일 이름이 패턴과 일치하는지 아닌지를 구하는 프로그램
 * - "abcd", "ad", "anestonestod"는 모두 패턴 "a*d"와 일치. 하지만, "bcd"는 일치하지 않는다.
 */
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 파일의 개수 N이 주어진다. (1 ≤ N ≤ 100)
        N = Integer.parseInt(br.readLine());

        // 둘째 줄에는 패턴이 주어진다.
        // 패턴은 알파벳 소문자와 별표(아스키값 42) 한 개로 이루어져 있다. 
        // 문자열의 길이는 100을 넘지 않으며, 별표는 문자열의 시작과 끝에 있지 않다.
        String pat = br.readLine();

        int idx = pat.indexOf("*");
        String first = pat.substring(0, idx);
        String last = pat.substring(idx + 1);

        // 다음 N개 줄에는 파일 이름이 주어진다. 
        // 파일 이름은 알파벳 소문자로만 이루어져 있고, 길이는 100을 넘지 않는다.
        while (N-- > 0) {
            boolean flag1 = false;
            boolean flag2 = false;
            String input = br.readLine();

            // 반례 - 패턴보다 주어진 글자가 짧은 경우 예외 처리
            if (input.length() < first.length() + last.length()) {
                System.out.println("NE");
                continue;
            }

            // 입력으로 주어진 i번째 파일 이름이 패턴과 일치하면 "DA", 일치하지 않으면 "NE"를 출력한다.
            if (input.startsWith(first)) flag1 = true;
            if (input.endsWith(last)) flag2 = true;
            if (flag1 && flag2) System.out.println("DA");
            else System.out.println("NE");
        }
    }
}