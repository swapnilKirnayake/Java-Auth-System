package ui;

import model.User;
import service.AuthService;
import service.PasswordUtil;
import service.ValidatorService; // from FormValidator
import model.UserForm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Register  2.Login  3.Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                if (choice == 1) {
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();

                    System.out.print("Password: ");
                    String password = sc.nextLine().trim();

                    // Reuse validator (dummy phone used)
                    String result = ValidatorService.validate(
                        new UserForm(name, email, "9999999999", password)
                    );

                    if (!result.contains("valid")) {
                        System.out.println(result);
                        continue;
                    }

                    AuthService.register(
                        new User(name, email, PasswordUtil.hash(password))
                    );

                    System.out.println("Registered successfully ✔");

                } else if (choice == 2) {
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();

                    System.out.print("Password: ");
                    String password = sc.nextLine().trim();

                    System.out.println(
                        AuthService.login(email, password)
                        ? "Login successful "
                        : "Invalid credentials "
                    );

                } else if (choice == 3) {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }
}
