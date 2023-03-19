import java.util.*;

// 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다. 
// 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
// 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 
// 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.

class Solution {
    int[] arr;
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        arr = new int[n + 2];
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // 잃어버린 학생들 -1로 변경
        for(int a : lost){
            arr[a] = -1;
        }
        
        // 여분이 있는 학생들 +1
        for(int a : reserve){
            arr[a]++;
        }
        
        for(int i=1; i<=n; i++){
            // 체육복이 있을 경우
            if(arr[i] != -1) {
                answer++;
                continue;
            }
            // 체육복이 없을 경우
            if(arr[i] == -1){
                if(arr[i-1] == 1) {
                    arr[i] = 0;
                    arr[i-1] = 0;
                    answer++;
                }else if(arr[i + 1] == 1){
                    arr[i] = 0;
                    arr[i+1] = 0;
                    answer++;
                }
            }
        }
        
        return answer;
    }
}