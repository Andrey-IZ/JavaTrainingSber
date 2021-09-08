package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.IncorrectAmountOfMoney;
import com.sber.javaschool.hometask4.terminal.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        try {
            bankAccount = new BankAccount("Josh", BigDecimal.valueOf(5000), PinValidator.toPasswordHash(String.valueOf(1234)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getBalance() {
        assertEquals(BigDecimal.valueOf(5000), bankAccount.getBalance());
    }

    @Test
    void addBalance() throws IncorrectAmountOfMoney {
        bankAccount.addBalance(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(5200), bankAccount.getBalance());
    }

    @Test
    void addIncorrectBalance() {
        assertThrows(IncorrectAmountOfMoney.class, () ->
                bankAccount.addBalance(BigDecimal.valueOf(123)));
    }

    @Test
    void withdrawMoney() throws NotEnoughMoneyException, IncorrectAmountOfMoney {
        bankAccount.withdrawMoney(BigDecimal.valueOf(300));
        assertEquals(BigDecimal.valueOf(4700), bankAccount.getBalance());
    }

    @Test
    void withdrawIncorrectMoney() {
        assertThrows(IncorrectAmountOfMoney.class, () ->
                bankAccount.withdrawMoney(BigDecimal.valueOf(365)));
    }

}