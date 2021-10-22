package com.sber.javaschool.hometask15.refactoring2.interfaces;

import javax.mail.internet.MimeMessage;

public interface MessageBuilder {
    MimeMessage createSalaryReport(StringBuilder resultingHtml) throws Exception;
}
