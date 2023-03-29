import java.util.*;

// 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 
// 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
// 변환할 수 없는 경우에는 0를 return 합니다.

class Solution {
    
    int min = Integer.MAX_VALUE; // 최소 변환 수
    int[] chk; // 단어 체크 수
    
    public int solution(String begin, String target, String[] words) {
        
        chk = new int[words.length];  
        dfs(0, begin, target, words);
        
        if(min == Integer.MAX_VALUE) return 0;
        else return min;
    }
    
    public void dfs(int cnt, String cur, String tar, String[] words){
        if(cur.equals(tar)){
            min = Math.min(min, cnt);
            return;
        }
        
        for(int i=0; i<words.length; i++){
            if(chk[i]==1) continue;
            
            String w = words[i];
            int n = 0; // 겹치는 글자수 카운트
            
            for(int j=0; j < cur.length(); j++){
                if(cur.charAt(j) != w.charAt(j)) n++;
            }
            
            // 1글자만으로 변경 가능한 경우
            if(n == 1){
                chk[i] = 1;
                dfs(cnt+1, w, tar, words);
                chk[i] = 0;
            }
        }
    }
}