package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
  private final int id;
  private final String firstName;
  private final String lastName;
  private final int salary;
  private final Employee manager;
  private final List<Employee> subordinates;

  public Employee(int id, String firstName, String lastName, int salary, Employee manager) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary;
    this.manager = manager;
    this.subordinates = new ArrayList<>();
    if (manager != null) {
      manager.addSubordinate(this);
    }
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getSalary() {
    return salary;
  }

  public Employee getManager() {
    return manager;
  }

  public List<Employee> getSubordinates() {
    return subordinates;
  }

  public void addSubordinate(Employee employee) {
    subordinates.add(employee);
  }
}
