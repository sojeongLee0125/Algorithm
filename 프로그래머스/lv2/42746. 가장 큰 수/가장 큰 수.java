import java.util.*;

// 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
// 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 
// 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        boolean zero = true;
        
        // 무조건 첫째자리 수가 큰 값이 앞으로 와야한다. 그 다음은 둘째 자리수 string 의 정렬을 이용한다.
        // 3과 30 같은 수의 경우 첫째 자리가 같고 단위가 다를 경우 33과 30을 비교해서 계산해야 한다.
        for(int num : numbers){
            String str = String.valueOf(num);
            list.add(str);
            if(num != 0) zero = false;
        }
        
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        
        for(String num : list){
            sb.append(num);
        }
        
        if(zero) return "0";
        else return sb.toString();
    }
}