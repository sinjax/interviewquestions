
	
public class Consumer implements Runnable{
	Buffer<Integer> buffer;
	public Consumer(Buffer<Integer> buffer){
		this.buffer = buffer;
	}
	@Override
	public void run() {
		while(true){
			Integer got = buffer.get();
			System.out.println("Just got: " + got);
		}
		
	}
}
