import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 생성기를 테스트해보고 생성되는 패스워드의 품질을 평가하여야 한다.
 * - 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
 * - 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
 * - 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 테스트할 패스워드가 주어진다.
        // 마지막 테스트 케이스는 end이며, 패스워드는 한글자 이상 20글자 이하의 문자열이다. 패스워드는 대문자를 포함하지 않는다.

        String input;
        while (!(input = br.readLine()).equals("end")) {
            boolean t1 = false;
            boolean t2 = true;
            boolean t3 = true;

            int cnt1 = 0; // 모음의 갯수
            int cnt2 = 0; // 자음의 갯수

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    t1 = true;
                    cnt1++;
                    cnt2 = 0;
                } else {
                    cnt1 = 0;
                    cnt2++;
                }
                if (cnt1 >= 3 || cnt2 >= 3) t2 = false;
                if (i != 0 && c == input.charAt(i - 1)) {
                    if (c != 'e' && c != 'o') t3 = false;
                }
            }

            if (t1 && t2 && t3) {
                System.out.printf("<%s> is acceptable. \n", input);
            } else {
                System.out.printf("<%s> is not acceptable. \n", input);
            }
        }
    }

}