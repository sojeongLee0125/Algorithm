import java.util.*;

// 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
// 배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때, 
// 결과를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        
        // commands 를 반복문으로 돌면서 맞는 값을 구한다.
        for(int i=0; i<commands.length; i++){
            int st = commands[i][0] - 1;
            int ed = commands[i][1];
            int k = commands[i][2] - 1;
            int[] arr2 = Arrays.copyOfRange(array, st, ed);
            Arrays.sort(arr2);
            answer[idx++] = arr2[k];
        }
        
        return answer;
    }
}