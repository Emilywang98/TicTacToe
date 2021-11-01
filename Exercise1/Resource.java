package Exercise1;

/**
 * Resource is the runnable interface for the multithreaded program
 */
public class Resource implements Runnable{

	private int counter;
	
	public int increment() {

		//wait till one thread is finished, blocks all other threads from running this increment
		synchronized(this){
			counter++;
		}
		return counter;
	}

	@Override
	public void run(){
		increment();
	}
	
}
