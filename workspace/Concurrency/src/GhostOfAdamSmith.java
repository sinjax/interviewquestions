
public class GhostOfAdamSmith {
	public void go(){
		Factory<Integer> randomFactory = new RandomFactory<Integer>();
		Buffer<Integer> shared = new Buffer<Integer>(new Integer[10]);
		Consumer consumer = new Consumer(shared);
		Producer producer = new Producer(shared);
		Thread p = new Thread(producer);
		Thread c = new Thread(consumer);
		
		p.start();
		c.start();
	}
	
	public static void main(String[] args){
		new GhostOfAdamSmith().go();
	}
}
