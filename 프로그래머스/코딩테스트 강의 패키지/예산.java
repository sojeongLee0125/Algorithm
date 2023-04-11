import java.util.*;

class Solution {    
    public int solution(int[] budgets, int M) {
        int answer = 0;
        int max = 0;

        // 예산 값 정렬
        Arrays.sort(budgets);
        int lt = 0;
        int rt = budgets[budgets.length - 1];

        while(lt <= rt){
            int mid = (lt + rt) / 2;

            if(isOk(mid, budgets, M)){
                // 해당 상한액을 조건 만족하는 경우
                // 답을 갱신하고 예산 크기 늘리기
                answer = mid;
                lt = mid + 1;
            }else rt = mid - 1;
        }
        
        return answer;
    }

    public boolean isOk(int num, int[] budgets, int M){
        int total = 0;

        for(int a : budgets){
            if(a > num) total += num;
            else total += a;
        }

        return total <= M;
    }
}
