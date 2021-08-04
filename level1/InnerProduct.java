package programmers.level1;

public class InnerProduct {
	public static void main(String[] args) {
		InnerProduct p = new InnerProduct();
//		int[] a = {1,2,3,4};  
//		int[] b = {-3,-1,0,2};  //3
		int[] a = {-1,0,1};  
		int[] b = {1,0,-1};  //-2
		p.solution(a, b);
		
	}
	public int solution(int[] a, int[] b) {
        int answer = 1234567890;

        for(int i =0; i < a.length;i++) {
        	answer += a[i]*b[i];
        }
        return answer;
    }
	
	public int recursive(int a, int b) {
		
		return a*b;
	}
}
