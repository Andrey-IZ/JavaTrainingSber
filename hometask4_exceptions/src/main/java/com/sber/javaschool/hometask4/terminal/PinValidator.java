package com.sber.javaschool.hometask4.terminal;

import com.sber.javaschool.hometask4.terminal.exceptions.AccountIsLockedException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordContentException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordException;
import com.sber.javaschool.hometask4.terminal.exceptions.InvalidPasswordLengthException;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class PinValidator {
    public static final int ATTEMPTS_DEFAULT = 3;
    private final int blockTimeoutInSec;
    private final int passwordLimit;
    private int attempts;
    private LocalDateTime blockedTime;
    private boolean isLocked;

    public PinValidator(int passwordLimit, int blockTimeoutInSec) {
        this.passwordLimit = passwordLimit;
        attempts = ATTEMPTS_DEFAULT;
        isLocked = false;
        this.blockTimeoutInSec = blockTimeoutInSec;
    }

    public boolean checkPassword(String pinCode, String passwordHash) throws NoSuchAlgorithmException, InvalidPasswordException,
            InvalidPasswordContentException, InvalidPasswordLengthException, AccountIsLockedException {
        if (checkIsLocked()) {
            return false;
        }

        Pattern pattern = Pattern.compile("\\d+");
        if (!pattern.matcher(pinCode).matches()) {
            throw new InvalidPasswordContentException();
        }
        if (pinCode.length() != passwordLimit) {
            throw new InvalidPasswordLengthException();
        }
        if (isPasswordCorrect(pinCode, passwordHash)) {
            return true;
        } else {
            attempts--;
            var attemptsUnchanged = attempts;
            if (attempts == 0) {
                blockedTime = LocalDateTime.now().plusSeconds(blockTimeoutInSec);
                isLocked = true;
                checkIsLocked();
            }
            throw new InvalidPasswordException(attemptsUnchanged);
        }
    }

    private boolean checkIsLocked() throws AccountIsLockedException {
        if (isLocked) {
            var diff = ChronoUnit.SECONDS.between(LocalDateTime.now(), blockedTime);
            if (diff > 0) {
                throw new AccountIsLockedException(diff);
            } else {
                isLocked = false;
                resetAttempts();
            }
        }
        return false;
    }

    public static boolean isPasswordCorrect(String rawCode, String passwordHash) throws NoSuchAlgorithmException {
        return passwordHash.equals(toPasswordHash(rawCode));
    }

    public static String toPasswordHash(String pinCode) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(pinCode.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(md.digest());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new NoSuchAlgorithmException(e);
        }
    }

    public void resetAttempts() {
        attempts = ATTEMPTS_DEFAULT;
    }

}
