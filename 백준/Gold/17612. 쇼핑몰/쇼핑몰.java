import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q.줄을 서 있는 고객 N명의 정보(회원번호, 구매한 물건의 수)를 알고 있을 때,
 * 이들이 계산을 하고 쇼핑몰을 빠져나오는 순서를 구해야 한다.
 * <p>
 * - N명의 고객들이 계산을 하고 쇼핑몰을 떠나고자 계산대 앞에 줄을 서 있다.
 * - 각 고객은 커다란 짐수레(cart)에 물건을 담아 계산대로 간다.
 * - 쇼핑몰에는 아래 그림과 같이 K개의 계산대가 병렬로 배치되어 있다.
 * - 쇼핑몰 안내원들은 계산대에 온 사람들을 가장 빨리 계산을 마칠 수 있는 계산대로 안내를 한다.
 * - 안내원은 각 계산대에서 기다리고 있는 사람들이 계산을 하는데 얼마나 걸리는지 이미 알고 있다.
 * <p>
 * - 안내원이 고객을 계산대로 안내할 때 두 계산대에서 기다려야 할 시간이 같을 경우에는
 * - 가장 번호가 작은 계산대로 안내를 한다.
 * <p>
 * - 계산을 마친 고객은 출구를 통하여 쇼핑몰을 완전히 빠져 나간다.
 * - 계산대에서 계산을 마친 고객의 시간이 동일하다면
 * - 출구에 가까운 높은 번호 계산대의 고객부터 먼저 빠져나간다.
 * <p>
 * - 물건을 계산하는 데에는 종류에 관계없이 동일하게 1분이 소요된다.
 * - 즉, 물건이 w개 있는 손님이 계산을 마치는 데에는 정확히 w분이 소요된다.
 * - 계산대로 들어가고 계산대에서 나오는데 걸리는 시간은 없다고 가정한다.
 */
public class Main {
    static int N, K;
    static class Customer implements Comparable<Customer> {
        int id;
        int time;
        int cashierId;

        public Customer(int id, int time, int cashierId) {
            this.id = id;
            this.time = time;
            this.cashierId = cashierId;
        }

        @Override
        public int compareTo(Customer o) {
            if (this.time == o.time) return o.cashierId - this.cashierId;
            else return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 첫 줄에는 2개의 정수 N(1 ≤ N ≤ 100,000)과 k(1 ≤ k ≤ 100,000)가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Customer> customerQ = new LinkedList<>();

        // 다음 줄부터 N개의 줄에 걸쳐 고객 N명의 정보가
        // 줄 맨 앞의 고객부터 맨 뒤 고객까지 순서대로 주어진다.
        // i번째 줄은 i번째 고객의 회원번호 idi(1 ≤ idi ≤ 1,000,000)와
        // 그가 구입한 물건의 수 wi(1 ≤ wi ≤ 20)로 이루어져 있다.
        // N명의 회원번호는 모두 다르다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            customerQ.offer(new Customer(id, w, 0));
        }

        PriorityQueue<Customer> customerPQ = new PriorityQueue<>();

        for (int i = 0; i < K; i++) {
            if (customerQ.isEmpty()) break;
            Customer customer = customerQ.poll();
            customer.cashierId = i;
            customerPQ.offer(customer);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        Stack<Integer> out = new Stack<>();

        while (!customerQ.isEmpty()) {
            int curTime = customerPQ.peek().time;

            while (!customerPQ.isEmpty() && customerPQ.peek().time == curTime) {
                Customer cur = customerPQ.poll();
                out.add(cur.cashierId);
                answer.add(cur.id);
            }

            while (!customerQ.isEmpty() && customerPQ.size() != K) {
                Customer customer = customerQ.poll();
                customer.time += curTime;
                customer.cashierId = out.pop();
                customerPQ.add(customer);
            }
        }

        while (!customerPQ.isEmpty()) {
            answer.add(customerPQ.poll().id);
        }

        // 고객 N명의 회원번호를 쇼핑몰을 빠져나가는 순서대로 r1, r2, ..., rN이라 할 때,
        // 1×r1 + 2×r2 + ... + N×rN의 값을 출력한다.
        // 출력값이 int 범위를 넘어갈 수 있음에 유의하라.
        long res = 0L;
        for (int i = 0; i < answer.size(); i++) {
            res += (long) (i + 1) * answer.get(i);
        }
        System.out.println(res);
    }
}