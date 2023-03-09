import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q. 주사위에서 나올 수 10개를 미리 알고 있을 때, 얻을 수 있는 점수의 최댓값을 구해보자.
 * - 처음에는 시작 칸에 말 4개가 있다.
 * - 이 파란색 칸에서 이동을 시작하면 파란색 화살표를 타야 하고, 이동하는 도중이거나 파란색이 아닌 칸에서 이동을 시작하면 빨간색 화살표를 타야 한다.
 * - 말이 도착 칸으로 이동하면 주사위에 나온 수와 관계 없이 이동을 마친다.
 * - 게임은 10개의 턴으로 이루어진다.  도착 칸에 있지 않은 말을 하나 골라 주사위에 나온 수만큼 이동시킨다.
 * - 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
 * - 말이 이동을 마칠 때마다 칸에 적혀있는 수가 점수에 추가된다.
 */
public class Main {

    static int[] dice = new int[10];
    static int[] mal = new int[4];
    static ArrayList<Integer>[] map = new ArrayList[55];
    static HashMap<Integer, Integer> score = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 주사위에서 나올 수 10개가 순서대로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        // 게임판 - ArrayList<Integer>[] 로 다음 말을 맵에 넣는다. 이 때 파란색 화살표를 뒤에 넣는다.
        //       - 점수는 HashMap<번호, 점수>로 저장한다.
        // 재귀함수 (cnt)로 진행하면서 cnt == 10 이 되면 return 0;
        // 재귀함수 기저 조건 외에는 for 1 - 4 4가지 말 중에 하나를 현재 주사위 점수만큼 이동시키고 재귀함수를 빠져나오면 다시 원복한다.

        // 맵 그리기
        makeMap();

        // 얻을 수 있는 점수의 최댓값을 출력한다.
        System.out.println(go(0));
    }

    private static int go(int cnt) {
        int sum = 0;
        if (cnt == 10) return 0;
        for (int i = 0; i < 4; i++) {
            // 도착한 말은 제외
            if (mal[i] == 50) continue;
            // 현재 선택된 말의 위치
            int curIdx = mal[i];
            // 다음에 갈 예상위치
            int nxtIdx = getNext(curIdx, dice[cnt]);
            // 가능여부 확인
            if (!isPossible(nxtIdx)) continue;
            // 이동
            mal[i] = nxtIdx;
            sum = Math.max(sum, go(cnt + 1) + score.get(nxtIdx));
            // 원상복구
            mal[i] = curIdx;
        }
        return sum;
    }

    private static boolean isPossible(int nxtIdx) {
        if (nxtIdx == 50) return true;
        for (int i = 0; i < 4; i++) {
            if (mal[i] == nxtIdx) return false;
        }
        return true;
    }

    private static int getNext(int curIdx, int dice) {

        if (map[curIdx].size() > 1) {
            curIdx = map[curIdx].get(1);
            dice--;
        }

        if (dice > 0) {
            Queue<Integer> q = new LinkedList<>();
            q.add(curIdx);
            int nxtIdx = 0;
            while (!q.isEmpty()) {
                nxtIdx = map[q.poll()].get(0);
                if (nxtIdx == 50) break;
                q.offer(nxtIdx);
                dice--;
                if (dice == 0) break;
            }
            return nxtIdx;
        } else return curIdx;
    }

    private static void makeMap() {
        for (int i = 0; i < 51; i++) {
            map[i] = new ArrayList<>();
        }

        // 직선 경로 저장
        for (int i = 0; i < 20; i++) {
            map[i].add(i + 1);
            score.put(i, i * 2);
        }

        // 도착점 저장
        map[20].add(50);
        score.put(20, 40);
        score.put(50, 0);

        // 파란선 경로 저장
        // 5번 노드
        map[5].add(21); // 10-13
        map[21].add(22); // 13-16
        map[22].add(23); // 16-19
        map[23].add(24); // 19-25

        // 10번 노드
        map[10].add(25); // 20-22
        map[25].add(26); // 22-24
        map[26].add(24); // 24-25

        // 15번 노드
        map[15].add(27); // 30-28
        map[27].add(28); // 28-27
        map[28].add(29); // 27-26
        map[29].add(24); // 26-25

        //중간 노드
        map[24].add(30); // 25-30
        map[30].add(31); // 30-35
        map[31].add(20); // 35-40

        score.put(21, 13);
        score.put(22, 16);
        score.put(23, 19);
        score.put(24, 25);

        score.put(25, 22);
        score.put(26, 24);

        score.put(27, 28);
        score.put(28, 27);
        score.put(29, 26);

        score.put(30, 30);
        score.put(31, 35);

    }

}