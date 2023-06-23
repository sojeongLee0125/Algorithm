import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 영선이의 친구들을 파티에 투입시켰을 때 욱제가 파티에 머무를 수 있는 최대 시간 구하기
 * <p>
 * - 초대장을 받은 모든 사람들이 자신이 언제 와서 언제 떠날 것인지 답변을 줬다.
 * - 욱제는 자신을 제외한 파티 인원이 T명 미만이 되는 순간 파티장에서 나가고,
 * 파티 인원이 T명 이상이 되는 순간 다시 돌아온다.
 * - 파티가 진행되는 동안 T명 이상의 인원이 계속 유지되지 못 한다.
 * - 그래서 영선이는 급히 자신의 친구들 K명을 부르려고 한다.
 * <p>
 * - 영선이의 친구들은 욱제 및 영선이의 친구들을 제외한 파티 인원이
 * T명 이상이 되는 순간 다 같이 파티장에서 나가 버리고 돌아오지 않는다.
 * - 파티 인원이 T명 이상이면 영선이의 친구들은 파티장에 들어가지 않는다.
 * - 단, 아직 들어오지 않은 영선이의 친구들은 이후 파티 인원이 T명 미만이 되면
 * 파티장으로 들어 갈 수 있다.
 * <p>
 * - 영선이는 친구들 각각을 적절한 시각에 투입시켜 최대한 오랫동안 욱제가 파티에 머물도록 하고 싶다.
 * - 꼭 K명을 모두 투입시킬 필요는 없다.
 * - 영선이는 욱제를 얼마나 오래 파티에 머물게 할 수 있을까?
 */
public class Main {
    static int N, M, K, T;
    static int[] timeCnt = new int[301];
    static int[][] dp = new int[301][301];
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 파티가 진행되는 시간 N (1 ≤ N ≤ 300),
        // 파티에 초대한 사람 수 M (1 ≤ M ≤ 300),
        // 영선이의 친구 수 K (1 ≤ K ≤ 300),
        // 욱제가 기대하는 최소의 파티 인원 T (1 ≤ T ≤ M)가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        // 둘째 줄부터 M개의 줄에 걸쳐 각 줄마다 ai, bi가 주어진다.
        // i번째 사람은 시각 ai에 파티에 참여하고, 시각 bi에 파티를 떠난다.
        // (1 ≤ ai ≤ N, ai < bi ≤ N + 1)
        // 파티가 시작되는 시각은 1을 기준으로 한다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int in = Integer.parseInt(st.nextToken());
            int out = Integer.parseInt(st.nextToken());
            for (int j = in; j < out; j++) timeCnt[j] = Math.min(T, timeCnt[j] + 1);
        }

        // 시작 시간 인원
        int tmp = timeCnt[1];
        int cnt = 1;

        for (int i = 2; i <= N; i++) {
            // 새로운 구간이 생길 경우 구간을 추가한다.
            if (tmp != timeCnt[i]) {
                list.add(new int[]{cnt, tmp}); // 시간, 투입해야할 인원
                cnt = 0;
                tmp = timeCnt[i];
            }
            cnt++;
        }

        list.add(new int[]{cnt, tmp});

        System.out.println(party(0, K, 0));
    }

    private static int party(int curIdx, int total, int prev) {
        // 채워야 할 친구를 다 채운 경우
        if (curIdx == list.size()) return 0;

        // 메모이제이션
        if (dp[curIdx][total] != 0) return dp[curIdx][total];

        int need = Math.max(0, T - list.get(curIdx)[1]);
        int insert = (prev >= need) ? 0 : need;

        // 투입할 수 있는 경우
        if (total - insert >= 0)
            return dp[curIdx][total] = Math.max(
                    // 투입하는 경우, 투입 안하는 경우 중 큰 값
                    party(curIdx + 1, total - insert, need) + list.get(curIdx)[0],
                    party(curIdx + 1, total, 0));
        else return dp[curIdx][total] = party(curIdx + 1, total, 0);
    }
}