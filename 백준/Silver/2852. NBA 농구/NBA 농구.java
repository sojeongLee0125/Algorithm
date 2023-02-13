import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 농구 경기는 정확히 48분동안 진행된다. 각 팀이 몇 분동안 이기고 있었는지 출력하는 프로그램을 작성
 */
public class Main {
    static int N, t1, t2, s1, s2, last;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 골이 들어간 횟수 N(1<=N<=100)이 주어진다.
        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int team = Integer.parseInt(st.nextToken());

            String[] str = st.nextToken().split(":");
            int time = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);

            if (s1 > s2) t1 += (time - last);
            else if (s2 > s1) t2 += (time - last);

            // 점수 갱신
            updateScore(team);
            // 시간 갱신
            last = time;
        }

        // 마지막 처리
        if (s1 > s2) t1 += (48 * 60 - last);
        else if (s2 > s1) t2 += (48 * 60 - last);

        printTime(t1);
        printTime(t2);
    }

    private static void updateScore(int team) {
        if (team == 1) s1++;
        else if (team == 2) s2++;
    }

    private static void printTime(int time) {
        System.out.printf("%s:%s \n",
                (time / 60) >= 10 ? (time / 60) : "0" + (time / 60),
                (time % 60) >= 10 ? (time % 60) : "0" + (time % 60));
    }

}