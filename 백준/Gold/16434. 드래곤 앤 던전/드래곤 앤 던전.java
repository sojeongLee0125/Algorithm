import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 용사는 N번 방에 있는 용을 쓰러트리기 위한 최소의 HMaxHP를 여러분이 계산해주면 좋겠다고 합니다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 첫 번째 줄에 방의 개수 N (1 ≤ N  ≤ 123,456) 과 용사의 초기 공격력 HATK (1 ≤ HATK  ≤ 1,000,000) 가 주어집니다.
        long N = Integer.parseInt(st.nextToken());
        long initA = Integer.parseInt(st.nextToken());

        // i+1번째 줄엔 i번째 방의 정보를 나타내는 세개의 정수 ti, ai, hi (ti ∈ {1, 2}, 1 ≤ ai, hi  ≤ 1,000,000) 가 주어집니다.
        // 모든 몬스터의 생명력 + 1 이 용사가 가질 수 있는 최소의 생명력
        // 포션의 경우도 고려해주어야 한다. -> 포션은 다음 몬스터의 생명력을 깎는 방식으로 진행
        long cur = 0L;
        long p = 0L;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            long t = Long.parseLong(st.nextToken()); // 1 - 몬스터, 2 - 포션
            long a = Long.parseLong(st.nextToken()); // 공격력
            long h = Long.parseLong(st.nextToken()); // 생명력

            //몬스터일 경우
            // 몬스터 공격력 * 공격횟수만큼의 체력이 필요하다.
            if (t == 1L) {
                long times = (h / initA) + (h % initA == 0 ? 0 : 1);
                long attk = times - 1;
                long damage = (attk * a);

                // 포션보다 데미지가 클 경우
                if (p < damage) {
                    cur += damage - p;
                    p = 0;
                } else p -= damage; // 포션이 몬스터 총 데미지보다 큰 경우 cur에 추가하지 않고 포션에서 빼주기만 한다.
            } else {
                //포션일 경우 - 다음 몬스터의 공격력에서 해당만큼을 뺀다.
                initA += a;
                p = Math.min(cur, p + h);
            }
        }

        System.out.println(cur + 1); // 총 데미지보다 1 많아아 한다.
    }


}