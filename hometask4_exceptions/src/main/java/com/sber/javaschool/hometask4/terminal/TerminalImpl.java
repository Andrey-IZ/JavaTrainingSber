package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.*;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    IMessage message;

    public TerminalImpl(TerminalServer server, IMessage msg) {
        this.server = server;
        pinValidator = new PinValidator(4, 10);
        message = msg;
    }

    @Override
    public BigDecimal checkBalance() {
        return server.checkBalance();
    }

    public void printBalance() {
        message.show("Баланс счета: " + checkBalance());
    }

    @Override
    public void addMoney(BigDecimal money) {
        try {
            server.addMoney(money);
        } catch (TerminalServerException e) {
            message.show(MessageFormat.format("Ошибка: {0}", e.getMessage()));
        } catch (IncorrectAmountOfMoney e) {
            message.show(MessageFormat.format("Ошибка: \"Сумма должна быть кратна {0}\"", e.getMultiply()));
        }
    }

    @Override
    public void withdrawMoney(BigDecimal money) {
        try {
            server.withdrawMoney(money);
        } catch (NotEnoughMoneyException e) {
            message.show("Ошибка: \"На Вашем счёте недостаточно средств\"");
        } catch (IncorrectAmountOfMoney e) {
            message.show(MessageFormat.format("Ошибка: \"Сумма должна быть кратна {0}\"", e.getMultiply()));
        }
    }

    @Override
    public boolean login(String pinCode) throws InvalidPasswordContentException, InvalidPasswordException,
            InvalidPasswordLengthException, AccountIsLockedException {
        try {
            return pinValidator.checkPassword(pinCode, server.getCurAccount().getHashPinCode());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidPasswordContentException e) {
            message.show("Предупреждение, введен не цифровой символ.");
            throw e;
        } catch (AccountIsLockedException e) {
            message.show(MessageFormat.format("Ошибка: Осталось до снятия блокировки {0} c.", e.getRemainingTime()));
            throw e;
        } catch (InvalidPasswordException e) {
            message.show(MessageFormat.format("Предупреждение, введен неверный пин-код! Осталось попыток: {0}", e.getAttempts()));
            throw e;
        }
    }
}
