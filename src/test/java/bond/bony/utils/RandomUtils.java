package bond.bony.utils;

import java.lang.reflect.Array;
import java.util.Random;

public class RandomUtils {
    public static int getRandomInt(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt((max - min)) + min;
    }

    public static String getRandomMonth(){
        String[] m = {"January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"};
        return m[getRandomInt(0,11)];
    }
}
