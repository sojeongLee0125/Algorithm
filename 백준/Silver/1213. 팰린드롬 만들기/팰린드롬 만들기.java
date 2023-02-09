import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 영어 이름을 팰린드롬으로 바꾸는 프로그램을 작성
 * - 영어 이름의 알파벳 순서를 적절히 바꿔서 팰린드롬을 만들려고 한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 영어 이름이 있다. 알파벳 대문자로만 된 최대 50글자
        String name = br.readLine();

        // 알파벳을 담을 배열
        int[] arr = new int[30];
        for (char c : name.toCharArray()) {
            arr[c - 'A']++;
        }

        // 알파벳 끝에서 부터 하나씩 붙여나간다.
        // 알파벳 갯수가 1개일 경우 가운데 붙인다.
        StringBuilder pre = new StringBuilder();
        StringBuilder suf = new StringBuilder();
        StringBuilder mid = new StringBuilder();

        for (int i = 26; i >= 0; i--) {
            if (arr[i] != 0) {
                while (arr[i] != 0) {
                    if (arr[i] == 1) {
                        mid.append((char) (i + 'A'));
                        arr[i]--;
                    } else {
                        pre.append((char) (i + 'A'));
                        suf.append((char) (i + 'A'));
                        arr[i] -= 2;
                    }
                }
            }
        }

        // 첫째 줄에 문제의 정답을 출력한다.
        // 만약 불가능할 때는 "I'm Sorry Hansoo"를 출력한다.
        // 정답이 여러 개일 경우에는 사전순으로 앞서는 것을 출력한다.
        if (mid.length() >= 2) System.out.println("I'm Sorry Hansoo");
        else {
            pre = pre.reverse();
            pre.append(mid);
            pre.append(suf);
            System.out.println(pre);
        }

    }

}