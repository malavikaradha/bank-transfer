package com.example.banking.service;

import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private double balance = 1000.00;

    public synchronized double deposit(double amount) {
        validateAmount(amount);
        balance += amount;
        return balance;
    }

    public synchronized double withdraw(double amount) {
        validateAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException(String.format(
                    "Insufficient funds. Available balance is %.2f.", balance));
        }
        balance -= amount;
        return balance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    private void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number.");
        }
    }
}
