package Exercise2.Task2;
import java.util.ArrayList;
import java.util.Random;

/**
 * Generates a randomized number and adds it to an array list as one collection
 */
public class RandomizedSum implements Runnable{
    public RandomizedSum(){}

    private int sum = 0;
    private ArrayList<Integer> theSumList = new ArrayList<Integer>();

    public void AddRandomizedNumber(){

        Random rand = new Random();
        //generates random value from 1 - 100
        int randInt = (int) rand.nextInt(1, 100);
        System.out.println("Random number generated: " + randInt);


        //synchronized: if one thread is running this, all other threads have to wait (locked)
        synchronized(this){
            theSumList.add(randInt);
        }
    }

    public int getSum(){
        for (int i = 0; i < theSumList.size(); i++){
            sum += theSumList.get(i);
        }
        return sum;
    }

    /**
     * running the AddRandomizedNumber()
     */
    public void run() {
        AddRandomizedNumber();
    }
}
