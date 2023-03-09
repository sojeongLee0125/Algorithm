import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Q. 물웅덩이들의 위치와 크기에 대한 정보가 주어질 때, 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수를 구하여라.
 * - 흙으로 된 비밀길 위에 폭우가 내려서 N (1 <= N <= 10,000) 개의 물웅덩이가 생겼다.
 * - 물웅덩이를 덮을 수 있는 길이 L (L은 양의 정수) 짜리 널빤지들을 충분히 가지고 있어서, 이들로 다리를 만들어 물웅덩이들을 모두 덮으려고 한다.
 */
public class Main {

    static int N, L, idx, cnt, ans;
    static ArrayList<int[]> pair = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N과 L이 들어온다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 웅덩이의 갯수
        L = Integer.parseInt(st.nextToken()); // 널빤지 길이

        // 웅덩이들의 정보가 주어진다. 웅덩이의 정보는 웅덩이의 시작 위치와 끝 위치로 이루어진다. 각 위치는 0이상 1,000,000,000이하의 정수이다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pair.add(new int[]{s, e});
        }

        Collections.sort(pair, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < N; i++) {
            if (pair.get(i)[1] <= idx) continue;
            if (idx < pair.get(i)[0]) {
                cnt = ((pair.get(i)[1] - pair.get(i)[0]) / L) + ((pair.get(i)[1] - pair.get(i)[0]) % L == 0 ? 0 : 1);
                idx = pair.get(i)[0] + (cnt * L);
            } else {
                // 널빤지가 덮여진 안일 경우
                cnt = ((pair.get(i)[1] - idx) / L) + ((pair.get(i)[1] - idx) % L == 0 ? 0 : 1);
                idx = idx + (cnt * L);
            }

            ans += cnt;
        }

        System.out.println(ans);
    }
}