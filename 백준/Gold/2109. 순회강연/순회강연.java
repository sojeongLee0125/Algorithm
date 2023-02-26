import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q. 가장 많은 돈을 벌 수 있도록 순회강연을 하려 한다. 강연의 특성상, 이 학자는 하루에 최대 한 곳에서만 강연을 할 수 있다.
 * - n(0 ≤ n ≤ 10,000)개의 대학에서 강연 요청을 해 왔다.
 * - 각 대학에서는 d(1 ≤ d ≤ 10,000)일 안에 와서 강연을 해 주면 p(1 ≤ p ≤ 10,000)만큼의 강연료를 지불하겠다고 알려왔다.
 */
public class Main {

    static ArrayList<Lecture> list = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int n, max;

    static class Lecture implements Comparable<Lecture> {
        int pay;
        int day;

        public Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(Lecture o) {
            if (o.day == this.day) return this.pay - o.pay;
            else return this.day - o.day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 정수 n이 주어진다. 다음 n개의 줄에는 각 대학에서 제시한 p값과 d값이 주어진다.
        n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 페이
            int d = Integer.parseInt(st.nextToken()); // 마감일
            list.add(new Lecture(p, d));
        }

        // 날짜순 오름차순, 강의료순 오름차순으로 정렬한다.
        list.sort(Lecture::compareTo);
        
        list.forEach(l -> {
            pq.offer(l.pay);
            if (pq.size() > l.day) {
                pq.poll();
            }
        });

        while (!pq.isEmpty()) {
            max += pq.poll();
        }

        //첫째 줄에 최대로 벌 수 있는 돈을 출력한다.
        System.out.println(max);
    }

}