package Exercise2.Task1;

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

        int randomNum1 = exercise.getRandomNumber();
        int randomNum2 = exercise.getRandomNumber();
        int randomNum3 = exercise.getRandomNumber();
        int randomNum4 = exercise.getRandomNumber();
        int randomNum5 = exercise.getRandomNumber();

        int sum = randomNum1 + randomNum2 + randomNum3 + randomNum4 + randomNum5;

        System.out.println("The sum of the random numbers: " + sum);
    }
}
