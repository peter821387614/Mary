import java.util.*;

public class GuessNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[] answer = new int[4];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = random.nextInt(10);
            for (int j = 0; j < i; j++) {
                if (answer[i] == answer[j]) {
                    i--;
                    break;
                }
            }
        }

        int guessCount = 0;
        while (guessCount < 10) {
            System.out.print("Please enter your guess (4 digits): ");
            String guessString = scanner.nextLine();
            if (guessString.length() != 4) {
                System.out.println("Invalid guess. Please enter 4 digits.");
                continue;
            }

            int[] guess = new int[4];
            for (int i = 0; i < guess.length; i++) {
                guess[i] = guessString.charAt(i) - '0';
            }

            int A = 0, B = 0;
            for (int i = 0; i < guess.length; i++) {
                if (guess[i] == answer[i]) {
                    A++;
                } else {
                    for (int j = 0; j < answer.length; j++) {
                        if (guess[i] == answer[j]) {
                            B++;
                            break;
                        }
                    }
                }
            }

            System.out.println(A + "A" + B + "B");
            if (A == 4) {
                System.out.println("Congratulations! You have guessed the number.");
                break;
            }
            guessCount++;
        }

        if (guessCount == 10) {
            System.out.println("Sorry, you have used up all your guesses. The answer is " + Arrays.toString(answer));
        }
    }
}