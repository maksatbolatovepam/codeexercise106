package org.example.model;

public class ReportingLineCountReportItem {
  private final Employee employee;
  private final int reportingLineCount;

  public ReportingLineCountReportItem(Employee employee, int reportingLineCount) {
    this.employee = employee;
    this.reportingLineCount = reportingLineCount;
  }

  public Employee getEmployee() {
    return employee;
  }

  public int getReportingLineCount() {
    return reportingLineCount;
  }
}
