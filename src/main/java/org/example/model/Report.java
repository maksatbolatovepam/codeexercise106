package org.example.model;

import java.util.List;

public class Report {
  private final List<SalaryReportItem> managersWithLowSalary;
  private final List<SalaryReportItem> managersWithHighSalary;
  private final List<ReportingLineCountReportItem> longReportingLine;

  public Report(List<SalaryReportItem> managersWithLowSalary,
                List<SalaryReportItem> managersWithHighSalary,
                List<ReportingLineCountReportItem> longReportingLine) {
    this.managersWithLowSalary = managersWithLowSalary;
    this.managersWithHighSalary = managersWithHighSalary;
    this.longReportingLine = longReportingLine;
  }

  public List<SalaryReportItem> getManagersWithLowSalary() {
    return managersWithLowSalary;
  }

  public List<SalaryReportItem> getManagersWithHighSalary() {
    return managersWithHighSalary;
  }

  public List<ReportingLineCountReportItem> getLongReportingLine() {
    return longReportingLine;
  }

  public void print() {
    StringBuilder report = new StringBuilder();
    if (!managersWithLowSalary.isEmpty()) {
      report.append("Managers earn less than they should:").append(System.lineSeparator());
      for (SalaryReportItem each : managersWithLowSalary) {
        Employee employee = each.getEmployee();
        report.append(employee.getFirstName()).append(" ").append(employee.getLastName())
            .append(". ").append("Current salary: ").append(employee.getSalary())
            .append(". Should earn at least ").append(each.getDifference()).append(" more.")
            .append(System.lineSeparator());
      }
    }

    if (!managersWithHighSalary.isEmpty()) {
      report.append(System.lineSeparator())
          .append("Managers earn more than they should:").append(System.lineSeparator());
      for (SalaryReportItem each : managersWithHighSalary) {
        Employee employee = each.getEmployee();
        report.append(employee.getFirstName()).append(" ").append(employee.getLastName())
            .append(". ").append("Current salary: ").append(employee.getSalary())
            .append(". Should earn at least ").append(each.getDifference()).append(" less.")
            .append(System.lineSeparator());
      }
    }

    if (!longReportingLine.isEmpty()) {
      report.append(System.lineSeparator())
          .append("Employees have a reporting line which is too long:")
          .append(System.lineSeparator());
      for (ReportingLineCountReportItem each : longReportingLine) {
        Employee employee = each.getEmployee();
        report.append(employee.getFirstName()).append(" ").append(employee.getLastName())
            .append(". ").append("Reporting line count: ").append(each.getReportingLineCount())
            .append(System.lineSeparator());
      }
    }

    System.out.println(report);
  }
}
