package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.AccountIsLockedException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordContentException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TerminalImplTest {
    private BankAccount bankAccount;
    private Terminal terminal;

    @BeforeEach
    void setUp() {
        try {
            bankAccount = new BankAccount("Josh", BigDecimal.valueOf(5000), PinValidator.toPasswordHash(String.valueOf(1234)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        terminal = new TerminalImpl(new TerminalServer(bankAccount), new TerminalMessage());
    }

    @Test
    void checkBalance() {
        assertEquals(terminal.checkBalance(), BigDecimal.valueOf(5000));
    }

    @Test
    void addMoney() {
        terminal.addMoney(BigDecimal.valueOf(500));
        assertEquals(terminal.checkBalance(), BigDecimal.valueOf(5500));
    }

    @Test
    void addIncorrectBalance() {
        initInput(pinToString(1, 2, 3, 4));
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {

            System.setOut(printStream);
            terminal.addMoney(BigDecimal.valueOf(123));

            assertEquals("Ошибка: \"Сумма должна быть кратна 100\"", outputStream.toString().stripTrailing());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void withdrawMoney() {
        terminal.withdrawMoney(BigDecimal.valueOf(200));
        assertEquals(terminal.checkBalance(), BigDecimal.valueOf(4800));
    }

    @Test
    void login() throws InvalidPasswordContentException, InvalidPasswordException, InvalidPasswordLengthException, AccountIsLockedException {
        assertTrue(terminal.login(pinToString(1, 2, 3, 4)));
    }

    @Test
    void loginDeniedOnFirstAttempt() {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {

            System.setOut(printStream);
            assertThrows(InvalidPasswordException.class, () -> terminal.login(pinToString(1, 2, 3, 3)));

            assertEquals("Предупреждение, введен неверный пин-код! Осталось попыток: 2", outputStream.toString().stripTrailing());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initInput(String pinCode) {
        InputStream is = new ByteArrayInputStream(pinCode.getBytes());
        System.setIn(is);
    }

    private static String pinToString(int... pinCode) {
        return Arrays.stream(pinCode).mapToObj(String::valueOf).collect(Collectors.joining(""));
    }

//    private static String pinToInputString(int... pinCode) {
//        return Stream.of(pinCode).map(Object::toString).collect(Collectors.joining("\n"));
//    }
}