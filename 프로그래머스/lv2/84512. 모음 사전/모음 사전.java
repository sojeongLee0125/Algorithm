import java.util.*;

class Solution {
    String target;
    int ret;
    String[] arr = {"A","E","I","O","U"};
    ArrayList<String> list = new ArrayList<>();
    
    public int solution(String word) {
        target = word;
        dfs(0, new StringBuilder(), 0);
        
        for(int i=0; i<list.size(); i++){
            if(list.get(i).equals(target)) break;
            ret++;
        }
        
        return ret;
    }
    
    public void dfs(int idx, StringBuilder sb, int cnt){
        if(idx == 6) return;
        list.add(sb.toString());
        
        for(int i=0; i<5; i++){
            sb.append(arr[i]);
            dfs(idx+1, sb, cnt);
            sb.deleteCharAt(idx);
        }
    }
}