import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PrimeNumberGenerator {
	public int[] getPrimes(int maxNumber){
		if(maxNumber<1)return new int[0];
		int[] out = new int[nPrimesUnder(maxNumber)];
		int pIndex = 0;
		out[pIndex++] = 1; if(maxNumber == 1) return out;
		out[pIndex++] = 2; if(maxNumber == 2) return out;
		out[pIndex++] = 3; if(maxNumber == 3) return out;
		out[pIndex++] = 5; if(maxNumber == 5) return out;
		out[pIndex++] = 7; if(maxNumber == 7) return out;
		out[pIndex++] = 11; if(maxNumber == 11) return out;
		out[pIndex++] = 13; if(maxNumber == 13) return out;
		
		int intervalSize = 2 * 3 * 5 * 7 * 11 * 13; // 30030
		
		int interval0 = 13;
		int interval1 = interval0 + intervalSize;
		interval1 = interval1 > maxNumber ? maxNumber: interval1; 
		
		boolean[] intervalArr = new boolean[intervalSize];
		while(pIndex < out.length){
//			System.out.println("Checking interval: " + interval0 + "->" + interval1);
			Arrays.fill(intervalArr, true);
			int oldPrimeStart = interval0+1;
			for(int primeIndex = 1; primeIndex < pIndex; primeIndex++){
				int indexPrime = out[primeIndex];
				int primeStart = indexPrime * indexPrime ;
				if(primeStart < interval0){
					primeStart = interval0;
					int modulo = (interval0 % indexPrime);
					primeStart += (indexPrime - modulo );
				}
				if(primeStart>=interval1)primeStart = interval1;
//				System.out.println("For prime: " + out[primeIndex] + " starting at: " + primeStart);
				for(int valToCheck = primeStart; valToCheck < interval1; valToCheck+=out[primeIndex]){
					intervalArr[valToCheck - interval0] = false;
				}
//				System.out.println("Checking for primes between: " + oldPrimeStart + " and " + primeStart);
				if(indexPrime * indexPrime >= oldPrimeStart){
					int checkForPrimes = oldPrimeStart;
					for(; checkForPrimes < primeStart && indexPrime * indexPrime >= checkForPrimes; checkForPrimes++){
						if(intervalArr[checkForPrimes - interval0])
							out[pIndex++] = checkForPrimes;
						if(pIndex>=out.length)break;
					}
					oldPrimeStart = checkForPrimes;
				}
				
				if(pIndex>=out.length)break;
				if(oldPrimeStart>=interval1)break;
			}
			if(pIndex>=out.length)break;
			if(interval1 >= maxNumber)break;
			// Prepare the next interval
			interval0 = out[pIndex-1];
			interval1 = interval0 + intervalSize;
			interval1 = interval1 > maxNumber ? maxNumber: interval1;
		}
		int[] finalOut = new int[pIndex];
		System.arraycopy(out, 0, finalOut, 0, finalOut.length);
		return finalOut;
	}
	
	public static int estimateNextPrimeLocation(int n){
		double logn = Math.log(n);
		return (int) Math.floor(n * (logn + Math.log(Math.log(n) - 1)));
	}
	
	public static int nPrimesUnder(int x) {
		// From Pierre Dusart 99 
		// pi(x) <= (x/log x)(1 + 1.2762/log x)
		double logx = Math.log(x);
		return (int) Math.ceil((x / logx) * (1 + 1.2762/logx));
	}
	

	public static void main(String args[]){
		PrimeNumberGenerator p = new PrimeNumberGenerator();
		
//		for(int i = 4; i < 17; i++){
//			long start = System.currentTimeMillis();
//			int max = (int)(2 * Math.pow(10, i));
//			p.getPrimes(max );
//			long end = System.currentTimeMillis();
//			System.out.println(max + " took:" + (end-start) + "ms" + " Expecting:" + p.nPrimesUnder(max));
//		}
		int[] sortedPrimes = p.getPrimes((int)(Math.pow(10,9)));
		timeSortingExperiment(sortedPrimes,5);
		
	}

	private static void timeSortingExperiment(int[] sortedPrimes, int nRepeats) {
		System.out.println("Number of primes: " + sortedPrimes.length);
		System.out.println("...Shuffeling");
		ArrayList<Integer> l = new ArrayList<Integer>(sortedPrimes.length);
		for(int a : sortedPrimes){
			l.add(a);
		}
		Collections.shuffle(l);
		int[] shuffledPrimes = new int[l.size()];
		int i = 0;
		for(int a : l){
			shuffledPrimes[i++] = a;
		}
		long start = 0;
		long end = 0;
		long jSort = 0;
		long bSort = 0;
		long cSort = 0;
		for(int nRepeat = 0; nRepeat < nRepeats; nRepeat++){
			System.out.println("...Repeat: " + nRepeat);
			System.out.println("......Sorting");
			int[] toSort = shuffledPrimes.clone();
			start = System.currentTimeMillis();
			Arrays.sort(toSort);
			end = System.currentTimeMillis();
			System.out.println("......Done");
			jSort += (end - start);
			
//			System.out.println("......Bucket Sorting");
//			toSort = shuffledPrimes.clone();
//			start = System.currentTimeMillis();
//			PrimeBucketSort.sort(toSort);
//			end = System.currentTimeMillis();
//			System.out.println("......Done");
//			bSort += (end - start);
			
			System.out.println("......Coutning Sort");
			toSort = shuffledPrimes.clone();
			start = System.currentTimeMillis();
			CountingSort.sort(toSort);
			end = System.currentTimeMillis();
			System.out.println("......Done");
			cSort += (end - start);
		}
		
		System.out.println("...jSort: " + jSort / nRepeats + "ms");
		System.out.println("...bSort: " + bSort / nRepeats + "ms");
		System.out.println("...cSort: " + cSort / nRepeats + "ms");
	}
}
