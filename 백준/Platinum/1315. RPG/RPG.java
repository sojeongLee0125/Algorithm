import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 준규가 깰 수 있는 퀘스트 개수의 최댓값을 구하는 프로그램을 작성하시오.
 * - 이 게임에서 캐릭터는 2가지 스탯을 가지고 있다.
 * - 하나는 힘(STR)이고, 다른 하나는 지력(INT)이다.
 * - 캐릭터를 생성했을 때, 두 스탯은 모두 1이다.
 * <p>
 * - 게임에는 총 N개의 퀘스트가 있다.
 * - i번째 퀘스트를 깨려면 캐릭터의 힘이 STR[i]보다 크거나 같거나,
 * - 지력이 INT[i]보다 크거나 같아야 한다.
 * <p>
 * - 이 퀘스트를 깨면, 스탯을 올릴 수 있는 포인트를 PNT[i]개 만큼 얻게 된다.
 * - 모든 퀘스트는 단 한 번만 깰 수 있으며, 퀘스트를 깨는 순서는 준규가 마음대로 정할 수 있다.
 * - 퀘스트 보상으로 얻게되는 포인트로 준규 마음대로 스탯을 올릴 수 있다.
 */
public class Main {

    static int N;
    static int[] chk = new int[1001];
    static int[][] dp = new int[1001][1001];
    static ArrayList<int[]> rpg = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 퀘스트의 개수 N이 주어진다.
        // N은 50보다 작거나 같은 자연수이다.
        N = Integer.parseInt(br.readLine());

        // 둘째 줄부터 N개의 줄에 STR[i], INT[i], PNT[i]가 주어진다.
        // 이 숫자는 모두 1,000보다 작거나 같은 자연수이다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int STR = Integer.parseInt(st.nextToken());
            int INT = Integer.parseInt(st.nextToken());
            int PNT = Integer.parseInt(st.nextToken());
            rpg.add(new int[]{STR, INT, PNT});
        }

        // dp 배열 초기화
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);

        // 첫째 줄에 준규가 깰 수 있는 퀘스트 개수의 최댓값을 출력한다.
        System.out.println(go(1, 1));
    }

    private static int go(int STR, int INT) {

        if (dp[STR][INT] != -1) return dp[STR][INT];
        dp[STR][INT] = 0;

        int ans = 0;
        int PNT = 0;
        ArrayList<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            // 게임을 깰 수 있는 경우
            if (rpg.get(i)[0] <= STR || rpg.get(i)[1] <= INT) {
                ans++;

                // 이미 진행한 게임일 경우
                if (chk[i] == 1) continue;
                chk[i] = 1;
                PNT += rpg.get(i)[2];
                tmp.add(i);
            }
        }

        for (int i = 0; i <= PNT; i++) {
            int nxtSTR = Math.min(1000, STR + i);
            int nxtINT = Math.min(1000, INT + (PNT - i));
            ans = Math.max(ans, go(nxtSTR, nxtINT));
        }

        // 방문 체크 해제
        for (Integer integer : tmp) {
            chk[integer] = 0;
        }

        return dp[STR][INT] = ans;
    }
}