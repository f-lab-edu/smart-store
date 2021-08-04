package programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class PickUpPuppet {
	public static void main(String[] args) {
		PickUpPuppet p = new PickUpPuppet();
		int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		p.solution(board, moves);
	}
	
	public int solution(int[][] board, int[] moves) {
        List<Integer> basket = new ArrayList<Integer>();
        int puppetNum = 0;
        int deleteCnt = 0;
        for(int m : moves) {
        	for(int i = 0; i < board.length; i++) {
        		int idx = m-1;
        		if(board[i][idx] != 0) {
        			puppetNum = board[i][idx];
        			board[i][idx] = 0;
        			if(basket.size() == 0) {
        				basket.add(puppetNum);
        			}else {
        				int lastIdx = basket.size() - 1;
        				int lastPuppetNum = basket.get(lastIdx);
        				if(lastPuppetNum == puppetNum) {
        					basket.remove(lastIdx);
        					deleteCnt++;
        				}else {
        					basket.add(puppetNum);
        				}
        			}
        			break;
        		}
        	}
        }
        return deleteCnt*2;
    }
}
