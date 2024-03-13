package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.model.Employee;
import org.example.model.Report;
import org.example.service.Analyzer;
import org.junit.jupiter.api.Test;

class AnalyzerTest {
  @Test
  void testAnalyze_withPositive() {
    // Create a company hierarchy
    Employee ceo = new Employee(1, "Alice", "CEO", 100000, null);
    Employee manager1 = new Employee(2, "Bob", "Manager", 80000, ceo);
    Employee manager2 = new Employee(3, "Charlie", "Manager", 70000, ceo);
    Employee employee1 = new Employee(4, "David", "Employee", 50000, manager1);
    Employee employee2 = new Employee(5, "Eva", "Employee", 60000, manager1);
    Employee employee3 = new Employee(6, "Frank", "Employee", 50000, manager2);

    ceo.addSubordinate(manager1);
    ceo.addSubordinate(manager2);
    manager1.addSubordinate(employee1);
    manager1.addSubordinate(employee2);
    manager2.addSubordinate(employee3);

    // Analyze the hierarchy
    Report report = Analyzer.analyze(ceo);

    // Test results
    assertTrue(report.getManagersWithLowSalary().isEmpty());
    assertTrue(report.getManagersWithHighSalary().isEmpty());
    assertTrue(report.getLongReportingLine().isEmpty());
  }

  @Test
  void testAnalyze_withOneLowSalary() {
    // Create a company hierarchy
    Employee ceo = new Employee(1, "Alice", "CEO", 100000, null);
    Employee manager1 = new Employee(2, "Bob", "Manager", 80000, ceo);
    Employee manager2 = new Employee(3, "Charlie", "Manager", 55000, ceo);
    Employee employee1 = new Employee(4, "David", "Employee", 50000, manager1);
    Employee employee2 = new Employee(5, "Eva", "Employee", 60000, manager1);
    Employee employee3 = new Employee(6, "Frank", "Employee", 50000, manager2);

    // Analyze the hierarchy
    Report report = Analyzer.analyze(ceo);

    // Test results
    assertEquals(1, report.getManagersWithLowSalary().size());
    assertTrue(report.getManagersWithHighSalary().isEmpty());
    assertTrue(report.getLongReportingLine().isEmpty());
  }

  @Test
  void testAnalyze_withOneHighSalary() {
    // Create a company hierarchy
    Employee ceo = new Employee(1, "Alice", "CEO", 130000, null);
    Employee manager1 = new Employee(2, "Bob", "Manager", 80000, ceo);
    Employee manager2 = new Employee(3, "Charlie", "Manager", 70000, ceo);
    Employee employee1 = new Employee(4, "David", "Employee", 50000, manager1);
    Employee employee2 = new Employee(5, "Eva", "Employee", 60000, manager1);
    Employee employee3 = new Employee(6, "Frank", "Employee", 50000, manager2);

    // Analyze the hierarchy
    Report report = Analyzer.analyze(ceo);

    // Test results
    assertTrue(report.getManagersWithLowSalary().isEmpty());
    assertEquals(1, report.getManagersWithHighSalary().size());
    assertTrue(report.getLongReportingLine().isEmpty());
  }

  @Test
  void testAnalyze_withOneLongReportingLine() {
    // Create a company hierarchy
    Employee ceo = new Employee(1, "Alice", "CEO", 120000, null);
    Employee manager1 = new Employee(2, "Bob", "Manager1", 100000, ceo);
    Employee manager2 = new Employee(3, "Charlie", "Manager2", 80000, manager1);
    Employee manager3 = new Employee(4, "David", "Manager3", 60000, manager2);
    Employee manager4 = new Employee(5, "Eva", "Manager4", 50000, manager3);
    Employee employee3 = new Employee(6, "Frank", "Employee", 40000, manager4);

    // Analyze the hierarchy
    Report report = Analyzer.analyze(ceo);

    // Test results
    assertTrue(report.getManagersWithLowSalary().isEmpty());
    assertTrue(report.getManagersWithHighSalary().isEmpty());
    assertEquals(1, report.getLongReportingLine().size());
  }
}
