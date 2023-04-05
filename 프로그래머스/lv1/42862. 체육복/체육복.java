import java.util.*;

class Solution {
    int[] arr;
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        arr = new int[n + 1];
        
        // 체육복이 있는 학생과 없는 학생을 표시
        for(int a: lost) arr[a]--;
        for(int b: reserve) arr[b]++;
        
        for(int i=1; i<=n; i++){
            if(arr[i] >= 0) answer++;
            else if(arr[i] == -1){
                if(i != 1 && arr[i-1] >= 1){
                    arr[i-1]--;
                    arr[i]++;
                    answer++;
                }else if(i != n && arr[i+1] >= 1){
                    arr[i+1]--;
                    arr[i]++;
                    answer++;
                } 
            }
        }
        
        return answer;
    }
}