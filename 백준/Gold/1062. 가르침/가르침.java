import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램을 작성하시오.
 * - 김지민은 K개의 글자를 가르칠 시간 밖에 없다.
 * - 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다.
 * - 남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다. 남극언어에 단어는 N개 밖에 없다고 가정한다.
 */
public class Main {

    static int N, K;
    static int[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 단어의 개수 N과 K가 주어진다. N은 50보다 작거나 같은 자연수이고, K는 26보다 작거나 같은 자연수 또는 0이다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new int[N];

        // 둘째 줄부터 N개의 줄에 남극 언어의 단어가 주어진다.
        // 단어는 영어 소문자로만 이루어져 있고, 길이가 8보다 크거나 같고, 15보다 작거나 같다. 모든 단어는 중복되지 않는다.
        for (int i = 0; i < N; i++) {
            String w = br.readLine();
            for (char c : w.toCharArray()) {
                words[i] |= (1 << (c - 'a'));
            }
        }

        // 필수 단어 : a c i n t
        // K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값을 출력한다.
        if (K < 5) {
            System.out.println(0);
        } else {
            System.out.println(dfs(0, K, 0));
        }
    }

    private static int dfs(int idx, int k, int bit) {
        if (k < 0) return 0;
        if (idx == 26) {
            // 알파벳 끝까지 도달한 경우 계산
            return count(bit);
        }

        int rs = dfs(idx + 1, k - 1, bit | (1 << idx));

        // 필수 값이 아닌 경우 포함하지 않는 경우도 계산한다.
        if (idx != 'a' - 'a' && idx != 'c' - 'a' && idx != 'i' - 'a' && idx != 'n' - 'a' && idx != 't' - 'a') {
            rs = Math.max(rs, dfs(idx + 1, k, bit));
        }

        return rs;
    }

    private static int count(int bit) {
        int cnt = 0;
        for (int w : words) {
            if ((w & bit) == w) cnt++;
        }
        return cnt;
    }

}