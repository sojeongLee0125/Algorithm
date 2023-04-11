import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {        
        int answer = 0;

        // 배열을 정렬한다.
        Arrays.sort(A);
        Arrays.sort(B);

        int BIdx = B.length-1;
        for(int i=A.length-1; i>=0; i--){
            if(B[BIdx] > A[i]){
                answer++;
                BIdx--;
            }
        }

        return answer;
    }
}
