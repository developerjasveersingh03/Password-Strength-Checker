
import java.util.Scanner;
import java.security.SecureRandom;

public class Main {

    static SecureRandom random = new SecureRandom();

    public static boolean hasUppercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                return true;
            }
        }
        return false;
    }

    public static boolean hasLowercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                return true;
            }
        }
        return false;
    }

    public static boolean hasDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSpecialCharacter(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    public static void checkPasswordStrength(String password) {

        boolean uppercase = hasUppercase(password);
        boolean lowercase = hasLowercase(password);
        boolean digit = hasDigit(password);
        boolean special = hasSpecialCharacter(password);
        boolean longEnough = password.length() >= 8;

        System.out.println("\n--- Password Analysis ---");

        System.out.println("Length: "
                + (longEnough ? "OK" : "Missing (minimum 8)"));

        System.out.println("Uppercase: "
                + (uppercase ? "OK" : "Missing"));

        System.out.println("Lowercase: "
                + (lowercase ? "OK" : "Missing"));

        System.out.println("Digit: "
                + (digit ? "OK" : "Missing"));

        System.out.println("Special Character: "
                + (special ? "OK" : "Missing"));

        if (longEnough && uppercase && lowercase
                && digit && special) {
            System.out.println("\nPassword Strength: STRONG");
        } else if (longEnough
                && (uppercase || lowercase)
                && (digit || special)) {
            System.out.println("\nPassword Strength: MEDIUM");
        } else {
            System.out.println("\nPassword Strength: WEAK");
        }
    }

    public static String generatePassword(int length) {

        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "@#$!%&*";

        String allCharacters =
                uppercase + lowercase + digits + special;

        StringBuilder password = new StringBuilder();

        password.append(
                uppercase.charAt(random.nextInt(uppercase.length())));

        password.append(
                lowercase.charAt(random.nextInt(lowercase.length())));

        password.append(
                digits.charAt(random.nextInt(digits.length())));

        password.append(
                special.charAt(random.nextInt(special.length())));

        while (password.length() < length) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }

        for (int i = password.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }

        return password.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (choice != 3) {


            System.out.println("  PASSWORD STRENGTH CHECKER");

            System.out.println("1. Check Password Strength");
            System.out.println("2. Generate Password");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter 1, 2 or 3.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                System.out.print("Enter your password: ");
                String password = sc.nextLine();

                if (password.isEmpty()) {
                    System.out.println("Password cannot be empty.");
                } else {
                    checkPasswordStrength(password);
                }

            } else if (choice == 2) {

                System.out.print("Enter password length (8-64): ");

                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    sc.nextLine();
                    continue;
                }

                int length = sc.nextInt();
                sc.nextLine();

                if (length < 8 || length > 64) {
                    System.out.println(
                            "Password length must be between 8 and 64.");
                } else {
                    String generated = generatePassword(length);

                    System.out.println(
                            "\nGenerated Password: " + generated);

                    System.out.println(
                            "Strength Analysis:");

                    checkPasswordStrength(generated);
                }

            } else if (choice == 3) {

                System.out.println(
                        "Thank you for using Password Checker!");

            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
