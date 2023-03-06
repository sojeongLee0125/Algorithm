import java.util.*;
//  총 N 마리의 폰켓몬 중에서 N/2마리를 가져가도 좋다고 했습니다.
//  폰켓몬은 종류에 따라 번호를 붙여 구분합니다. 따라서 같은 종류의 폰켓몬은 같은 번호를 가지고 있습니다.
//  최대한 다양한 종류의 폰켓몬을 가지길 원하기 때문에, 최대한 많은 종류의 폰켓몬을 포함해서 N/2마리를 선택하려 합니다.  
//  N마리 폰켓몬의 종류 번호가 담긴 배열 nums가 매개변수로 주어질 때, 
//  N/2마리의 폰켓몬을 선택하는 방법 중, 가장 많은 종류의 폰켓몬을 선택하는 방법을 찾아, 
// 그때의 폰켓몬 종류 번호의 개수를 return 하도록 solution 함수를 완성해주세요.
// 가장 많은 종류의 폰켓몬을 선택하는 방법이 여러 가지인 경우에도, 선택할 수 있는 폰켓몬 종류 개수의 최댓값 하나만 리턴

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        // 1. nums 숫자들을 HashSet에 넣는다.
        // 2. HashSet의 사이즈와 nums/2 중 작은 값을 리턴한다.
        HashSet<Integer> set = new HashSet<>();
        for(int n: nums){
            set.add(n);
        }
        
        return Math.min(set.size(), nums.length / 2);
    }
}