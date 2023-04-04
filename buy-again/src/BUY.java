
import java.util.Scanner;

public class BUY {

    static Scanner in = new Scanner(System.in);
    private int[] count;
    private int[] p;
    private int T;
    private int[] output;

    public void run() {
        output = new int[100];
        int i = 0;
        /*
        Using the input function, I store the outputs in the output array as long
        as we have input, and after we reach the end of the input, we print the
        value of each output in order.
         */
        while (in.hasNextLine()) {
            String tStr = in.nextLine();
            String pStr = in.nextLine();
            if (tStr.length() == 0 || pStr.length() == 0) {
                break;
            } else {
                input(tStr, pStr);
                output[i] = numberOfBuyToZero();
                i++;
            }
        }

        for (int j = 0; j < i; j++) {
            System.out.println(output[j]);
        }
    }

    /* Dynamic programming algorithm :
    ** Return min {Number of purchases until our money is zero}
    ** Recursive : count[i] = min{1 + count[i - p[j]]}  0 < i <= T , 0 <= j <= n , p[j] <= i 
     */
    private int numberOfBuyToZero() {
        count = new int[T + 1];
        count[0] = 0;
        int x = 0;
        for (int i = 1; i <= T; i++) {
            int min = (int) Double.POSITIVE_INFINITY;
            for (int j = 0; j < p.length; j++) {
                if (i >= p[j] && count[i - p[j]] != -1) {
                    x = 1 + count[i - p[j]];
                } else {
                    x = (int) Double.POSITIVE_INFINITY;
                }
                if (x < min) {
                    min = x;
                }
            }
            if (min == (int) Double.POSITIVE_INFINITY) {
                count[i] = -1;
            } else {
                count[i] = min;
            }
        }
        return count[T];
    }

    /*
    Application input : 
    Each time the call receives the amount of money(T) and the array of 
    goods(p[]) in the form of strings and converts them into integers and 
    values in the program variables.
     */
    private void input(String tStr, String pStr) {
        T = Integer.parseInt(tStr);
        String[] str;
        str = pStr.split(" ");
        p = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            p[i] = Integer.parseInt(str[i]);
        }
    }

    public static void main(String[] args) {
        BUY test = new BUY();
        test.run();
    }
}
