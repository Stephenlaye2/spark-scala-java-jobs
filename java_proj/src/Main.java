import java.lang.reflect.Array;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/*
Write a class with a score() method that accepts an array of dice values (up to 6). Scoring rules are as follows:
•	A single one (100)
•	A single five (50)
•	Triple ones [1,1,1] (1000)
•	Triple twos [2,2,2] (200)
•	Triple threes [3,3,3] (300)
•	Triple fours [4,4,4] (400)
•	Triple fives [5,5,5] (500)
•	Triple sixes [6,6,6] (600)
Note that the scorer should work for any number of dice up to 6.
•	Four-of-a-kind (Multiply Triple Score by 2)
•	Five-of-a-kind (Multiply Triple Score by 4)
•	Six-of-a-kind (Multiply Triple Score by 8)
•	Three Pairs [2,2,3,3,4,4] (800)
•	Straight [1,2,3,4,5,6] (1200)
 */


public class Main {
    public static void main(String[]args){
        int[] dice_nums = {1,2,3,4,5,6};
        System.out.println("number of score: " + score(dice_nums));
    }
    public static int score(int[] dice_nums) {
        Arrays.sort(dice_nums);
        int num_of_score = 0;

        int[] count = new int[7];

        for (int i : dice_nums) {
            count[i]++;
        }

//      Scoring rule calculation
        for (int i = 1; i <= 6; i++) {
            int cnt = count[i];

//            Checking for single ones
            if (cnt >= 3) {
                if (i == 1) num_of_score += 1000;
                else num_of_score += i * 100;
                cnt -= 3;
            }

//            Checking for single and fives ones
            if (i == 1) num_of_score += cnt * 100;
            else if (i == 5) num_of_score += cnt * 50;

//            Four, five and six of a kind check
            if (cnt >= 4) {
                if (i == 1) num_of_score = 1000;
                else num_of_score = i * 100;
                cnt -= 3;
            }
            if (cnt == 3) {
                if (i == 1) num_of_score = 1000;
                else num_of_score = i * 100;
            }
//            Three pairs check
            if(Arrays.equals(dice_nums, new int[]{dice_nums[0],dice_nums[0],dice_nums[1],dice_nums[2],dice_nums[2]}))
                num_of_score += 800;

//            Straight pairs check
            if (Arrays.equals(dice_nums, new int[]{1, 2, 3, 4, 5, 6})) num_of_score += 1200;
        }
        return num_of_score;
    }
}