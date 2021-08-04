package programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class NewIDRecommendation {
	public static void main(String[] args) {
		NewIDRecommendation n = new NewIDRecommendation();
		n.solution("...!@BaT#*..y.abcdefghijklm");	//"bat.y.abcdefghi"
//		n.solution("z-+.^.");	//"z--"
//		n.solution("=.="");	//	"aaa"
//		n.solution("123_.def"");	//	"123_.def"
//		n.solution("abcdefghijklmn.p"");	//	"abcdefghijklmn"
	}
	public String solution(String new_id) {
        String answer = new_id.toLowerCase();
        
        answer = answer.replaceAll("[^0-9a-z-_.]", "");
        answer = answer.replaceAll("[.]{2,}", ".");
        answer = answer.replaceAll("^[.]{1}", "");
        
        if(answer.equals(""))  answer += "a";
 
        
        if(answer.length() >= 16) answer = answer.substring(0, 15);
        
        answer = answer.replaceAll("[.]{1}$", "");
       
        
        if(answer.length() <= 2) {
            for(int i = answer.length(); i < 3; i++){
                answer += answer.substring(answer.length()-1);
            }
        }

        return answer;
    }
}
