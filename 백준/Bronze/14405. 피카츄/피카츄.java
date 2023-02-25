import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 문자열 S가 주어졌을 때, 피카츄가 발음할 수 있는 문자열인지 아닌지 구하는 프로그램을 작성하시오.
 * - 피카츄는 "pi", "ka", "chu"를 발음할 수 있다. 따라서, 피카츄는 이 세 음절을 합친 단어만 발음할 수 있다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 문자열 S가 주어진다. 문자열은 알파벳 소문자로 이루어진 문자열이며, 길이는 5000을 넘지 않는다.
        String s = br.readLine();

        // 문자열 S가 "pi", "ka", "chu"를 이어 붙여서 만들 수 있으면 "YES"를 아니면 "NO"를 출력한다.
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            if ((i < s.length() - 1) && (s.charAt(i) == 'p' && s.charAt(i + 1) == 'i')) i++;
            else if ((i < s.length() - 1) && (s.charAt(i) == 'k' && s.charAt(i + 1) == 'a')) i++;
            else if ((i < s.length() - 2) && (s.charAt(i) == 'c' && s.charAt(i + 1) == 'h' && s.charAt(i + 2) == 'u')) i += 2;
            else {
                flag = false;
                break;
            }
        }

        if (flag) System.out.println("YES");
        else System.out.println("NO");
    }

}