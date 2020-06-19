import static java.lang.Math.*;


public class fibonacci {
    public static int of (int n) {
        return (int)((double)1/(sqrt(5)) * (pow((1 + sqrt(5))/2, n + 1) - pow((1 - sqrt(5))/2, n + 1)));
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(fibonacci.of(i));
        }
    }
}
