package programmers.level1;

public class GivingUpMath {
	public static void main(String[] args) {
//		1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
//		2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
//		3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
		
//		input 	    return
//		[1,2,3,4,5]	[1]
//		[1,3,2,4,2]	[1,2,3]
		
		int[] answer = {1,3,2,4,2};
		GivingUpMath g = new GivingUpMath();
		g.solution(answer);
	}
	
	public int[] solution(int[] answers) {
		int[] score = new int[3];
		
		int[][] patterns = {{1, 2, 3, 4, 5}, 
							{2, 1, 2, 3, 2, 4, 2, 5}, 
							{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
		
		for(int i = 0 ; i < score.length; i++) {
			for(int j =0; j < answers.length; j++) {
				int length = patterns[i].length;
				if(answers[j]==patterns[i][j%length]) score[i]++;
			}
		}
		
		int top = 0;
		for(int i =0; i < score.length; i++) {
			if(score[i] > top) top = score[i];
		}
		
		int size = 0;
		for(int i : score) {
			if(i==top) size++;
		}
		
		int[] answer = new int[size];
		int idx = 0;
		for(int i = 0; i < score.length; i++) {
			if(top == score[i]) answer[idx++] = i+1;
		}
		
		printArray(answer);
        return answer;
    }
	
	
	public void printArray(int[] array) {
		for(int a : array) {
			System.out.print(a + " ");
		}
	}
	
	
}
