package com.sber.javaschool.hometask15.refactoring2.interfaces;

import javax.mail.internet.MimeMessage;

public interface MailSender {
    void sendSalaryReport(MimeMessage message);
}
