package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.IncorrectAmountOfMoney;
import com.sber.javaschool.hometask4.terminal.exceptions.NotEnoughMoneyException;
import com.sber.javaschool.hometask4.terminal.exceptions.TerminalServerException;

import java.math.BigDecimal;

public class TerminalServer {
    private BankAccount curAccount;

    public TerminalServer(BankAccount curAccount) {
        this.setCurAccount(curAccount);
    }

    public BigDecimal checkBalance() {
        return getCurAccount().getBalance();
    }

    public boolean addMoney(BigDecimal money) throws TerminalServerException, IncorrectAmountOfMoney {
        return getCurAccount().addBalance(money);
    }

    public boolean withdrawMoney(BigDecimal money) throws NotEnoughMoneyException, IncorrectAmountOfMoney {
        return getCurAccount().withdrawMoney(money);
    }

    public BankAccount getCurAccount() {
        return curAccount;
    }

    public void setCurAccount(BankAccount curAccount) {
        this.curAccount = curAccount;
    }
}
