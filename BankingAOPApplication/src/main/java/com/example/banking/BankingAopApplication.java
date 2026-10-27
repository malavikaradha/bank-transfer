package com.example.banking;

import com.example.banking.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class BankingAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingAopApplication.class, args);
    }

    @Bean
    CommandLineRunner bankingMenu(BankAccountService account) {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("=== Banking Application (Spring AOP) ===");
                System.out.printf("Starting balance: %.2f%n", account.getBalance());
                while (true) {
                    System.out.println("\n1. Deposit  2. Withdraw  3. Check balance  0. Exit");
                    System.out.print("Choose an option: ");
                    String choice = scanner.nextLine().trim();
                    if ("0".equals(choice)) break;
                    try {
                        switch (choice) {
                            case "1" -> {
                                System.out.print("Deposit amount: ");
                                account.deposit(Double.parseDouble(scanner.nextLine().trim()));
                                System.out.printf("Deposit successful. Balance: %.2f%n", account.getBalance());
                            }
                            case "2" -> {
                                System.out.print("Withdrawal amount: ");
                                account.withdraw(Double.parseDouble(scanner.nextLine().trim()));
                                System.out.printf("Withdrawal successful. Balance: %.2f%n", account.getBalance());
                            }
                            case "3" -> System.out.printf("Current balance: %.2f%n", account.getBalance());
                            default -> System.out.println("Invalid option. Please choose 0, 1, 2, or 3.");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: Please enter a valid numeric amount.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Transaction failed: " + ex.getMessage());
                    }
                }
                System.out.println("Thank you for using the Banking Application.");
            }
        };
    }
}
