
import java.util.Scanner;

public class PalindromeString {

    static Scanner in = new Scanner(System.in);
    private String input;
    private int[][] NumOfInsertChar;      //Number of characters inserted in string from i to j to convert to a Palindrome String
    private int output;                   //final output

    public void run() {
        input();
        output = palindromeStringDP();
        System.out.println(output);
    }

    // C[i][j] = {if i == j : C[i][j] = 0 else if input[i] == input[j] : C[i-1][j-1] = 0 
    //           else : C[i][j] = min(C[i][j-1] + 1, C[i + 1][j] + 1) }
    private int palindromeStringDP() {

        int n = input.length();
        int j = 0;
        int min = 0;
        NumOfInsertChar = new int[n][n];

        for (int L = 0; L < n; L++) {

            for (int i = 0; i < n - L; i++) {
                j = i + L;
                if (i == j) {
                    NumOfInsertChar[i][j] = 0;
                } else if (input.charAt(i) == input.charAt(j)) {
                    NumOfInsertChar[i][j] = NumOfInsertChar[i + 1][j - 1];
                } else {
                    min = NumOfInsertChar[i][j - 1] + 1;
                    if (NumOfInsertChar[i + 1][j] + 1 < min) {
                        min = NumOfInsertChar[i + 1][j] + 1;
                    }
                    NumOfInsertChar[i][j] = min;
                }
            }
        }
        return NumOfInsertChar[0][n - 1];
    }

    //Get input and value in array
    private void input() {
        input = in.nextLine();
    }

    public static void main(String[] args) {
        PalindromeString test = new PalindromeString();
        test.run();
    }
}
