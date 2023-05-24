import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Q. 체스판의 크기와 말의 위치, 이동 방향이 모두 주어졌을 때, 게임이 종료되는 턴의 번호를 구해보자.
 * - 새로운 게임은 크기가 N×N인 체스판에서 진행되고, 사용하는 말의 개수는 K개 이다.
 * - 하나의 말 위에 다른 말을 올릴 수 있다. 체스판 각 칸은 흰색, 빨간색, 파란색 중 하나로 색칠.
 * - 게임은 체스판 위에 말 K개를 놓고 시작한다.
 * - 말은 1번부터 K번, 이동 방향은 위, 아래, 왼쪽, 오른쪽 4가지 중 하나이다.
 * - 턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다.
 * - 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동한다.
 * - 말의 이동 방향에 있는 칸에 따라서 말의 이동이 다르며 아래와 같다.
 * - 턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료된다.
 * <p>
 * - A번 말이 이동하려는 칸이 흰색인 경우에는 그 칸으로 이동한다.
 * - 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
 * <p>
 * - A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
 * - A,B,C -> D,E == D,E,A,B,C
 * <p>
 * - 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다
 * - A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
 * - A,D,F,G -> E,C,B ==  E, C, B, G, F, D, A
 * <p>
 * - 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
 * - 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
 * - 체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
 */
public class Main {
    static int N, K;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static ArrayList<Integer>[][] malBoard;
    static ArrayList<int[]> mal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 체스판의 크기 N, 말의 개수 K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        malBoard = new ArrayList[N][N];
        mal = new ArrayList<>();

        // 둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다.
        // 체스판의 정보는 정수로 이루어져 있고,
        // 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                malBoard[i][j] = new ArrayList<>();
            }
        }

        // 다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다.
        // 말의 정보는 세 개의 정수로 이루어져 있고, 순서대로 행, 열의 번호, 이동 방향이다.
        // 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고
        // 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            malBoard[y][x].add(i);
            mal.add(new int[]{y, x, dir});
        }

        // 게임이 종료되는 턴의 번호를 출력한다.
        // 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.
        int turn = 0;
        boolean flag = false;

        while (turn <= 1000) {
            turn++;
            if (game()) {
                flag = true;
                break;
            }
        }

        if (flag) System.out.println(turn);
        else System.out.println(-1);
    }

    // 첫번째 말부터 순서대로 게임을 진행한다.
    private static boolean game() {
        for (int i = 0; i < mal.size(); i++) {
            int y = mal.get(i)[0];
            int x = mal.get(i)[1];
            int dir = mal.get(i)[2];

            go(y, x, dir, i);

            if (isEnd()) return true;
        }
        return false;
    }

    private static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (malBoard[i][j].size() >= 4) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void go(int y, int x, int dir, int idx) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        // 이동이 불가능할 경우 방향 전환
        if (ny < 0 || nx < 0 || ny >= N || nx >= N || board[ny][nx] == 2) {
            int changeDir = mal.get(idx)[2] ^ 1;
            mal.get(idx)[2] = changeDir;

            ny = y + dy[changeDir];
            nx = x + dx[changeDir];

            // 방향 전환 후에도 불가능한 경우
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || board[ny][nx] == 2) return;
        }

        // 이동이 가능할 경우
        ArrayList<Integer> cur = malBoard[y][x]; // 현재 위치의 말들
        ArrayList<Integer> nxt = malBoard[ny][nx]; // 다음 위치의 말들

        // 현재 idx 말이 cur 속에 있는 위치
        int p = cur.indexOf(new Integer(idx));

        List<Integer> subListBeforeP = cur.subList(0, p);
        List<Integer> subListAfterP = cur.subList(p, cur.size());

        // cur 위치 갱신
        malBoard[y][x] = new ArrayList<>();
        for (int n : subListBeforeP) malBoard[y][x].add(n);

        // 뒤집어야 할 경우
        if (board[ny][nx] == 1) {
            Collections.reverse(subListAfterP);
        }

        // nxt 위치로 말 이동
        for (int num : subListAfterP) {
            nxt.add(num);
            mal.get(num)[0] = ny;
            mal.get(num)[1] = nx;
        }

    }
}