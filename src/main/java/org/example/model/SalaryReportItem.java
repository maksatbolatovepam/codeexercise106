package org.example.model;

public class SalaryReportItem {
  private final Employee employee;
  private final double difference;

  public SalaryReportItem(Employee employee, double difference) {
    this.employee = employee;
    this.difference = difference;
  }

  public Employee getEmployee() {
    return employee;
  }

  public double getDifference() {
    return difference;
  }
}
