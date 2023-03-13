import java.util.*;

// 다양한 모양과 크기의 명함들을 모두 수납할 수 있으면서, 작아서 들고 다니기 편한 지갑을 만들어야 합니다. 
// 모든 명함의 가로 길이와 세로 길이를 나타내는 2차원 배열 sizes가 매개변수로 주어집니다. 
// 모든 명함을 수납할 수 있는 가장 작은 지갑을 만들 때, 지갑의 크기를 return 하도록 solution 함수를 완성해주세요.

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        // 각 쌍에서 큰 수를 앞으로 작은 수를 뒤로 보내서 2개 그룹을 만든다.
        // 큰 수 중에 가장 큰수 * 작은 수 중에 가장 큰 수
        
        int w = 0;
        int h = 0;
        
        for(int i=0; i<sizes.length; i++){
            int max = Math.max(sizes[i][0], sizes[i][1]);
            int min = Math.min(sizes[i][0], sizes[i][1]);
            w = Math.max(w, max);
            h = Math.max(h, min);
        }
        
        return w * h;
    }
}