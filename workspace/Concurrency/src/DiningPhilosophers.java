

public class DiningPhilosophers {

    // Each "fork" is just an Object we synchronize on
    private Object[]      forks;

    private Philosopher[] philosophers;

    // Prepare the forks and philosophers

    private DiningPhilosophers( int num ){

        forks = new Object[ num ];
        philosophers = new Philosopher[ num ];
        
        Waiter waiter = new Waiter(philosophers,forks);
        waiter.start();
        
        for( int i = 0; i < num; ++i ){
            forks[i] = new Object();
//            if(i == 0)
            	philosophers[i] = new Philosopher( i, i, ( i + 1 ) % num ,waiter);
//            else
//            	philosophers[i] = new Philosopher( i, ( i + 1 ) % num, i);
        }
        
        
    }

    
    private class Waiter extends Thread{
    	private Philosopher[] philosophers;
		private Object[] forks;
		private int forksInUse;;

		public Waiter(Philosopher[] philosophers,Object[] forks){
    		this.philosophers = philosophers;
    		this.forks = forks;
    		this.forksInUse = 0;
    	}
		
		public void run(){
			while(true){
				try {
					sleep(2000);
				} catch (InterruptedException e) {
				}
				printState();
			}
		}
		
		public synchronized Object[] giveForks(int fork1, int fork2){
			while(this.forksInUse >= this.forks.length-1){
            	try {
					wait();
				} catch (InterruptedException e) {
				}
            }
			this.forksInUse+=2;
			return new Object[]{this.forks[fork1],this.forks[fork2]};
		}
		
		public void printState(){
			for(Philosopher p : philosophers){
				System.out.println(p.id + " has eaten " + p.eaten);
			}
		}

		public synchronized void returnForks(int fork1, int fork2) {
			this.forksInUse-=2;
			notifyAll();
		}
    }
    // Start the eating process

    public void startEating() throws InterruptedException {
        for( int i = 0; i < philosophers.length; ++i ){
        	philosophers[i].start();
        }

	    // Suspend the main thread until the first philosopher
	    // stops eating, which will never happen -- this keeps
	    // the simulation running indefinitely
	
	    philosophers[0].join();
    }
	
	// Entry point for simulation
	
	public static void main( String[] args ){
	    try {
	        DiningPhilosophers d = new DiningPhilosophers( 5 );
	        d.startEating();
	    }
	    catch( InterruptedException e ){
	    }
	}

// Each philosopher runs in its own thread.

	private class Philosopher extends Thread {
	
	    public int eaten;
		private int id;
	    private int fork1;
	    private int fork2;
		private Waiter waiter;
	
	    Philosopher( int id, int fork1, int fork2 , Waiter waiter){
	        this.id = id;
	        this.fork1 = fork1;
	        this.fork2 = fork2;
	        this.waiter = waiter;
	    }
	
	    public void run() {
	
	        status( "Ready to eat using forks " + fork1 +
	                " and " + fork2 );
	
	        pause(); // pause to let others get ready
	
	        while( true ){
	            status( "Asking waiter for forks");
	            Object[] myForks = waiter.giveForks(fork1, fork2);
	            
	            status( "Eating" );
	            eaten++;
	            waiter.returnForks(fork1,fork2);
	        }
	    }
	
	    private void pause(){
	        try {
	            sleep( 200 );
	        }
	        catch( InterruptedException e ){
	            // do nothing
	        }
	   }
	
	    private void status( String msg ){
//	        System.out.println( "Philosopher " + id +
//	                            ": " + msg );
		}
	}
}