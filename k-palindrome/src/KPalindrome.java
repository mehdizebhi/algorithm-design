
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KPalindrome {

    private String input;
    private ArrayList<Character> listInput;
    private ArrayList<Character> listOutput;
    private String output;
    private int K;
    static Scanner in = new Scanner(System.in);

    public void run() {

        listInput = new ArrayList<>();
        listOutput = new ArrayList<>();

        /*
        As long as we have input, we receive the inputs using the input function
         */
        while (in.hasNextLine()) {
            int k = in.nextInt();
            String str = in.nextLine();
            if (str.length() == 0) {
                break;
            } else {
                listInput.clear();
                listOutput.clear();

                input(k, str);
                output = "";
                KPalindromeGreedy(1);

                // convert listOutput to string wihtout "[" and "]" and "," and " "
                output = listOutput.toString().replace("[", "");
                output = output.replace("]", "");
                output = output.replace(" ", "");
                output = output.replace(",", "");

                /*The resulting output changes (output: convert the input string
                  to a Palindrome string with the least amount of displacement) with input.
                  Displacement: The ceiling of change is divided into two ( ceil(numOfChanges / 2) )
                 */
                int displacement = (int) Math.ceil(numOfChanges(input, output) / 2.0);

                //Print output if our output string was a Palindrome and the number of move is less than the maximum (K)
                if (displacement <= K && isPalindrome(output)) {
                    System.out.println(output);
                } else {
                    System.out.println("NO");
                }
            }
        }

    }

    /*
    recursive function with greedy selection
    Greedy selection: Each time we choose a character from the string that leads to the Palindrome of the output
     */
    private void KPalindromeGreedy(int num) {

        ArrayList<Character> str = new ArrayList<>();
        String str2 = "";
        int mid = 0;
        boolean flag = false;

        if (num > input.length()) {
            return;
        }

        for (int i = 0; i < listInput.size(); i++) {

            char ch = listInput.get(i);
            str = listOutput;

            //Place each character somewhere in the output that Palindrome the output
            for (int j = 0; j <= listOutput.size(); j++) {

                str.add(j, ch);

                // convert str to string wihtout "[" and "]" and "," and " "
                str2 = str.toString().replace("[", "");
                str2 = str2.replace("]", "");
                str2 = str2.replace(" ", "");
                str2 = str2.replace(",", "");

                //Whenever the added character leads to the output Palindrome, we save it in the output
                if (isPalindrome(str2) || (i == listInput.size() - 1 && j == listOutput.size() - 1)) {
                    listOutput = str;
                    listInput.remove(i);
                    flag = true;
                    break;
                }
                str.remove(j);
            }
            if (flag) {
                break;
            }
        }
        KPalindromeGreedy(num + 1);
    }

    //Returns the number of changes between you strings
    private int numOfChanges(String str1, String str2) {
        int num = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                num++;
            }
        }
        return num;
    }

    //is Palindrome this string?
    private boolean isPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;

        while (j > i) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private void input(int k, String str) {
        this.K = k;
        input = str.substring(1);
        for (int i = 0; i < input.length(); i++) {
            listInput.add(input.charAt(i));
        }
    }

    public static void main(String[] args) {
        KPalindrome test = new KPalindrome();
        test.run();
    }
}
