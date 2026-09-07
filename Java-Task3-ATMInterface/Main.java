import java.util.Scanner;

public class Main {

    static double balance = 10000.00;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int pin = 1234;

        System.out.println("===== ATM INTERFACE =====");

        System.out.print("Enter PIN: ");
        int enteredPin = scanner.nextInt();

        if (enteredPin != pin) {
            System.out.println("Invalid PIN!");
            return;
        }

        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.printf("Current Balance: ₹%.2f%n", balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double deposit = scanner.nextDouble();

                    if (deposit > 0) {
                        balance += deposit;
                        System.out.printf("Deposit successful!%n");
                        System.out.printf("New Balance: ₹%.2f%n", balance);
                    } else {
                        System.out.println("Invalid amount!");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdraw = scanner.nextDouble();

                    if (withdraw <= 0) {
                        System.out.println("Invalid amount!");
                    } else if (withdraw > balance) {
                        System.out.println("Insufficient balance!");
                    } else {
                        balance -= withdraw;
                        System.out.printf("Withdrawal successful!%n");
                        System.out.printf("Remaining Balance: ₹%.2f%n", balance);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        scanner.close();
    }
                               }
