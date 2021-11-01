package Exercise2.Task3;

import java.util.Random;

/**
 * Generates a randomized number
 */
public class RandomizedSumThreadPool implements Runnable{
    private int sum = 0;

    public RandomizedSumThreadPool(){}

    synchronized public void AddRandomizedNumber() throws InterruptedException {
        Random rand = new Random();

        //generates random value from 1 - 100
        int a = (int) rand.nextInt(1, 100);

        System.out.println("Random number generated: " + a);

        //waits for the thread to complete operation, blocks all other threads
        synchronized(this){
            sum += a;
        }
    }

    /**
     * running the AddRandomizedNumber()
     */
    public void run() {
        try {
            AddRandomizedNumber();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSum(){
        this.sum = sum;
    }

    public int getSum(){

        return sum;
    }
}
