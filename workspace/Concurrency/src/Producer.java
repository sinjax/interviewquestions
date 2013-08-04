import java.util.Random;


public class Producer implements Runnable{
	Buffer<Integer> buffer;
	public Producer(Buffer<Integer> buffer){
		this.buffer = buffer;
	}
	@Override
	public void run() {
		Random random = new Random();
		while(true){
			int p = random.nextInt();
			System.out.println("Adding: " + p );
			this.buffer.add(p);
		}
	}
}
