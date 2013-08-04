import java.util.Random;


public class RandomFactory<T> implements Factory<Integer> {
	private Random random;

	public RandomFactory(){
		this.random = new Random();
	}
	
	int addNumber = 0;
	int gotNumber = 0;
	@Override
	public synchronized Integer more() {
		int p = this.random.nextInt();
		System.out.println(addNumber++ +") Adding: " + p);
		return p;
	}

	@Override
	public synchronized void handle(Integer a) {
		System.out.println(gotNumber++ + ") Got: " + a);
	}

}
