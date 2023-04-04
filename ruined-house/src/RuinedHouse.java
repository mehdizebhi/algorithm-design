import java.util.Scanner;

public class RuinedHouse {

    static Scanner in = new Scanner(System.in);
    int[] array;
    int firstSameBit;
    int secondSameBit;

    //run (output) function
    public void run() {
        input();
        isTheRuinedHouse(0, array.length - 1);
        System.out.println(firstSameBit + ", " + secondSameBit);
    }

    //recursive function (Divide and conquer algorithm)
    //Value of firstSameBit & secondSameBit (The first and second bits are the same)
    private void isTheRuinedHouse(int start, int end) {
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        int numLeft = mid - start + 1;
        int numRight = end - (mid + 1) + 1;

        // The problem is on the left side of the array
        if ((numLeft % 2 == 0 && array[start] == array[mid]) || (numLeft % 2 == 1 && array[start] != array[mid])) {
            isTheRuinedHouse(start, mid);
        }
        // The problem is on the right side of the array
        else if ((numRight % 2 == 0 && array[mid + 1] == array[end]) || (numRight % 2 == 1 && array[mid + 1] != array[end])) {
            isTheRuinedHouse(mid + 1, end);
        } 
        // The problem is between the left side and right side arrays
        else {
            firstSameBit = mid;
            secondSameBit = mid + 1;
        }
    }

    //Get input and value in array
    private void input() {
        String str = in.nextLine();
        String[] str2;
        str2 = str.split(" ");
        array = new int[str2.length];
        for (int i = 0; i < str2.length; i++) {
            array[i] = Integer.parseInt(str2[i]);
        }
    }

    public static void main(String[] args) {
        RuinedHouse test = new RuinedHouse();
        test.run();
    }
}
