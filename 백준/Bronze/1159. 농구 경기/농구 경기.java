import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Q. 성의 첫 글자가 같은 선수 5명을 선발, 성의 첫 글자가 같은 선수가 5명보다 적다면, 친선 경기를 기권
 * - 뽑을 수 있는 성의 첫 글자를 모두 구한다.
 */
public class Main {
    static int N;
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 선수의 수 N (1 ≤ N ≤ 150)이 주어진다.
        N = Integer.parseInt(br.readLine());

        // 각 선수의 성이 주어진다. (성은 알파벳 소문자로만 이루어져 있고, 최대 30글자이다)
        while (N-- > 0) {
            String str = br.readLine();
            map.put(str.charAt(0), map.getOrDefault(str.charAt(0), 0) + 1);
        }

        // 다섯 명을 선발할 수 없는 경우에는 "PREDAJA" (따옴표 없이)를 출력한다.
        // 선발할 수 있는 경우에는 가능한 성의 첫 글자를 사전순으로 공백없이 모두 출력한다.

        StringBuilder ans = new StringBuilder();
        for (char c : map.keySet()) {
            if (map.get(c) >= 5) ans.append(c);
        }

        if (!ans.toString().equals("")) {
            System.out.println(ans);
        } else System.out.println("PREDAJA");
    }

}