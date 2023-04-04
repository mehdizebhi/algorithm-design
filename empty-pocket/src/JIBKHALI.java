
import java.util.*;  
import java.util.ArrayList;
import java.util.Scanner;

public class JIBKHALI {

    static Scanner in = new Scanner(System.in);
    private int T;
    private ArrayList<Integer> obj = new ArrayList<Integer>();

    public JIBKHALI() {
        run();
    }

    //This function finds different states that can be finished
    //(the first case is found to return the true value
    // otherwise it will go back to the end of the state and return the false value if no case is found)
    public boolean isEqualMoney(ArrayList<Integer> object, int money) {
        int wallet = money;
        int n = object.size();
        //Collections.sort(object);
        ArrayList<Integer> index = new ArrayList<>();
        //The first loop is repeated n times to create n object subsets in the second loop.
        for (int i = 0; i < n; i++) {

            //The second loop reduces the subset members one by one from the wallet to the point where the wallet is empty
            for (int j = i; j < n; j++) {
                if (search(object, wallet, index)) {
                    return true;
                }
                if (wallet < object.get(j)) {
                    continue;
                } else {
                    wallet = wallet - object.get(j);
                    index.add(j);
                }
            }
            //Wallet empty checking condition
            if (wallet == 0) {
                return true;
            } else {
                wallet = money;
                index.clear();
                continue;
            }
        }
        return false;
    }

    //search x in ArrayList
    public boolean search(ArrayList<Integer> object, int x, ArrayList<Integer> index) {
        
        for (int i = 0; i < index.size(); i++) {
            if (object.get(index.get(i)) == x) {
                return false;
            }
        }
        
        for (int i = 0; i < object.size(); i++) {
            if (object.get(i) == x) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    public void run() {
        input();
        output();
    }

    //Get question entries
    public void input() {
        String str = in.nextLine();
        T = in.nextInt();
        if (str.endsWith(" ")) {
            str = str.substring(0, str.length() - 1);
        }

        String[] str2;
        str2 = str.split(" ");
        for (int i = 0; i < str2.length; i++) {
            obj.add(Integer.parseInt(str2[i]));
        }
    }

    //Question Output ("YES" or "NO")
    public void output() {
        if (isEqualMoney(obj, T)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        JIBKHALI test = new JIBKHALI();
    }
}
