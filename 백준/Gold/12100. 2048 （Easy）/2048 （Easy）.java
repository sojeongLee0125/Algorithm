import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 보드의 크기가 N×N 이다. 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하기.
 * - 2048 게임은 4×4 크기의 보드에서 혼자 즐기는 재미있는 게임이다.
 * - 한 번의 이동은 보드 위에 있는 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것.
 * - 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다. 
 * - 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.
 * - 똑같은 수가 세 개가 있는 경우에는 이동하려고 하는 쪽의 칸이 먼저 합쳐진다. 
 * - 예를 들어, 위로 이동시키는 경우에는 위쪽에 있는 블록이 먼저 합쳐지게 된다.
 */
public class Main {
    static int N, max;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫째 줄에 보드의 크기 N (1 ≤ N ≤ 20)이 주어진다.
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        // 게임판의 초기 상태가 주어진다.
        // 0은 빈 칸을 나타내며, 이외의 값은 모두 블록을 나타낸다.
        // 블록에 쓰여 있는 수는 2보다 크거나 같고, 1024보다 작거나 같은 2의 제곱꼴이다. 
        // 블록은 적어도 하나 주어진다.
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        // 1. 재귀함수 - 상하좌우 완전탐색한다.
        // 2. cnt == 5 일경우 결과 값을 도출한다.
        go(0, map);
        
        // 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록을 출력한다.
        System.out.println(max);
    }

    private static void go(int cnt, int[][] map) {
        if (cnt == 5) {
            getMax(map);
            return;
        }
        
        // 4방향 rotate 각각 실시
        for (int k = 0; k < 4; k++) {
            go(cnt + 1, move(map));
            rotate(map); // 배열을 회전한다.
        }
    }

    // 가로 기준으로 왼쪽으로 당긴다.
    private static int[][] move(int[][] map) {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            int curIdx = -1; // 현재까지 합쳐진 곳 인덱스
            boolean flag = false; // 합쳐질 수 있는지 여부

            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;
                
                // 합쳐질 수 있고, 블록이 같은 경우
                // 현재까지 합쳐진 부분 tmp[i][curIdx]와 현재 좌표 map[i][j]부분 비교
                if (flag && map[i][j] == tmp[i][curIdx]) {
                    tmp[i][curIdx] *= 2;
                    flag = false;
                } else {
                    tmp[i][++curIdx] = map[i][j];
                    flag = true;
                }
            }
        }

        return tmp;
    }
    
    // 배열을 돌린다.
    private static void rotate(int[][] map) {
        int[][] tmp = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[N - j - 1][i]; // 90도 시계방향 회전
            }
        }
        
        // 복사
        for (int y = 0; y < N; y++) {
            System.arraycopy(tmp[y], 0, map[y], 0, tmp[y].length);
        }
    }
	
    // 최댓값 구하기
    private static void getMax(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

}