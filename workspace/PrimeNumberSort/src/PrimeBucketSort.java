import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;


public class PrimeBucketSort {
	private static final int c = 10;
	private static ArrayList<Bucket> bucketList;
	static{
		
	}
	
	/**
	 * Find the min/max primes.
	 * Construct k buckets of size 100 with varying ranges between the min and max found
	 * sort each item in the array to its appropriate bucket
	 * sort each bucket (using quick sort)
	 * recombine buckets:
	 * n + n * k + k * c * log(c) + k
	 * O(nk)
	 * @param primes
	 */
	public static void sort(int[] primes){
		int max = max(primes);
		int min = min(primes);
		
		sort(primes,max,min);
	}
	private static class Bucket{
		private int[] list;
		private int min;
		private int max;
		private int index;

		public Bucket(int min, int max){
			this.list = new int[c*10];
			this.min = min;
			this.max = max;
			this.index = 0;
		}
		public boolean inside(int x){
			return x < max && x >= min; 
		}
		public void add(int x){
			if(index >= this.list.length){
				int[] newList = new int[this.list.length * 2];
				System.arraycopy(list, 0, newList, 0, index);
				this.list = newList;
			}
			this.list[index++] = x;
		}
	}
	public static void sort(int[] primes, int max, int min) {
		
		bucketList = new ArrayList<Bucket>();
//		int min = 0;
//		int max = Integer.MAX_VALUE;
		int bot = min;
		int top = -1;
		// Construct all the buckets
		while(top < max){
			int nPrimesAtBot = PrimeNumberGenerator.nPrimesUnder(bot);
			top = PrimeNumberGenerator.estimateNextPrimeLocation(nPrimesAtBot + c);
			bucketList.add(new Bucket(bot,top));
			bot = top;
		}
		
		// Place each prime in a bucket
		for(int x : primes){
			for(Bucket b : bucketList){
				if(b.inside(x)){
					b.add(x);
					break;
				}
			}
		}
		// Sort the primes
		int index = 0;
		for(Bucket b : bucketList){
			Arrays.sort(b.list,0,b.index);
			for(int i = 0; i < b.index; i++){
				primes[index++] = b.list[i];
			}
		}
	}

	public static int min(int[] primes) {
		int min = Integer.MAX_VALUE;
		for(int x: primes){
			if(x < min)
				min = x;
		}
		return min;
	}

	public static int max(int[] primes) {
		int max = -Integer.MAX_VALUE;
		for(int x: primes){
			if(x > max)
				max = x;
		}
		return max;
	}
}
