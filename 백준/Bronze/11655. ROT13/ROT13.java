import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 문자열이 주어졌을 때, "ROT13"으로 암호화한 다음 출력하는 프로그램
 * - ROT13은 카이사르 암호의 일종으로 영어 알파벳을 13글자씩 밀어서 만든다.
 * - ROT13은 알파벳 대문자와 소문자에만 적용할 수 있다.
 * - 알파벳이 아닌 글자는 원래 글자 그대로 남아 있어야 한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 알파벳 대문자, 소문자, 공백, 숫자로만 이루어진 문자열 S가 주어진다. S의 길이는 100을 넘지 않는다.
        String str = br.readLine();
        StringBuilder ans = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) {
                if (c + 13 <= 'z') ans.append((char) (c + 13));
                else ans.append((char) ('a' + (c + 12 - 'z')));
            } else if (Character.isUpperCase(c)) {
                if (c + 13 <= 'Z') ans.append((char) (c + 13));
                else ans.append((char) ('A' + (c + 12 - 'Z')));
            } else {
                ans.append(c);
            }
        }
        // 첫째 줄에 S를 ROT13으로 암호화한 내용을 출력
        System.out.println(ans);
    }

}