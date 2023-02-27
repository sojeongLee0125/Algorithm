import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
 * - 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
 * -  단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
 * - 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
 */
public class Main {

    static int N, cnt, to;

    static class Meet implements Comparable<Meet> {
        int st;
        int ed;

        public Meet(int st, int ed) {
            this.st = st;
            this.ed = ed;
        }

        // 회의 끝나는 시간을 기준으로 오름차순
        @Override
        public int compareTo(Meet m) {
            if (this.ed == m.ed) return this.st - m.st;
            return this.ed - m.ed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Meet> meets = new ArrayList<>();

        // 첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다.
        N = Integer.parseInt(br.readLine());

        // 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
        // 시작 시간과 끝나는 시간은 2^31-1보다 작거나 같은 자연수 또는 0이다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            meets.add(new Meet(start, ed));
        }

        meets.sort(Meet::compareTo);
        to = meets.get(0).ed;
        cnt++;

        for (int i = 1; i < meets.size(); i++) {
            Meet m = meets.get(i);
            if (m.st >= to) {
                cnt++;
                to = m.ed;
            }
        }

        System.out.println(cnt);
    }

}