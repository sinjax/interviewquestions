
public class CountingSort {
	public static void sort(int arr[]){
		int min = 0;
		int max = PrimeNumberGenerator.estimateNextPrimeLocation(arr.length);
		
		int[] counts = new int[(max - min) + 1];
		for(int x : arr){
			counts[x-min]++;
		}
		int index = 0;
		for(int i = 0; i < counts.length; i++){
			while(counts[i]>0){
				arr[index++] = i + min;
				counts[i]--;
			}
		}
	}
}
