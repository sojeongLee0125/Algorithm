import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Q. 입력으로 식재료 표가 주어졌을 때, 최저 영양소 기준을 만족하는 최소 비용의 식재료 집합을 찾아야 한다.
 * - 식재료 N개 중에서 몇 개를 선택해서 이들의 영양분(단백질, 탄수화물, 지방, 비타민)이 일정 이상이 되어야 한다.
 * - 조건을 만족시키면서도 비용이 최소가 되는 선택을 하려고 한다.
 * -
 */
public class Main {

    static int N, mp, mf, ms, mv, min = Integer.MAX_VALUE;
    static ArrayList<food> foods = new ArrayList<>();
    static ArrayList<Integer> comb = new ArrayList<>();
    static HashMap<Integer, ArrayList<String>> ans = new HashMap<>();

    static class food {
        int p;
        int f;
        int s;
        int v;
        int c;

        public food(int p, int f, int s, int v, int c) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에 식재료의 개수 N이 주어진다. (3 <= N <= 15)
        N = Integer.parseInt(br.readLine());

        // 단백질, 지방, 탄수화물, 비타민의 최소 영양성분을 나타내는 정수 mp,mf,ms,mv가 주어진다. 각각 500 이하
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        // 이어지는 각 줄에는 i번째 식재료의 단백질, 지방, 탄수화물, 비타민과 가격이 5개의 정수가 주어진다. 식재료의 번호는 1부터 시작한다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            foods.add(new food(p, f, s, v, c));
        }

        // 모든 재료 조합을 합쳐보며 조건에 맞는 경우, 해당 비용에 재료 목록 숫자를 추가한다.
        for (int i = 1; i < (1 << N); i++) {
            // 해당 조합이 조건을 만족할 경우
            check(i);
        }

        // 조건을 만족하는 답이 없다면 -1을 출력하고, 둘째 줄에 아무것도 출력하지 않는다.
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.out.println();
        } else {
            // 첫 번째 줄에 최소 비용을 출력하고, 두 번째 줄에 조건을 만족하는 최소 비용 식재료의 번호를 공백으로 구분해 오름차순으로 한 줄에 출력한다.
            System.out.println(min);
            ArrayList<String> list = ans.get(min);

            // 같은 비용의 집합이 하나 이상이면 사전 순으로 가장 빠른 것을 출력한다.
            list.sort(String::compareTo);
            System.out.println(list.get(0));
        }
    }

    private static void check(int num) {
        int p = 0;
        int f = 0;
        int s = 0;
        int v = 0;
        int c = 0;
        ArrayList<Integer> comb = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if ((num & (1 << i)) != 0) {
                comb.add(i + 1);
                p += foods.get(i).p;
                f += foods.get(i).f;
                s += foods.get(i).s;
                v += foods.get(i).v;
                c += foods.get(i).c;
            }
        }

        if (p >= mp && f >= mf && s >= ms && v >= mv) {
            if (c <= min) {
                min = c;
                // 해당 비용을 가진 조합을 저장한다.
                if (!ans.containsKey(c)) {
                    ans.put(c, new ArrayList<>());
                }
                StringBuilder sb = new StringBuilder();
                comb.forEach(i -> sb.append(i).append(" "));
                ans.get(c).add(sb.toString());
            }
        }
    }

}