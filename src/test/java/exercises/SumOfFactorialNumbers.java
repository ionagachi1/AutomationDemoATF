package exercises;

public class SumOfFactorialNumbers {
    public static void main(String[] args) {
        //Integer[] numbers = {1,2,3,4,5,6,7,8,9,10};
        int start = 1;
        int end = 50;

        System.out.println("Prime numbers from " + start + " to " + end + ":");
        for (int number = start; number <= end; number++) {
            if (isPrime(number)) {
                System.out.print(number + " ");
            }
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i=2; i<=number/2; i++){
            if(number % i ==0) {
                return false;
            }
        }
        return true;
    }

}

