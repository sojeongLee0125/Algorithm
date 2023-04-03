import java.util.*;

class Solution {
    public class Song implements Comparable<Song>{
        int num;
        int plays;
        
        public Song(int num, int plays){
            this.num = num;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Song s){
            if(this.plays == s.plays) return this.num - s.num;
            else return s.plays - this.plays;
        }
    }
    
    HashMap<String, Integer> gmap = new HashMap<>();
    HashMap<String, ArrayList<Song>> gsmap = new HashMap<>();
    
    public int[] solution(String[] genres, int[] plays) {
        
        for(int i=0; i<genres.length; i++){
            int num = i;
            String genre = genres[i];
            int play = plays[i];
            gmap.put(genre, gmap.getOrDefault(genre, 0) + play); // 장르 재생횟수 저장
            if(gsmap.containsKey(genre)) gsmap.get(genre).add(new Song(num, play));
            else{
                gsmap.put(genre, new ArrayList<>());
                gsmap.get(genre).add(new Song(num, play));
            }
        }
        
        // 장르를 재생횟수 순서대로 정렬한다.
        ArrayList<Integer> list = new ArrayList<>();
        for(int v : gmap.values()) list.add(v);
        Collections.sort(list, Collections.reverseOrder());
        
        ArrayList<String> glist = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            int num = list.get(i);
            for(String key : gmap.keySet()){
                if(gmap.get(key) == num){
                    glist.add(key);
                    break;
                }
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(String k : glist){
            ArrayList<Song> s = gsmap.get(k);
            s.sort(Song::compareTo);
            for(int i=0; i<s.size(); i++){
                ans.add(s.get(i).num);
                if(i == 1) break;
            }
        }
        
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}