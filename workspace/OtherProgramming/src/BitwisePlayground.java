
public class BitwisePlayground {
	public static void main(String args[]){
		int a = Integer.parseInt("10000001",2);
		rep(a);
		rep(a>>5);
		int val = Integer.parseInt("-1000000000000000000000010000001",2);
		rep(val);
		rep(Integer.toBinaryString(val));
		rep("n 1s: " + countOnes(val));
		
	}

	private static void rep(Object a) {
		System.out.println(a);
	}
	
	public static int countOnes(int i){
		int count = 0;
		while(i!=0){
			count+=(i & 1);
			i = i >>> 1;
		}
		return count;
	}
}
