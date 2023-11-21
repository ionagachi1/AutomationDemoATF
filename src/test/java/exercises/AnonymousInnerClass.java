package exercises;

import java.util.ArrayList;
import java.util.List;

public class AnonymousInnerClass {
    public static void main(String[] args){
        Animal myAnimal = new Animal();
        myAnimal.makNoise();

        Animal bigfoot = new Animal(){
            public void makNoise() {
                System.out.println("BU HA HA");
            }
        };
        bigfoot.makNoise();

        Animal bigmamut = new Animal(){
            public void makNoise() {
                System.out.println("BRRRRRRRRRRRRRRR");
            }
        };

        Runnable myRun = new Runnable(){

            public void run() {

            }
        };

    }
}
