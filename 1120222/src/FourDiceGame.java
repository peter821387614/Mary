import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FourDiceGame {
    public static void main(String[] args) {       
        playFourDiceGame();
    }

    public static void playFourDiceGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;

        System.out.println("===== 四顆骰子遊戲 =====");
        int betsum=0;

        while (true) {
            System.out.println("當前總分：" + totalScore);
            System.out.print("輸入賭注（輸入0結束遊戲）：");
            
            int bet = scanner.nextInt();
            
            
            if (bet == 0) {
                System.out.println("遊戲結束。最終總分：" + totalScore);
                break;
            }

            // 掷四颗骰子
            int[] dice = new int[4];
            for (int i = 0; i < 4; i++) {
                dice[i] = random.nextInt(6) + 1;
                System.out.println("第 " + (i + 1) + " 顆骰子：" + dice[i]);
            }

            // 排序骰子结果
            Arrays.sort(dice);

            if (isFourOfAKind(dice)) {
                System.out.println("豹子！四顆骰子一樣，獲得賭注：\" + betsum + \"，剩余骰子的总和：\" + totalScore"
                		+ "");
            } else if (isThreeOfAKind(dice)) {
                System.out.println("三顆骰子一樣，重來！");
            } else if (isTwoOfAKind(dice)) {
                int sum = getSumOfRemainingDice(dice);
                totalScore += sum;
                betsum+=bet;
                System.out.println("兩顆骰子一样，獲得賭注：" + betsum + "，剩餘骰子的总和：" + totalScore);
            } else {
                System.out.println("没有相同的骰子，重來！");
            }

            System.out.println();
        }

        scanner.close();
    }

    // 判断是否有四颗骰子一样
    public static boolean isFourOfAKind(int[] dice) {
        return dice[0] == dice[3];
    }

    // 判断是否有三颗骰子一样
    public static boolean isThreeOfAKind(int[] dice) {
        return dice[0] == dice[2] || dice[1] == dice[3];
    }

    // 判断是否有两颗骰子一样
    public static boolean isTwoOfAKind(int[] dice) {
        return dice[0] == dice[1] || dice[1] == dice[2] || dice[2] == dice[3];
    }

    // 计算剩余骰子的总和
    public static int getSumOfRemainingDice(int[] dice) {
        if (dice[0] == dice[1]) {
            return dice[2] + dice[3];
        } else {
            return dice[0] + dice[1];
        }
    }
}
