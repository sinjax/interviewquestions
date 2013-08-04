import java.util.HashMap;
import java.util.HashSet;


public class ArrayStringProblems {
	public static String firstNonRepeated(String s){
		HashMap<Character,Object> charMap = new HashMap<Character,Object>();
		Object once = new Object();
		Object twice = new Object();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			Object current = charMap.get(c);
			if(current == null)
				charMap.put(c, once);
			else
				charMap.put(c, twice);
			
		}
		for(int i = 0; i < s.length(); i++){
			if(charMap.get(s.charAt(i))==once)
				return s.charAt(i) + "";
		}
		return "";
	}
	
	public static String removeChars(String str, String remove){
		// Construct the search tree
		HashSet<Character> charMap = new HashSet<Character>();
		for(int i = 0; i < remove.length(); i++){
			charMap.add(remove.charAt(i));
		}
		// Build the string
		int dst = 0;
		int src = 0;
		char[] outChar = str.toCharArray();
		for(int i = 0;i < str.length(); i++){
			if(!charMap.contains(str.charAt(i))){
				outChar[dst++] = outChar[src];
			}
			src++;
		}
		return new String(outChar,0,dst);
	}
	
	public static int stringToInt(String str){
		char min = '0';
		int out = 0;
		int multi = 1;
		int end = 0;
		while(str.charAt(end) == '-' || str.charAt(end) == '+' )
			end ++;
		for(int i = str.length()-1; i >= end; i--){
			out += (str.charAt(i) - min) * multi;
			multi *= 10;
		}
		end--;
		while(end >= 0){
			out *= str.charAt(end--) == '-' ? -1 : 1;
		}
		return out;
	}
	
	public static final int MAX_LENGTH = 10;
	public static String intToString(int input){
		if(input == 0) return "0";
		
		char[] outChars = new char[MAX_LENGTH+1];
		int index = MAX_LENGTH;
		char negChar = 0;
		
		if(input < 0){
			negChar = '-';
			input*=-1;
		}
		while(input > 0){
			int units = (input % 10);
			outChars[index--] = (char)('0' + units);
			input = (input - units) / 10;
		}
		index++;
		StringBuilder b = new StringBuilder();
		if(negChar != 0) b.append(negChar);
		while(index <= MAX_LENGTH){
			b.append(outChars[index++]);
		}
		
		return b.toString();
	}
	
	
}
