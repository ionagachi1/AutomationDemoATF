package utilities;

import java.util.Random;

public class GenerateRandom {

    public String addRandomSuffix(String employeeUsername)
    {
        Random rand = new Random();
        int randomSuffix  = rand.nextInt(1000);
        return employeeUsername+randomSuffix;
    }
}
