import java.util.*;

class Solution {
    
    public boolean isOk(int cnt, int[] arr){
        int up = 0;
        int down = 0;
        
        for(int i=0; i<arr.length; i++){
            if(arr[i] >= cnt) up++;
            else down++;
        }
        
        return (up >= cnt) && (down <= cnt);
    }
    
    public int solution(int[] citations) {
        int answer = 0;
        int lt = 0;
        int rt = 10000;
        
        Arrays.sort(citations);
        
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            if(isOk(mid, citations)){
                answer = mid;
                lt = mid + 1;
            }else rt = mid - 1;
        }
        
        return answer;
    }
}