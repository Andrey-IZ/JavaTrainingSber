package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.IncorrectAmountOfMoney;
import com.sber.javaschool.hometask4.terminal.exceptions.NotEnoughMoneyException;

import java.math.BigDecimal;

public class BankAccount {
    private String userName;
    private BigDecimal balance;
    private String pinCode;
    private final int multiplyMoney = 100;

    public BankAccount(String userName, BigDecimal balance, String pinCode) {
        this.userName = userName;
        this.balance = balance;
        this.pinCode = pinCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean withdrawMoney(BigDecimal money) throws NotEnoughMoneyException, IncorrectAmountOfMoney {
        if (money.remainder(BigDecimal.valueOf(multiplyMoney)).intValue() == 0) {
            if (balance.compareTo(money) >= 0) {
                balance = balance.subtract(money);
                return true;
            } else {
                throw new NotEnoughMoneyException();
            }
        }
        throw new IncorrectAmountOfMoney(multiplyMoney);
    }

    public boolean addBalance(BigDecimal money) throws IncorrectAmountOfMoney {
        if (money.remainder(BigDecimal.valueOf(100)).intValue() == 0) {
            balance = balance.add(money);
            return true;
        }
        throw new IncorrectAmountOfMoney(multiplyMoney);
    }

    public String getHashPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
