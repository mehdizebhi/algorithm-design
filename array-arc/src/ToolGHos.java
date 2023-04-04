import java.util.Scanner;

public class ToolGHos {

    static Scanner in = new Scanner(System.in);
    private int[] array;
    private int arcLength;

    public void run() {
        input();
        arcLength = largestArcArray(0, array.length - 1);
        System.out.println(arcLength);
    }

    //recursive function
    //output : return length largest arc
    private int largestArcArray(int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) { //End condition
            return 1;
        }

        int mid = (leftIndex + rightIndex) / 2;
        int leftArc = largestArcArray(leftIndex, mid); //The first mode in the left half
        int rightArc = largestArcArray(mid + 1, rightIndex); //The second mode in the right half

        //The third mode between the two halves :
        int leftMax = (int) Double.NEGATIVE_INFINITY;
        int leftMaxIndex = 0;
        for (int i = mid; i >= leftIndex; i--) {
            if (array[i] >= leftMax) {
                leftMax = array[i];
                leftMaxIndex = i;
            }
        }
        int rightMax = (int) Double.NEGATIVE_INFINITY;
        int rightMaxIndex = 0;
        for (int i = mid + 1; i <= rightIndex; i++) {
            if (array[i] >= rightMax) {
                rightMax = array[i];
                rightMaxIndex = i;
            }
        }

        int mergeArc = ValidArc(leftMaxIndex, rightMaxIndex, mid);

        return max(leftArc, rightArc, mergeArc);
    }

    //Check if the third mode is selected correctly or not. If something goes wrong, return the correct part size.
    //output : return length merge arc
    private int ValidArc(int left, int right, int mid) {
        int start = array[left];
        int end = array[right];

        for (int i = mid + 1; i < right; i++) {
            if (start < array[i] && array[i] != end) {
                right = i;
            }
        }
        for (int i = mid; i > left; i--) {
            if (end < array[i] && array[i] != start) {
                left = i;
            }
        }
        
        return right - left + 1;
    }

    //Maximum between 3 number
    private int max(int num1, int num2, int num3) {
        return Math.max(Math.max(num1, num2), num3);
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
        ToolGHos test = new ToolGHos();
        test.run();
    }
}
