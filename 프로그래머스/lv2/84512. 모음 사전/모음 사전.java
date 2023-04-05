import java.util.*;

class Solution {
    String target;
    String[] arr = {"A","E","I","O","U"};
    ArrayList<String> list = new ArrayList<>();
    
    public int solution(String word) {
        target = word;
        dfs(0, new StringBuilder(), 0);
        return list.indexOf(target);
    }
    
    public void dfs(int idx, StringBuilder sb, int cnt){
        if(idx == 6) return;
        list.add(sb.toString());
        for(int i=0; i<5; i++){
            dfs(idx+1, sb.append(arr[i]), cnt);
            sb.deleteCharAt(idx);
        }
    }
}