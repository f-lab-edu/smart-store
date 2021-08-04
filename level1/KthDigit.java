package programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class KthDigit {
	public static void main(String[] args) {
		
//		2. K��° ��
//		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[] array = {12, 51, 22, 16, 34, 76, 14};
		int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
		solution2(array, commands);
		
	}
	
	public static int[] solution2(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		int idx = 0;
		int i, j, k = 0;
		
		// Ŀ�ǵ� �迭 ���̸�ŭ �ݺ��Ѵ�.  
		for(int x = 0; x < commands.length; x++ ) {
			List<Integer> numList = new ArrayList<>();
			i = commands[x][0];
			j = commands[x][1];
			k = commands[x][2];

			// ���� �迭�� list�� ����
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
