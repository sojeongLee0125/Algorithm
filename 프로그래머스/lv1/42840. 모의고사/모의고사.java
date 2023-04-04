import java.util.*;

class Solution {
    int[] arr1 = {1,2,3,4,5}; // 5개
    int[] arr2 = {2,1,2,3,2,4,2,5}; //8개
    int[] arr3 = {3,3,1,1,2,2,4,4,5,5}; //10개
    
    public int[] solution(int[] answers) {
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        
        for(int i=0; i<answers.length; i++){
            if(arr1[i % 5] == answers[i]) cnt1++;
            if(arr2[i % 8] == answers[i]) cnt2++;
            if(arr3[i % 10] == answers[i]) cnt3++;
        }
        
        int max = Math.max(cnt1, Math.max(cnt2, cnt3));
        
        ArrayList<Integer> tmp = new ArrayList<>();
        if(cnt1 == max) tmp.add(1);
        if(cnt2 == max) tmp.add(2);
        if(cnt3 == max) tmp.add(3);
        
        return tmp.stream().mapToInt(Integer::intValue).toArray();
    }
}