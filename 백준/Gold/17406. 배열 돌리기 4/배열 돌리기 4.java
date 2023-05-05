import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 배열 A와 사용 가능한 회전 연산이 주어졌을 때, 배열 A의 값의 최솟값을 구해보자. 
 *    회전 연산은 모두 한 번씩 사용해야 하며, 순서는 임의로 정해도 된다.
 * - 크기가 N×M 크기인 배열 A가 있을때, 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값
 * - 배열은 회전 연산을 수행할 수 있다. 회전 연산은 세 정수 (r, c, s)로 이루어져 있고,
 * - 가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을 
 * - 시계 방향으로 한 칸씩 돌린다는 의미이다
 * - 회전 연산이 두 개 이상이면, 연산을 수행한 순서에 따라 최종 배열이 다르다.
 */
public class Main {
    static int N, M, K, min = Integer.MAX_VALUE;
    
    static int[][] arr; // 원본 배열
    static int[][] tmp; // 임시 배열
    static ArrayList<int[]> rotate = new ArrayList<>(); // 회전 연산 저장
    
    static int[] order; // 회전 연산 순서 저장
    static int[] chk; // 회전 연산 순열 중복 체크
    
    static int[] dy = {0, 1, 0, -1}; // 오 아 왼 위 (시계방향)
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 배열 A의 크기 N, M, 회전 연산의 개수 K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        tmp = new int[N][M];
        
        chk = new int[K];
        order = new int[K];

        // 둘째 줄부터 N개의 줄에 배열 A에 들어있는 수 A[i][j]가 주어지고,
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 다음 K개의 줄에 회전 연산의 정보 r, c, s가 주어진다.
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            rotate.add(new int[]{r, c, s});
        }

        // 1. K번의 완전탐색을 통해 회전연산을 수행. 
        //    CNT == K -> 각 행의 합의 최소값을 갱신한다.
        // 2. 회전 연산 r-s,c-s ~ r+s,c+s 회전연산을 수행하면서 제자리로 돌아오면 
        //    r-s + 1, c-s + 1을 원점으로 다시 시작한다.
        // 3. 만약 원점이 /2 보다 작거나 같을때까지만 수행한다.
        rot(0);
        
        System.out.println(min);
    }
	
    // 회전연산 완전 탐색 실시
    private static void rot(int cnt) {
        if (cnt == K) {
            // 각 행마다의 합을 구하고 최소값을 갱신한다.
            calc();
            return;
        }

        // 회전 연산 순열 실시
        for (int i = 0; i < K; i++) {
            if (chk[i] == 0) {
                order[cnt] = i;
                chk[i] = 1;
                rot(cnt + 1);
                chk[i] = 0;
            }
        }
    }

    private static void calc() {
        // 배열 복제
        for (int i = 0; i < N; i++) {
            System.arraycopy(arr[i], 0, tmp[i], 0, arr[i].length);
        }
        
        // k번의 회전 실시
        for (int i = 0; i < K; i++) {
            change(rotate.get(order[i]));
        }
        
        getMin();
    }

    private static void change(int[] rot) {
        int r = rot[0];
        int c = rot[1];
        int s = rot[2];

        for (int i = 0; i < s; i++) {
            int cy = r - s + i; // 시작점
            int cx = c - s + i;
            int d = 0;
            
            int pre = tmp[cy][cx];
            
            while (d < 4) {
                cy += dy[d];
                cx += dx[d];

                // 이전 좌표와 다음 좌표의 값 교환
                int nxt = tmp[cy][cx]; 
                tmp[cy][cx] = pre;
                pre = nxt;
                
                // 다음 좌표 유효성 체크
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < r - s + i || nx < c - s + i || 
                ny > r + s - i || nx > c + s - i) d++;
            }
        }
    }

    private static void getMin() {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += tmp[i][j];
            }
            min = Math.min(min, sum);
        }
    }
}