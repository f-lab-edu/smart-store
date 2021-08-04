package programmers.level1;

import java.util.Arrays;
import java.util.HashMap;

public class MarathonerNotFinish {
	public static void main(String[] args) {
//		String[] participants= {"leo", "kiki", "eden"};
//		String[] completions = {"eden", "kiki"};
//		"leo"
//		String[] participants= {"marina", "josipa", "nikola", "vinko", "filipa"};
//		String[] completions = {"josipa", "filipa", "marina", "nikola"};
////		vinko"
		String[] participants= {"mislav", "stanko", "mislav", "stanko", "ana"};
		String[] completions = {"stanko", "ana", "mislav"};
//		"mislav"
		System.out.println(solution(participants, completions));
	}
	
	
	public static String solution2(String[] participants, String[] completions) {
		Arrays.sort(participants);
		Arrays.sort(completions);
		
		int i;
		for(i = 0; i < completions.length; i++) {
			if(!participants[i].equals(completions[i])) {
				return participants[i];
			}
		}
		return participants[i];
    }
	
	public static String solution(String[] participants, String[] completions) {
		HashMap<String, Integer> map = new HashMap<>();
		for(String p : participants) map.put(p, map.getOrDefault(p, 0)+1);
		for(String c : completions) map.put(c, map.get(c)-1);
		
		for(String key : map.keySet()) {
			if(map.get(key) != 0) {
				return key;
			}
		}
		
		return "";
    }
}
