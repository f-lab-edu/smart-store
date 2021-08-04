package programmers.level1;

public class PushingKeypad {
	public static void main(String[] args) {
		PushingKeypad ps = new PushingKeypad();
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
//		"right"	"LRLLLRLLRRL"
//				 LRLLLRLLLRL
		ps.solution(numbers, "right");
	}
	
	public String solution(int[] numbers, String hand) {
        String answer = "";
        
        int[][] keyPad = {{10,0,11},{7,8,9},{4,5,6},{1,2,3}};
        
        StringBuilder sb = new StringBuilder();
        int[] l_position = new int[2];
        int[] r_position = new int[2]; 
//        int[] c_position = new int[2];
        
        for(int n : numbers) {
        	for(int i = 0; i < keyPad.length; i++) {
            	for(int j = 0; j < keyPad[i].length; j++) {
            		int push = keyPad[i][j];
            		if(push == n) {
            			if(j==0) {
            				l_position[0]=i; 
            				l_position[1]=j;
            				sb.append("L");
            			}
            			if(j==2) {
            				r_position[0]=i; 
            				r_position[1]=j;
            				sb.append("R");
            			}
            			
            			if(j==1) {
            				int l_x = l_position[0];
            				int l_y = l_position[1];
            				
            				int l_distance = Math.abs(l_x - i) + Math.abs(l_y - j);
            				
            				int r_x = r_position[0];
            				int r_y = r_position[1];
            				String whichHand = "";
            				
            				int r_distance = Math.abs(r_x - i)+Math.abs(r_y - j);
            				
            				//  오른손이 더 가까운 경우
            				if(l_distance > r_distance) {
            					whichHand = "R";
            					sb.append(whichHand);
            				}
            				// 왼손이 더 가까운 경우
            				else if(l_distance < r_distance) {
            					whichHand = "L";
            					sb.append(whichHand);
            				}
            				// 거리가 같은 경우
            				else {
            					// 오른손잡이
            					if(hand.equals("right")) {
                					whichHand = "R";
            						sb.append(whichHand);
            					}
            					// 왼손잡이
            					else {
                					whichHand = "L";
            						sb.append(whichHand);
            					}
            				}
            				
            				if(whichHand.equals("R")) {
            					r_position[0] = i;
            					r_position[1] = j;
            				}else {
            					l_position[0] = i;
            					l_position[1] = j;
            				}
            			}
            		}
            	}
            }
        }
        
        return sb.toString();
    }
}
