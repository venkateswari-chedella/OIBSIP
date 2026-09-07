import java.util.Scanner;

public class OnlineExamination {

    static Scanner sc = new Scanner(System.in);

    static String username = "student";
    static String password = "1234";

    static int score = 0;

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       ONLINE EXAMINATION");
        System.out.println("=================================");

        // Login
        System.out.print("Enter Username: ");
        String user = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (!user.equals(username) || !pass.equals(password)) {
            System.out.println("\nInvalid username or password!");
            System.out.println("Examination terminated.");
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + user + "!");

        System.out.println("\nInstructions:");
        System.out.println("1. There are 5 questions.");
        System.out.println("2. Each correct answer carries 1 mark.");
        System.out.println("3. Enter A, B, C or D.");
        System.out.println("4. No negative marking.");

        System.out.print("\nPress Enter to start the examination...");
        sc.nextLine();

        // Question 1
        System.out.println("\nQ1. Which language is mainly used for Android development?");
        System.out.println("A. HTML");
        System.out.println("B. Java");
        System.out.println("C. SQL");
        System.out.println("D. CSS");

        System.out.print("Your answer: ");
        String ans = sc.nextLine();

        if (ans.equalsIgnoreCase("B")) {
            score++;
        }

        // Question 2
        System.out.println("\nQ2. Which keyword is used to create a class in Java?");
        System.out.println("A. class");
        System.out.println("B. create");
        System.out.println("C. new");
        System.out.println("D. object");

        System.out.print("Your answer: ");
        ans = sc.nextLine();

        if (ans.equalsIgnoreCase("A")) {
            score++;
        }

        // Question 3
        System.out.println("\nQ3. Which method is the entry point of a Java program?");
        System.out.println("A. start()");
        System.out.println("B. run()");
        System.out.println("C. main()");
        System.out.println("D. execute()");

        System.out.print("Your answer: ");
        ans = sc.nextLine();

        if (ans.equalsIgnoreCase("C")) {
            score++;
        }

        // Question 4
        System.out.println("\nQ4. Which symbol is used to end a Java statement?");
        System.out.println("A. :");
        System.out.println("B. .");
        System.out.println("C. ,");
        System.out.println("D. ;");

        System.out.print("Your answer: ");
        ans = sc.nextLine();

        if (ans.equalsIgnoreCase("D")) {
            score++;
        }

        // Question 5
        System.out.println("\nQ5. Which data type is used to store whole numbers?");
        System.out.println("A. float");
        System.out.println("B. int");
        System.out.println("C. char");
        System.out.println("D. boolean");

        System.out.print("Your answer: ");
        ans = sc.nextLine();

        if (ans.equalsIgnoreCase("B")) {
            score++;
        }

        // Result
        System.out.println("\n=================================");
        System.out.println("          EXAM RESULT");
        System.out.println("=================================");

        System.out.println("Student Name : " + user);
        System.out.println("Total Marks  : 5");
        System.out.println("Your Score   : " + score);

        double percentage = (score / 5.0) * 100;

        System.out.println("Percentage   : " + percentage + "%");

        if (score >= 3) {
            System.out.println("Result       : PASS");
        } else {
            System.out.println("Result       : FAIL");
        }

        System.out.println("=================================");
        System.out.println("Thank you for attending the exam!");

        sc.close();
    }
    }
