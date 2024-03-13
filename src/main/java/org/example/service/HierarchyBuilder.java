package org.example.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.model.Employee;

public class HierarchyBuilder {
  private HierarchyBuilder() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Builds employee hierarchy as a tree. It's considered that at least one and only one CEO exists.
   *
   * @param fileName csv fileName
   * @return Root object (CEO) returned
   */
  public static Employee buildHierarchyFromCsv(String fileName) {
    Employee ceo = null;
    Map<Integer, Employee> employeeMap = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      br.readLine();//skipping first line
      String line;
      while ((line = br.readLine()) != null) {
        String[] split = line.split(",");
        int id = Integer.parseInt(split[0]);
        String firstName = split[1];
        String lastName = split[2];
        int salary = Integer.parseInt(split[3]);
        Employee manager = null;
        if (split.length > 4 && !split[4].isEmpty()) {
          int managerId = Integer.parseInt(split[4]);
          manager = employeeMap.get(managerId);
        }
        Employee employee = new Employee(id, firstName, lastName, salary, manager);
        employeeMap.put(id, employee);
        //If manager is not found for this employee, then this employee is CEO
        if (manager == null) {
          ceo = employee;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ceo;
  }
}
