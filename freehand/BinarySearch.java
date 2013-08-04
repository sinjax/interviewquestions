import java.lang.Integer;
class BinarySearch{
	public final static int NOT_FOUND = -1;
	public final static int UNSORTED = -2;
	static int binarySearch( int[] array, int target ){
		return binarySearch(array,0,array.length-1,target);
	}
	static int binarySearch( int[] array, int lower, int upper, int target ){
		int diff = (upper - lower);
		int mid = lower + (diff / 2);
		System.out.println("l:" + lower + ", u:" + upper + ", m:" + mid);
		if(array[mid] == target) return mid;
		
		if(array[lower] > array[upper] || array[mid] > array[upper]) return UNSORTED;
		if(upper == lower) return NOT_FOUND;
		else if(target < array[mid] ) return binarySearch(array,lower,mid-1,target);
		else return binarySearch(array,mid+1,upper,target);
	}
	
	static int iterBinarySearch(int[] array, int target ){
		int lower = 0; int upper = array.length - 1;
		while(true){
			int diff = (upper - lower);
			int mid = lower + (diff / 2);
			System.out.println("l:" + lower + ", u:" + upper + ", m:" + mid);
			if(array[mid] == target) return mid;

			if(array[lower] > array[upper] || array[mid] > array[upper]) return UNSORTED;
			if(upper == lower) return NOT_FOUND;
			else if(target < array[mid] ) 
				upper = mid - 1;
			else
				lower = mid+1;
		}
		
	}
	
	public static void main(String args[]){
		if(args.length < 2) return;
		int[] values = new int[args.length-1];
		System.out.print("arr: [");
		for(int i = 0; i < values.length; i++){
			values[i] = Integer.parseInt(args[i]);
			System.out.print(values[i] + ", ");
		}
		System.out.println("]");
		int target = Integer.parseInt(args[args.length - 1]);
		System.out.println("target: " + target);
		System.out.println(binarySearch(values,target));
		System.out.println(iterBinarySearch(values,target));
	}
}