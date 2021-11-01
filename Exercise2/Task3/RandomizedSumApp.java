package Exercise2.Task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * creates 5 threads in a threadpool and finds the sum of the randomized numbers
 */
public class RandomizedSumApp {

    static final int maxThreadCount = 5;

    public static void main(String[] args) {
        int totalSum = 0;

        RandomizedSumThreadPool randomPool = new RandomizedSumThreadPool();

        //max thread pool count of 5
        ExecutorService pool = Executors.newFixedThreadPool(maxThreadCount);

        //generate another instance for every thread
        for (int i = 0; i<maxThreadCount; i++){
            pool.execute(randomPool);
        }
        pool.shutdown();

        //checks if all the threads are done executing, then it prints the statement
        //holds the main thread at this loop till all threads are done executing
        while(!pool.isTerminated()){
        }
        System.out.println("The sum of the random numbers: " + randomPool.getSum());

    }
}