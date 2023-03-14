import java.util.*;

// 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
// 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.
// 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 
// 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.
// 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.

class Solution {
    public int[] solution(int brown, int yellow) {
        // 브라운 + 노랑의 합을 구한다.
        // 해당 합을 3부터 나누면서 해당 답이 맞는지 확인한다.
        //  나눈 값 2개를 각각 -2 해서 곱한 값이 yellow 값이 맞는지 확인
        
        int sum = brown + yellow;
        
        int ans1 = 0;
        int ans2 = 0;
        
        for(int i=3; i <= sum; i++){
            int div = sum / i;
            int r = i - 2;
            int c = div - 2;
            if(r * c == yellow){
                ans1 = i;
                ans2 = div;
                break;
            }
        }
        
        int[] answer = {ans2, ans1};
        
        return answer;
    }
}