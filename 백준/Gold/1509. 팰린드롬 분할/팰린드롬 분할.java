import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Q. 분할의 개수의 최솟값을 출력하는 프로그램을 작성하시오.
 * - 세준이는 어떤 문자열을 팰린드롬으로 분할하려고 한다.
 * - 예를 들어, ABACABA를 팰린드롬으로 분할하면,
 * - {A, B, A, C, A, B, A}, {A, BACAB, A}, {ABA, C, ABA}, {ABACABA}등이 있다.
 */
public class Main {
    static String str;
    static int len;
    static int[][] dp; // [인덱스],[연속된 갯수]
    static int[] chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 문자열이 주어진다.
        // 이 문자열은 알파벳 대문자로만 이루어져 있고, 최대 길이는 2,500이다.
        str = br.readLine();
        len = str.length();

        dp = new int[len + 5][len + 5];
        chk = new int[len + 5];

        // 1. 자기 자신만 있는 경우 팰린드롬이다.
        for (int i = 0; i < len; i++) dp[i][1] = 1;

        // 2. 바로 앞과 연속된 경우 팰린드롬이다.
        for (int i = 0; i < len - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) dp[i][2] = 1;
        }

        // 3. 3개 ~ len개 연속까지 팰린드롬을 검사한다.
        for (int size = 3; size <= len; size++) {
            for (int j = 0; j + size <= len; j++) {
                if (str.charAt(j) == str.charAt(j + size - 1) && dp[j + 1][size - 2] == 1) dp[j][size] = 1;
            }
        }

        // 4. 체크 배열의 초기화 - 무한대값
        Arrays.fill(chk, Integer.MAX_VALUE);

        // 첫째 줄에 팰린드롬 분할의 개수의 최솟값을 출력한다.
        System.out.println(find(0));
    }

    private static int find(int cur) {
        // 문자열의 끝에 도달한 경우
        if (cur == len) return 0;

        // 메모이제이션
        if (chk[cur] != Integer.MAX_VALUE) return chk[cur];

        // 길이 2부터 len 까지 검사
        for (int i = 1; cur + i <= len; i++) {
            if (dp[cur][i] == 1) chk[cur] = Math.min(chk[cur], find(cur + i) + 1);
        }

        return chk[cur];
    }
}