package programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class Phoneketmon {
	public static void main(String[] args) {
		
//		1. 폰켓몬 문제 - 
//		int[] nums = {3,1,2,3};
//		int[] nums = {3,3,3,2,2,4};
		int[] nums = {3,3,3,2,5,4};
		solution(nums);
		
//		2. K번째 수
//		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[] array = {12, 51, 22, 16, 34, 76, 14};
		int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
		solution2(array, commands);
		
	}
	
	public static int solution(int[] nums) {
		// 문제 이해
//		내가 가질 수 있는 개수이면서 중복이 없는 수 -> 최대값
//		모두가 다른 것이라면 최대값.
		
        // 가져갈 수 있는 개수
        int numberIcanHave = nums.length / 2;
        // 가져갈 것을 담을 리스트
        List<Integer> mine = new ArrayList<Integer>();
        
        for(int i = 0; i < nums.length ; i++) {
        	if( mine.size() < numberIcanHave ) {
        		if(!mine.contains(nums[i])) {
        			mine.add(nums[i]);
	        	}
        	}
        }
        
        return mine.size();
    }
	
	public static int[] solution2(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		int idx = 0;
		int i, j, k = 0;
		
		// 커맨드 배열 길이만큼 반복한다.  
		for(int x = 0; x < commands.length; x++ ) {
			List<Integer> numList = new ArrayList<>();
			i = commands[x][0];
			j = commands[x][1];
			k = commands[x][2];

			// 원본 배열을 list에 복제
			for(int y = i-1; y < j; y++) {
				numList.add(array[y]);
			}
			
			numList.sort(null);
			
			answer[idx] = numList.get(k-1);
					
			idx++;
		}
		
        
        return answer;
    }
}
