import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Q. 모든 소가 농장에 입장하려면 얼마나 걸리는 지 구해보자.
 * - 존의 농장에 들어가는 문은 하나밖에 없고, 그 문을 통과하려면 감시관의 길고 긴 검문을 받아야 한다.
 * - 여러 마리의 소가 한 번에 들어가려고 하면 줄이 그 만큼 길어진다.
 * - N마리의 소가 이 농장에 방문하러 왔다. 소가 도착한 시간과 검문받는 데 걸리는 시간은 소마다 다르다. (물론 같을 수도 있다.)
 * - 두 소가 동시에 검문을 받을 수는 없다.
 * - 한 소가 5초에 도착했고 7초 동안 검문을 받으면, 8초에 도착한 그 다음 소는 12초까지 줄을 서야 검문을 받을 수 있다.
 */
public class Main {

    static int N;

    static class Cow implements Comparable<Cow> {
        int in;
        int chk;

        public Cow(int in, int chk) {
            this.in = in;
            this.chk = chk;
        }

        // 들어온 순서대로 오름차순
        @Override
        public int compareTo(Cow o) {
            return this.in - o.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Cow> pq = new PriorityQueue<>();

        // 첫 줄에 100 이하의 양의 정수 N이 주어진다.
        int N = Integer.parseInt(br.readLine());

        // 다음 N줄에는 한 줄에 하나씩 소의 도착 시각과 검문 시간이 주어진다. 각각 1,000,000 이하의 양의 정수이다.
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int arrive = Integer.parseInt(st.nextToken());
            int chk = Integer.parseInt(st.nextToken());
            pq.add(new Cow(arrive, chk));
        }

        int time = 0;
        while (!pq.isEmpty()) {
            Cow cow = pq.poll();
            if (time <= cow.in) {
                time = cow.in;
            }
            time += cow.chk;
        }
        System.out.println(time);
    }

}