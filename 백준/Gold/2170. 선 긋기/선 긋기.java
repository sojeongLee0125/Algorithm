import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Q.
 * - 매우 큰 도화지에 자를 대고 선을 그으려고 한다.
 * - 선을 그을 때에는 이미 선이 있는 위치에 겹쳐서 그릴 수도 있는데, 여러 번 그은 곳과 한 번 그은 곳의 차이를 구별할 수 없다고 하자.
 * - 선을 그었을 때, 그려진 선(들)의 총 길이를 구하는 프로그램을 작성하시오. 선이 여러 번 그려진 곳은 한 번씩만 계산한다.
 */
public class Main {

    static int N;
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 선을 그은 횟수 N (1 ≤ N ≤ 1,000,000)이 주어진다.
        N = Integer.parseInt(br.readLine());

        // 다음 N개의 줄에는 선을 그을 때 선택한 두 점의 위치 x, y (-1,000,000,000 ≤ x < y ≤ 1,000,000,000)가 주어진다.
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new int[]{x, y});
        }

        Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);

        int st = list.get(0)[0];
        int lt = list.get(0)[1];
        int len = 0;

        for (int i = 1; i < list.size(); i++) {
            int[] cur = list.get(i);
            int s = cur[0];
            int e = cur[1];
            // s 위치가 라스트위치보다 클 경우 -> 기존 lt - st 를 정답에 더하고 st/lt를 갱신한다.
            if (lt < s) {
                len += (lt - st);
                st = s;
                lt = e;
            } else if (s <= lt && e > lt) {
                // s 위치가 라스트위치보다 작거나 같으면서 e위치가 lt보다 클 경우 -> lt를 갱신한다.
                lt = e;
            }

        }

        // 마지막 처리
        len += (lt - st);

        System.out.println(len);
    }

}