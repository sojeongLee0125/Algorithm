import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q. 정수 N에 대하여 val(X) = N을 만족하는 올바른 괄호 문자열 중
 * dmap(X) 값이 최소인 X를 찾아서 출력해야 한다.
 * 출력 문자열은 공백 없이 괄호 문자로만 구성
 * <p>
 * - 6개의 문자 ‘(’, ‘)’, ‘{’, ‘}’, ‘[’, ‘]’ 로 이루어진
 * 올바른 괄호 문자열 W에 대하여
 * 정수의 ‘괄호값’을 지정하는 함수 val(W)가 정의되어 있다.
 * <p>
 * - 한 쌍의 괄호로 구성된 길이 2인 문자열, ‘()’, ‘[]’, ‘{}’은 모두 올바른 괄호 문자열이다.
 * - X, Y가 올바른 괄호 문자열인 경우, 작업을 통하여 만든 새로운 문자열은 모두 올바른 괄호 문자열.
 * - 1) XY // 올바른 두 괄호 문자열의 접합(concatenation).
 * - 2) (X), {X}, [X] // 올바른 괄호 문자열 전체를 다시 괄호로 감쌈.
 * <p>
 * - 괄호값 val()의 계산 방법
 * - 3종류의 단위 괄호 (), {}, []의 괄호값은 각각 1, 2, 3으로 정의되어 있다.
 * - 문자열이 X=‘()’, Y=‘{}’, Z=‘[]’이면 val(X) = 1, val(Y) = 2, val(Z) = 3
 * <p>
 * - X, Y가 올바른 문자열이라고 할 때 순차적으로 연결한 문자열 Z=XY의 괄호값
 * - val(Z) = val(X) + val(Y)
 * <p>
 * - 올바른 문자열 X가 A=‘(X)’, B=‘{X}’, C=‘[X]’와 같이
 * - (), {}, []로 둘러싸여 있을 경우 A, B, C의 괄호값
 * - val(A) = 2·val(X), val(B) = 3·val(X), val(C) = 5·val(X)
 * <p>
 * - 괄호값이 k인 문자열은 유일하지 않다.
 * - ‘[]’, ‘{}()’, ‘()()()’ 모두는 괄호값이 3
 * <p>
 * - 올바른 괄호 문자열 X를 숫자로도 표현할 수 있다.
 * - 각 괄호 문자에 대하여 1에서 6까지의 숫자로 대치해 십진수로 읽은 값.
 * - 괄호 문자열 X를 위에 설명한 방식으로 변환시킨 값을 dmap(X)로 표시한다.
 */
public class Main {
    static int T;
    static Map<Character, Character> map = new HashMap<>();
    static String[] dp = new String[1005]; // 각 N의 dmap 최솟값
    static char[] arr = {'(', ')', '{', '}', '[', ']'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 각 괄호를 숫자로 치환
        for (int i = 0; i < arr.length; i++) map.put((char) (i + '1'), arr[i]);

        Arrays.fill(dp, "");
        // 각 val 1, 2, 3 최솟값 초기화
        dp[1] = "12";
        dp[2] = "34";
        dp[3] = "56";

        for (int idx = 1; idx <= 1000; idx++) {
            // 감싸는 경우가 더 작은 경우 교체
            if (idx % 2 == 0 && isShort(dp[idx], '1' + dp[idx / 2] + '2')) {
                dp[idx] = '1' + dp[idx / 2] + '2';
            }
            // 감싸는 경우가 더 작은 경우 교체
            if (idx % 3 == 0 && isShort(dp[idx], '3' + dp[idx / 3] + '4')) {
                dp[idx] = '3' + dp[idx / 3] + '4';
            }
            // 감싸는 경우가 더 작은 경우 교체
            if (idx % 5 == 0 && isShort(dp[idx], '5' + dp[idx / 5] + '6')) {
                dp[idx] = '5' + dp[idx / 5] + '6';
            }

            for (int i = 1; i <= idx; i++) {
                if (isShort(dp[idx], dp[i] + dp[idx - i])) dp[idx] = dp[i] + dp[idx - i];
            }
        }

        // 첫 번째 줄에 테스트케이스의 개수 T가 주어진다. (1 ≤ T ≤ 100)
        T = Integer.parseInt(br.readLine());

        // 두 번째 줄부터 T+1번째 줄까지 한 줄에 하나씩 정수 N이 주어진다. (5 ≤ N ≤ 1,000)
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            for (char c : dp[N].toCharArray()) System.out.print(map.get(c));
            System.out.println();
        }

    }

    private static boolean isShort(String prev, String after) {
        if ("".equals(prev) && "".equals(after)) return false;
        else if ("".equals(prev)) return true;
        else if (prev.length() == after.length()) return prev.compareTo(after) > 0;
        else return after.length() < prev.length();
    }
}