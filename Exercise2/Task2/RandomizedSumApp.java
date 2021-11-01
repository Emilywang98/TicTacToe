package Exercise2.Task2;

/**
 * creates threads and finds the sum of the randomized numbers
 */
public class RandomizedSumApp {

    public static void main(String[] args){
        RandomizedSum exercise = new RandomizedSum();

        Thread t1 = new Thread(exercise);
        Thread t2 = new Thread(exercise);
        Thread t3 = new Thread(exercise);
        Thread t4 = new Thread(exercise);
        Thread t5 = new Thread(exercise);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            //join allows one thread to wait for the completion of another
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The sum of the random numbers: " + exercise.getSum());
    }
}
