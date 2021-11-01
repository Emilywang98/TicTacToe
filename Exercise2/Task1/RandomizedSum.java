package Exercise2.Task1;
import java.util.ArrayList;
import java.util.Random;

/**
 * Generates a randomized number
 */
public class RandomizedSum implements Runnable{
    public RandomizedSum(){}

    private int randomNumber;

    public int getRandomNumber(){
        return randomNumber;
    }

    public void setRandomNumber(int num){
        randomNumber = num;
    }


    public void generateRandomizedNumber(){
        Random rand = new Random();

        //generates random value from 1 - 100
        setRandomNumber((int) rand.nextInt(1, 100));
    }

    public void run() {
        generateRandomizedNumber();
    }

}
