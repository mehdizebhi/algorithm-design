/**
 * Description:
 * In this implementation, each input is considered as an object that is 
 * stored in a matrix. The number of rows in all matrices is 2. The values 
 * of each input are in the first row and the initial indices are stored in 
 * the second row. To get the distances, we use the second row and get 
 * the difference between the final index of the matrix and the value in 
 * the second row.
 */

import java.util.Scanner;

public class SPACE {

    static Scanner in = new Scanner(System.in);
    private int[][] array; //The first row is the input values and the second row is the index of each value ([n][2])
    private int sumIndex;

    public void run() {
        input();
        spaceIndex();
        System.out.println(sumIndex);
    }

    private void spaceIndex() {
        int[][] sortArray;
        sumIndex = 0;
        sortArray = mergeSort(0, array.length - 1); //mergeHalves with Calculate distances
    }

    //recursive function (Divide and conquer algorithm)
    private int[][] mergeSort(int start, int end) {
        int[][] B = new int[end - start + 1][2]; //creat B array [n][2]
        if (start == end) {
            B[0][0] = array[start][0]; //value
            B[0][1] = array[start][1]; //index
            return B;
        }
        int mid = (start + end) / 2;
        int[][] leftHalf = mergeSort(start, mid);
        int[][] rightHalf = mergeSort(mid + 1, end);
        mergeHalves(leftHalf, rightHalf, B);
        return B;
    }

    //mergeHalves with Calculate distances
    private int[][] mergeHalves(int[][] leftHalf, int[][] rightHalf, int[][] B) {
        int i = 0; //index of leftHalf
        int j = 0; //index of rightHalf
        int sumLen = leftHalf.length + rightHalf.length;

        for (int k = 0; k < B.length; k++) {
            if (i >= leftHalf.length) {
                B[k][0] = rightHalf[j][0];
                B[k][1] = rightHalf[j][1];
                //Check Last time merged : 
                if (sumLen == array.length) {
                    sumIndex += Math.abs(k - B[k][1]);
                }
                j++;
            } else if (j >= rightHalf.length || leftHalf[i][0] <= rightHalf[j][0]) {
                B[k][0] = leftHalf[i][0];
                B[k][1] = leftHalf[i][1];
                //Check Last time merged : 
                if (sumLen == array.length) {
                    sumIndex += Math.abs(k - B[k][1]);
                }
                i++;
            } else {
                B[k][0] = rightHalf[j][0];
                B[k][1] = rightHalf[j][1];
                //Check Last time merged : 
                if (sumLen == array.length) {
                    sumIndex += Math.abs(k - B[k][1]);
                }
                j++;
            }
        }
        return B;
    }

    //Get input and value in array
    private void input() {
        String str = in.nextLine();
        String[] str2;
        str2 = str.split(" ");
        array = new int[str2.length][2];
        for (int i = 0; i < str2.length; i++) {
            //The first row ([i][0]) is the input values and the second row ([i][1]) is the index of each value
            array[i][0] = Integer.parseInt(str2[i]);
            array[i][1] = i;
        }
    }

    public static void main(String[] args) {
        SPACE test = new SPACE();
        test.run();
    }
}
