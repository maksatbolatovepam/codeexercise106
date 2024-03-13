package org.example.service;

import java.util.ArrayList;
import java.util.List;
import org.example.model.Employee;
import org.example.model.Report;
import org.example.model.ReportingLineCountReportItem;
import org.example.model.SalaryReportItem;

public class Analyzer {
  private final Employee ceo;
  private final List<SalaryReportItem> managersWithLowSalary = new ArrayList<>();
  private final List<SalaryReportItem> managersWithHighSalary = new ArrayList<>();
  private final List<ReportingLineCountReportItem> longReportingLine = new ArrayList<>();

  private Analyzer(Employee ceo) {
    this.ceo = ceo;
  }

  public static Report analyze(Employee ceo) {
    Analyzer analyzer = new Analyzer(ceo);
    return analyzer.analyze();
  }

  private Report analyze() {
    this.analyze(ceo, 0);
    return new Report(managersWithLowSalary, managersWithHighSalary, longReportingLine);
  }

  private void analyze(Employee employee, int reportingLineCount) {
    int totalSalary = 0;
    int count = 0;

    List<Employee> subordinates = employee.getSubordinates();

    for (Employee subordinate : subordinates) {
      totalSalary += subordinate.getSalary();
      count++;
      //recursively check subordinates, increasing reportingLineCount
      this.analyze(subordinate, reportingLineCount + 1);
    }

    //if this employee has subordinates, then check salary
    if (!subordinates.isEmpty()) {
      double avgSalaryOfSubordinates = (double) totalSalary / count;
      double minSalary = 1.2 * avgSalaryOfSubordinates;
      double maxSalary = 1.5 * avgSalaryOfSubordinates;
      if (employee.getSalary() < minSalary) {
        double difference = minSalary - employee.getSalary();
        managersWithLowSalary.add(new SalaryReportItem(employee, difference));
      } else if (employee.getSalary() > maxSalary) {
        double difference = employee.getSalary() - maxSalary;
        managersWithHighSalary.add(new SalaryReportItem(employee, difference));
      }
    }
    if (reportingLineCount > 4) {
      longReportingLine.add(new ReportingLineCountReportItem(employee, reportingLineCount));
    }
  }
}
