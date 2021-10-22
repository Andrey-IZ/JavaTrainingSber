package com.sber.javaschool.hometask15.refactoring2;

import com.sber.javaschool.hometask15.refactoring2.interfaces.HtmlBuilder;
import com.sber.javaschool.hometask15.refactoring2.interfaces.MailSender;
import com.sber.javaschool.hometask15.refactoring2.interfaces.MessageBuilder;
import com.sber.javaschool.hometask15.refactoring2.interfaces.ReportDao;

import java.time.LocalDate;

/**
 * sources:
 * https://bitbucket.org/agoshkoviv/solid-homework/src/099989b0c76217689c4642242c87c1ac080dfc01/src/main/java/ru/sbt/bit/ood/solid/homework/SalaryHtmlReportNotifier.java?at=master&fileviewer=file-view-default
 */

public class SalaryReportBuilder {
    private final ReportDao reportDao;
    private final HtmlBuilder htmlBuilder;
    private final MailSender mailSender;
    private final MessageBuilder messageBuilder;

    public SalaryReportBuilder(ReportDao connection, HtmlBuilder htmlBuilder, MailSender mailSender,
                               MessageBuilder messageBuilder) {
        this.reportDao = connection;
        this.htmlBuilder = htmlBuilder;
        this.mailSender = mailSender;
        this.messageBuilder = messageBuilder;
    }

    public void sendReport(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws Exception {
        mailSender.sendSalaryReport(messageBuilder.createSalaryReport(htmlBuilder.buildReport(reportDao.getSalaryReport(departmentId, dateFrom, dateTo))));
    }
}
