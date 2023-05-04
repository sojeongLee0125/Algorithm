import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Q. 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수 구하기
 * - 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다.
 * - 한 소수는 반드시 한 번만 덧셈에 사용될 수 있다.
 */
public class Main {
    static int N, rs, lt, rt;
    static int[] chk = new int[4000005];
    static ArrayList<Integer> prime = new ArrayList<>(); // 소수 리스트 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)
        N = Integer.parseInt(br.readLine());

        // 2에서 N까지의 소수들을 구한다.
        for (int i = 2; i <= N; i++) {
            
            // 소수가 아닐경우
            if (chk[i] == 1) continue;
            
            // 소수일 경우 관련된 2의 배수들 전부 제거
            for (int j = i + i; j <= N; j += i) {
                chk[j] = 1;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (chk[i] == 0) prime.add(i);
        }

        // 투포인터로 소수의 합이 되는 경우의 수를 구한다.
        // lt == 0, rt == 0 시작
        
        int sum = 0;
        
        while (true) {
            if (sum >= N) {
                sum -= prime.get(lt++);
            } else if (rt == prime.size()) break;
            else {
                sum += prime.get(rt++);
            }
            
            if (sum == N) {
                rs++;
            }
        }

        // 첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.
        System.out.println(rs);
    }
}