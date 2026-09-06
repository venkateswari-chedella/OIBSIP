import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int round = 1;
        boolean playAgain = true;

        System.out.println("======================================");
        System.out.println("       NUMBER GUESSING GAME");
        System.out.println("======================================");
        System.out.println("Guess a number between 1 and 100.");
        System.out.println("You have maximum 7 attempts.");

        while (playAgain) {

            int secretNumber = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n---------- ROUND " + round + " ----------");

            while (attempts < 7) {

                System.out.print("Enter your guess: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Enter a number.");
                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                    continue;
                }

                attempts++;

                System.out.println("Attempt: " + attempts + "/7");

                if (guess > secretNumber) {
                    System.out.println("Too High!");
                }
                else if (guess < secretNumber) {
                    System.out.println("Too Low!");
                }
                else {
                    System.out.println("Correct! Congratulations!");
                    System.out.println(
                        "You guessed the number in "
                        + attempts + " attempts."
                    );

                    int roundScore = (8 - attempts) * 10;
                    totalScore += roundScore;

                    System.out.println("Round Score: " + roundScore);

                    guessedCorrectly = true;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\nYou Lost!");
                System.out.println(
                    "The correct number was: " + secretNumber
                );
            }

            System.out.print("\nPlay Again? (yes/no): ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("yes")
                    || answer.equalsIgnoreCase("y")) {

                round++;

            } else {

                playAgain = false;
            }
        }

        System.out.println("\n======================================");
        System.out.println("            GAME SUMMARY");
        System.out.println("======================================");
        System.out.println("Rounds Played: " + round);
        System.out.println("Total Score: " + totalScore);
        System.out.println("Thank you for playing!");
        System.out.println("======================================");

        scanner.close();
    }
                    }
