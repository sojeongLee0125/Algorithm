import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q. 동호가 받을 수 있는 최대 컵라면 수를 구하는 것이다.
 * - 동호에게 N개의 문제를 주고서, 각각의 문제를 풀었을 때 컵라면을 몇 개 줄 것인지 제시 하였다.
 * - 문제를 푸는데는 단위 시간 1이 걸리며, 각 문제의 데드라인은 N이하의 자연수이다.
 * - 각 문제를 풀 때 받을 수 있는 컵라면 수와 최대로 받을 수 있는 컵라면 수는 모두 231보다 작거나 같은 자연수이다.
 */
public class Main {
    static PriorityQueue<Problem> pq = new PriorityQueue<>();
    static ArrayList<Problem> list = new ArrayList<>();
    static int N, max;

    static class Problem implements Comparable<Problem> {
        int num;
        int dead;
        int cup;

        public Problem(int num, int dead, int cup) {
            this.num = num;
            this.dead = dead;
            this.cup = cup;
        }

        // 컵라면 순 오름차순 정렬
        @Override
        public int compareTo(Problem p) {
            return this.cup - p.cup;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에 숙제의 개수 N (1 ≤ N ≤ 200,000)이 들어온다.
        N = Integer.parseInt(br.readLine());

        //  다음 줄부터 N+1번째 줄까지 i+1번째 줄에 i번째 문제에 대한 데드라인과 풀면 받을 수 있는 컵라면 수가 공백으로 구분되어 입력된다.
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Problem(i, d, c));
        }

        // 날짜순 오름차순 정렬
        list.sort(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                return o1.dead - o2.dead;
            }
        });

        list.forEach(p -> {
            pq.add(p);
            if (pq.size() > p.dead) {
                pq.poll();
            }
        });

        while (!pq.isEmpty()) {
            max += pq.poll().cup;
        }

        System.out.println(max);

    }

}