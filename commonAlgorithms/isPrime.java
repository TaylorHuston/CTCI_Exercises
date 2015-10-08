package commonAlgorithms;

/*Evaluates whether a number is prime. O(sqrt(n))*/
public class IsPrime {

    public static boolean compute(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        boolean result = IsPrime.compute(n);
        System.out.println(n + " is prime: " + result);
    }
}
