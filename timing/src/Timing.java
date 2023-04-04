
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Timing {

    static Scanner in = new Scanner(System.in);
    private List<Integer> starts;                       //input
    private List<Integer> startsCopy;
    private List<Integer> lengths;                      //input
    private List<Integer> lengthsCopy;
    private int T;                                      //input
    private List<Integer> successfulProcessing;         //output
    private int time;
    private int maxTime;

    public void run() {
        starts = new ArrayList<>();
        startsCopy = new ArrayList<>();
        lengths = new ArrayList<>();
        successfulProcessing = new ArrayList<>();
        lengthsCopy = new ArrayList<>();

        input();
        maxTime();
        this.time = starts.get(0);  // start time = Time to start the first processing

        processScheduling(0);
        
        System.out.println(successfulProcessing);
        System.out.println(successfulProcessing.size());
    }

    //Greedy algorithm based on selecting the shortest processing length : 
    private void processScheduling(int index) {

        if (time >= maxTime || index == -1) {
            return;
        }

        int num = lengths.get(index);
        /*
        For each process, one unit must be processed each time,
        and if there was a process in the next unit of time
        that was shorter than the continuation of the current process,
        the next process will replace the current one.
         */
        for (int i = 0; i < num; i++) {

            System.out.println("time :" + time);
            System.out.println("test : " + index);

            int start = starts.get(index) + 1;
            int length = lengths.get(index) - 1;
            starts.set(index, start);
            lengths.set(index, length);
            time++;

            int nextPick = searchStartingTime(time);
            System.out.println("nextPick : " + nextPick);
            System.out.println("len : " + length);
            System.out.println("next time :" + time);
            System.out.println("-------------------------------------");

            //If there was a process that was shorter than the current continuation :
            if (nextPick != -1 && lengths.get(nextPick) < length) {
                processScheduling(nextPick);
                break;

            } else if (/*time - startsCopy.get(index) > T || */ lengthsCopy.get(index) > T && nextPick != -1) {
                processScheduling(nextPick);
                break;

            } else if (length == 0) {
                /*
                When a process is finished,
                if it was less than or equal to T from the start,
                that process was successful.
                 */
                if (time - startsCopy.get(index) <= T) {
                    successfulProcessing.add(index);
                }

                int end = start + length;
                nextPick = minLengthBeforeTime(end);
                if (nextPick != Integer.MAX_VALUE) {
                    processScheduling(nextPick);
                }
                break;
            }
        }
    }

    //maxTime = Total all processing lengths
    private void maxTime() {
        this.maxTime = 0;
        for (int i = 0; i < lengths.size(); i++) {
            this.maxTime += lengths.get(i);
        }
    }

    //Search start time (can be binary search for better speed but it does not matter here)
    private int searchStartingTime(int time) {
        for (int i = 0; i < startsCopy.size(); i++) {
            if (startsCopy.get(i) == time) {
                return i;
            }
        }
        return -1;
    }

    //Search for the start time of a process before the given time
    private int minLengthBeforeTime(int time) {
        int min = Integer.MAX_VALUE;
        int indexMin = 0;
        for (int i = 0; i < lengths.size(); i++) {
            int x = lengths.get(i);
            int start = starts.get(i);
            if (x <= min && x != 0 && start <= time && this.time - startsCopy.get(i) <= T) {
                min = x;
                indexMin = i;
            }
        }
        return indexMin;
    }

    //Read input by private input function
    public void input() {
        this.T = Integer.parseInt(in.nextLine());
        while (in.hasNextLine()) {
            String str = in.nextLine();
            if (str.length() == 0) {
                break;
            } else {
                input(str);
            }
        }
    }

    //Read each line and convert it to an integer
    private void input(String str) {
        String[] strArray = str.split(" ");
        int start = Integer.parseInt(strArray[0]);
        int length = Integer.parseInt(strArray[1]);
        starts.add(start);
        startsCopy.add(start);
        lengths.add(length);
        lengthsCopy.add(length);
    }

    public static void main(String[] args) {
        Timing test = new Timing();
        test.run();
    }
}
