import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 트럭 총 세 대, 트럭을 주차하는데 비용이 얼마나 필요
 * - 트럭을 한 대 주차할 때는 1분에 한 대당 A원, 두 대를 주차할 때는 1분에 한 대당 B원, 세 대를 주차할 때는 1분에 한 대당 C원
 * - 트럭이 주차장에 주차된 시간이 주어졌을 때, 주차 요금으로 얼마를 내야 하는지 구하는 프로그램을 작성
 */
public class Main {
    static int A, B, C;
    static int[] time = new int[105];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 주차 요금 A, B, C (1 ≤ C ≤ B ≤ A ≤ 100)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 상근이가 가지고 있는 트럭이 주차장에 도착한 시간과 주차장에서 떠난 시간
        // 도착한 시간은 항상 떠난 시간보다 앞선다. 입력으로 주어지는 시간은 1과 100사이
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int stt = Integer.parseInt(st.nextToken());
            int dpt = Integer.parseInt(st.nextToken());
            for (int t = stt; t < dpt; t++) {
                time[t]++;
            }
        }

        int sum = 0;
        for (int i = 0; i < time.length; i++) {
            if (time[i] == 1) sum += (A);
            else if (time[i] == 2) sum += (2 * B);
            else if (time[i] == 3) sum += (3 * C);
        }

        System.out.println(sum);
    }
}