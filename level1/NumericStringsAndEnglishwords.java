package programmers.level1;

public class NumericStringsAndEnglishwords {
	public static void main(String[] args) {
		String z = "zeroddd";
//		"one4seveneight"	1478
//		"23four5six7"	234567
//		"2three45sixseven"	234567
//		"123"	123
		solution("23four5six7");
	}
	
	public static int solution(String s) {
		String[] array = {"zero","one","two","three","four","five","six","seven","eight","nine"};
		
		for(int i = 0; i < array.length; i++ ) {
			if(s.indexOf(array[i]) != -1) {
				
				s = s.replace(array[i], i+"");
			}
		}
        return Integer.parseInt(s);
    }
}
