package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.AccountIsLockedException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordContentException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordLengthException;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        List<BankAccount> accounts = initAccounts();
        TerminalMessage msg = new TerminalMessage();
        TerminalImpl terminal = new TerminalImpl(new TerminalServer(accounts.get(0)), msg);
        Scanner scanner = new Scanner(System.in);
        msg.show("\tТерминал банка \"UBS\" v1.0.2 ");
        msg.show("Введите пин-код:");
        LinkedList<String> password = new LinkedList<>();
        do {
            try {
                String number = scanner.next();
                if (number.length() > 1) {
                    msg.show("Ввод осуществляется по одной цифре");
                    continue;
                }

                password.add(number);
                terminal.login(String.join("", password));
                break;
            } catch (InvalidPasswordException e) {
                msg.show("Введите пин-код:");
                password.clear();
            } catch (InvalidPasswordContentException e) {
                password.removeLast();
            } catch (InvalidPasswordLengthException e) {
//                msg.show("Пин-код должен содержать 4 цифры");
            } catch (AccountIsLockedException e) {
                password.clear();
            }
        } while (true);

        int choice;

        do {
            msg.show("\tМеню:\n1 - Проверить состояние счета \n2 - Положить деньги\n3 - Снять деньги\n0 - Выход");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        terminal.printBalance();
                        break;
                    case 2:
                        msg.show("Введите сумму:");
                        if (scanner.hasNextBigDecimal()) {
                            var money = scanner.nextBigDecimal();
                            terminal.addMoney(money);
                            terminal.printBalance();
                        } else {
                            scanner.next();
                            msg.show("Введена неверная сумма.");
                        }
                        break;
                    case 3:
                        msg.show("Введите сумму:");
                        if (scanner.hasNextBigDecimal()) {
                            var money = scanner.nextBigDecimal();
                            terminal.withdrawMoney(money);
                            terminal.printBalance();
                        } else {
                            scanner.next();
                            msg.show("Введена неверная сумма.");
                        }
                        break;
                }
            }
        } while (true);
    }

    private static List<BankAccount> initAccounts() {
        try {
            return List.of(
                    new BankAccount("Josh", BigDecimal.valueOf(3000), PinValidator.toPasswordHash(String.valueOf(1234))),
                    new BankAccount("Bill", BigDecimal.valueOf(300), PinValidator.toPasswordHash(String.valueOf(8765))),
                    new BankAccount("Michael", BigDecimal.valueOf(20000), PinValidator.toPasswordHash(String.valueOf(4289))),
                    new BankAccount("Andrew", BigDecimal.valueOf(9999999.99), PinValidator.toPasswordHash(String.valueOf(4289)))
            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
