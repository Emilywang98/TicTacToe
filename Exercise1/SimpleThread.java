package Exercise1;

/**
 * implementation of two threads to increment the counter of resource
 */
public class SimpleThread extends Thread{

	Resource resource;

	SimpleThread(Resource resource){
		this.resource = resource;
	}

	public void run() {
		for(int i = 0; i<10; i++){
			try {
			System.out.println(resource.increment());
			
			//puts thread to sleep for 1 millisecond
			Thread.sleep(1);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		Resource resource = new Resource();

		//creates 2 new threads
		Thread t = new SimpleThread(resource);
		Thread s = new SimpleThread(resource);

		//starts the threads
		t.start();
		s.start();
	}

}
