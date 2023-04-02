import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Q. 두 집합의 대칭 차집합의 원소의 개수를 출력하는 프로그램을 작성하시오.
 * - 자연수를 원소로 갖는 공집합이 아닌 두 집합 A와 B가 있다.
 * - 두 집합 A와 B가 있을 때, (A-B)와 (B-A)의 합집합을 A와 B의 대칭 차집합이라고 한다.
 */
public class Main {

    static int a, b;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 집합 A의 원소의 개수와 집합 B의 원소의 개수가 빈 칸을 사이에 두고 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < a; i++) {
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < b; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (set.contains(num)) set.remove(num);
            else set.add(num);
        }

        System.out.println(set.size());
    }

}