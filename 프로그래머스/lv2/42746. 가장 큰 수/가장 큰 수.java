import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        String[] str = new String[numbers.length];
        
        int idx = 0;
        boolean isZero = true;
        
        for(int n: numbers){
            str[idx++] = n + "";
            if(n != 0) isZero = false;
        }
        
        if(isZero) return "0";
        
        Arrays.sort(str, (s1, s2) ->{
            return (s2+s1).compareTo(s1+s2);
        });
        
        for(String s : str) sb.append(s);
        
        return sb.toString();
    }
}