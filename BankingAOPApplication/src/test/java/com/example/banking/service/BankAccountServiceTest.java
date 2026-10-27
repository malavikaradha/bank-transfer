package com.example.banking.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {
    private BankAccountService account;

    @BeforeEach
    void setUp() { account = new BankAccountService(); }

    @Test
    void startsWithBalanceOf1000() { assertEquals(1000.00, account.getBalance(), 0.001); }

    @Test
    void depositUpdatesBalance() {
        assertEquals(1250.00, account.deposit(250.00), 0.001);
        assertEquals(1250.00, account.getBalance(), 0.001);
    }

    @Test
    void withdrawalUpdatesBalance() {
        assertEquals(700.00, account.withdraw(300.00), 0.001);
    }

    @Test
    void rejectsWithdrawalAboveBalanceWithoutChangingBalance() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> account.withdraw(1001.00));
        assertTrue(error.getMessage().contains("Insufficient funds"));
        assertEquals(1000.00, account.getBalance(), 0.001);
    }

    @Test
    void rejectsZeroOrNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
        assertEquals(1000.00, account.getBalance(), 0.001);
    }

    @Test
    void rejectsZeroOrNegativeWithdrawal() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-10));
        assertEquals(1000.00, account.getBalance(), 0.001);
    }
}
