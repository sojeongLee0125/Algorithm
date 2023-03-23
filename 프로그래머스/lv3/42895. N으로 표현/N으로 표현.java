import java.util.*;

// 이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 
// N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
// 최솟값이 8보다 크면 -1을 return 합니다. 즉, N의 최대 사용 횟수는 8이다.

class Solution {
    public int solution(int N, int number) {
        
        // N을 1번 사용했을 때 만들 수 있는 수 : N
        // N을 2번 사용했을 때 만들 수 있는 수 : NN(이어 붙인것) & (N + N), (N - N), (N * N), (N / N)
        // N을 3번 사용했을 때 만들 수 있는 수 : NNN(이어 붙인것) & 1 + 2 & 2 + 1
        // N을 N번 사용했을 때 만들 수 있는 수 : N을 N번 이어 붙인것 &
        //                                  1과 N-1의 사칙 연산 + 2와 N-2의 사칙연산 ... + N-1 과 1의 사칙연산 집합
        
        // 1. 리스트를 만든다 {{1번 집합}, {2번 집합}, ... , {8번 집합}}
        // 2. 각 리스트에 이어붙인 수를 집어 넣는다.
        // 3. 반복문 i : 1 -> 8 (1번 부터 8번까지의 집합을 채워 넣는다.)
        //              j : 1 -> i (1번 부터 목표 집합까지의 연산)
        //                  list(j) +-*/ (i - j - 1) => list[i] 에 넣는다.
        // 만약 사칙연산 값이 number 가 나오면 break, 8개를 넘어가도 닶이 없으면 -1을 리턴한다.
        
        int answer = -1; // N의 사용 횟수
        ArrayList<Integer>[] list = new ArrayList[9];
         
        for(int i=0; i <= 8; i++){
            int cnt = i;
            list[i] = new ArrayList<>();
            
            StringBuilder sb = new StringBuilder();
            while(cnt-- > 0){
                sb.append(N);
            }
            
            if(i == 0) continue;
            list[i].add(Integer.valueOf(sb.toString()));
        }
        
        for(int i=1; i<=8; i++){
            for(int j=1; j<i; j++){
                for(int n1 : list[j]){
                    for(int n2 : list[i - j]){
                        list[i].add(n1 + n2);
                        list[i].add(n1 - n2);
                        list[i].add(n1 * n2);
                        if(n2 != 0) list[i].add(n1 / n2);
                    }
                }
            }
        }

        outer : for(int i=1; i<=8; i++){
            for(int num : list[i]){
                if(num == number){
                    answer = i;
                    break outer;
                }
            }
        }
        
        return answer;
    }
}