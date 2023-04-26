import java.util.*;

// 어느 과목을 수강하기 위해서는 이전에 이미 수강했어야 하는 과목들이 정해져 있다.
// 수강하고자 하는 과목을 정했을 때, 이 과목을 수강하기 위해서는 적어도 어떤 과목들을
// 어떤 순서에 따라 수강해야 하는지 알아내는 함수를 완성하세요.

// 배열 S1의 i번째 원소가 x이고 배열 S2의 i번째 원소가 y인 경우 x가 y의 선수과목이다.
// k : 수강하고자 하는 과목 코드

// k 과목을 이수하기 위하여 순서ㅓ대로 이수해야 하는 과목들의 코드를 나열한 배열을 반환
// 서로 선후수 관계가 없는 과목 M과 K를 이수하는 순서는 알파벳 순서

class Solution {
    Map<String, List<String>> prev = new HashMap<>(); // 선수 과목 리스트 저장
    Map<String, List<String>> K = new HashMap<>(); // K과목과 연관된 과목들 선수 과목 저장
    Map<String, Integer> degreeMap = new HashMap<>(); // 진입 차수 저장
    Queue<String> pq = new PriorityQueue<>(); // K 이전 과목들 알파벳 순으로 저장

    public String[] solution(String[] s1, String[] s2, String k) {

        // 위상 정렬
        // 1. 전체 과목 연결 그래프 생성
        for(int i=0; i<s1.length; i++){
            if(!prev.containsKey(s2[i])) prev.put(s2[i], new ArrayList<>()); 
            prev.get(s2[i]).add(s1[i]); // 선수과목 입력
        }

        // 2. 목적지 지점 입력 후 dfs 탐색
        Stack<String> stack = new Stack<>(); // dfs용 스택
        Map<String, Integer> chk = new HashMap<>(); // 방문체크
        stack.push(k);
        chk.put(k, 1);

        while(!stack.isEmpty()){
            String cur = stack.pop();

            // 선수과목이 없을 경우
            if(!prev.containsKey(cur)){
                pq.add(cur);
            }else{
                // 선수과목이 존재할 경우 진입차수 증가 후 kmap에 해당 과목의 후수과목 입력
                for(String pre : prev.get(cur)){
                    degreeMap.put(cur, degreeMap.getOrDefault(cur, 0) + 1); // 진입차수 증가
                    if(!K.containsKey(pre)) K.put(pre, new ArrayList<>());
                    K.get(pre).add(cur);
                    if(chk.containsKey(pre)) continue;
                    stack.push(pre);
                    chk.put(pre, 1);
                }
            }
        }

        ArrayList<String> answer = new ArrayList<>();

        while(!pq.isEmpty()){
            String cur = pq.poll();
            answer.add(cur);
            if(!K.containsKey(cur)) continue;
            for(String pr : K.get(cur)){ // 후수과목이 있을 경우
                // 진입차수 감소
                degreeMap.put(pr, degreeMap.getOrDefault(pr, 0) - 1); // 해당 후수과목 진입차수 감소
                // 진입차수가 0일 경우 pq입력
                if(degreeMap.get(pr) == 0) pq.add(pr);
            }
        }
        
        return answer.toArray(String[]::new);
    }
}
