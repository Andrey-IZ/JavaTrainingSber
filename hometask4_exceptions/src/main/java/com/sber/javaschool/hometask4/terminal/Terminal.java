package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.AccountIsLockedException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordContentException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordLengthException;

import java.math.BigDecimal;

public interface Terminal {
    BigDecimal checkBalance();

    void addMoney(BigDecimal money);

    void withdrawMoney(BigDecimal money);

    boolean login(String pinCode) throws InvalidPasswordContentException, InvalidPasswordException,
            InvalidPasswordLengthException, AccountIsLockedException;
}
