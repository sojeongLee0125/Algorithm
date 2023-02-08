import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Q. 한번 입었던 옷들의 조합을 절대 다시 입지 않는다.
 * -  가진 의상들이 주어졌을때 조합의 수 구하기.
 */
public class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //  테스트 케이스는 최대 100
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            HashMap<String, Integer> map = new HashMap<>();
            int cnt = 1;

            // 테스트 케이스의 첫째 줄에는 해빈이가 가진 의상의 수 n(0 ≤ n ≤ 30)
            int n = Integer.parseInt(br.readLine());

            // 의상이 0개일 경우 처리
            if (n == 0) {
                System.out.println(0);
                continue;
            }

            // 다음 n개 가진 의상의 이름과 의상의 종류가 공백으로 구분되어 주어진다.
            // 같은 종류의 의상은 하나만 입을 수 있다.
            // 모든 문자열은 1이상 20이하의 알파벳 소문자로 이루어져있으며 같은 이름을 가진 의상은 존재하지 않는다.
            while (n-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                st.nextToken();

                // 의상의 종류를 key로 잡아서 총 수를 구한다.
                String key = st.nextToken();
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            // (n+1) * (m+1) - 1 = 조합의 수
            for (String s : map.keySet()) {
                cnt *= (map.get(s) + 1);
            }

            System.out.println(cnt - 1);
        }

    }
}