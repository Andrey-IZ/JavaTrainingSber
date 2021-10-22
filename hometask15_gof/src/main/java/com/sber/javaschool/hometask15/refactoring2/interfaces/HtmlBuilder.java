package com.sber.javaschool.hometask15.refactoring2.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HtmlBuilder {
    StringBuilder buildReport(ResultSet results) throws SQLException;
}
