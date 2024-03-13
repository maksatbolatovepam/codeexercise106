package org.example;

import org.example.model.Employee;
import org.example.model.Report;
import org.example.service.Analyzer;
import org.example.service.HierarchyBuilder;

public class Main {
  public static void main(String[] args) {
    Employee ceo = HierarchyBuilder.buildHierarchyFromCsv("src/main/resources/input.csv");
    Report report = Analyzer.analyze(ceo);
    report.print();
  }

}
