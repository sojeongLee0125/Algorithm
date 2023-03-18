import java.util.*;

// 사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다.
// 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
// 단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.

class Solution {
    char[] arr = new char[]{'A', 'E', 'I', 'O', 'U'};
    ArrayList<String> dict = new ArrayList<>();
    
    public void makeDict(String word, int depth){
        if(depth == 5){
            return;
        }
        
        for(int i=0; i<5; i++){
            String tmp = word + arr[i];
            dict.add(tmp);
            makeDict(tmp, depth + 1);
        }
    }
    
    public int solution(String word) {
        int answer = 1;
        
        // A E I O U를 완전탐색해서 전체 단어 사전을 만든다
        // word를 처음부터 완전탐색해서 해당 순서를 구한다.
        makeDict("", 0);
        
        for(int i=0; i<dict.size(); i++){
            if(word.equals(dict.get(i))) break;
            else answer++;
        }
        
        return answer;
    }
}