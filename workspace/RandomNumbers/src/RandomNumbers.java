import java.util.Arrays;


public class RandomNumbers {
	private long seed;

	public RandomNumbers(){
		this.seed = 1;
	}
	public int random(int low, int high){
		int min = Math.min(low, high);
		int max = Math.max(low, high);
		
		int range = (max - min) ;
		float fact = random();
		return (int) (min + (range * fact));
	}
	
	public float random(){
		seed = (int) (23 * seed % 10e8 + 1);
		return (float)(seed % 10e5)/((float)10e5);
	}
	
	public static void main(String args[]) throws InterruptedException{
		RandomNumbers r = new RandomNumbers();
		int [] outs = new int[7];
		for (int i = 0; i < 1000000; i++){
			outs[r.random(0,outs.length)] ++;
		}
		System.out.println(Arrays.toString(outs));
	}
}
