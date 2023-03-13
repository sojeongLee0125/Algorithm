import java.util.*;

// 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 
// 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int cnt1 = 0; 
        int cnt2 = 0;
        int cnt3 = 0;
        int[] s1 = {1, 2, 3, 4, 5}; // % 5
        int[] s2 = {2, 1, 2, 3, 2, 4, 2, 5}; // % 8
        int[] s3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 10
        
        for(int i=0; i < answers.length; i++){
            if(s1[i % 5] == answers[i]) cnt1++;
            if(s2[i % 8] == answers[i]) cnt2++;
            if(s3[i % 10] == answers[i]) cnt3++;
        }
        
        int max = Math.max(cnt1, Math.max(cnt2, cnt3));
        if(cnt1 == max) list.add(1);
        if(cnt2 == max) list.add(2);
        if(cnt3 == max) list.add(3);
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) answer[i] = list.get(i);
        
        return answer;
    }
}