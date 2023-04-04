import java.util.*;

class Solution {
    HashMap<Integer, Integer> map = new HashMap<>(); // 중복 소수 체크용
    int[] chk = new int[10]; // 0 부터 9까지 갯수 저장 
    int len, cnt;
    
    public int solution(String numbers) {
        len = numbers.length();
        
        for(char c : numbers.toCharArray()){
            chk[c -'0']++;
        }
        
        dfs(0, new StringBuilder());
        
        return cnt;
    }
    
    public boolean isPrime(String num){
        if(num.equals("")) return false;
        
        int n = Integer.parseInt(num);
        
        if(n < 2) return false;
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }
        
        return true;
    }
    
    public void dfs(int idx, StringBuilder sb){
        if(idx > len) return;
    
        if(isPrime(sb.toString()) 
           && !map.containsKey(Integer.parseInt(sb.toString()))){
            map.put(Integer.parseInt(sb.toString()), 1);
            cnt++;
        }
        
        for(int i=0; i<10; i++){
            if(chk[i] > 0){
                chk[i]--;
                dfs(idx + 1, sb.append(i));
                sb.deleteCharAt(sb.toString().length()-1);
                chk[i]++;
            }
        }
    }
}