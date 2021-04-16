package analyser.testApps;

import java.time.Instant;
import java.util.Random;

public class AppFailingOneOutOfTwoTimes {

    public static void main(String[] args) throws Exception{
	// write your code here
        Random random = new Random();
        boolean value = random.nextBoolean();
        System.out.println(value);
        if(value){
            System.out.println("Hi");
        }
        else {
            Thread.sleep(1000000);
            System.out.println(Instant.now());
        }
    }


}
