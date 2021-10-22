package com.sber.javaschool.hometask15.refactoring2.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface ReportDao {
    ResultSet getSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws SQLException;
}
