package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.example.model.Employee;
import org.example.service.HierarchyBuilder;
import org.junit.jupiter.api.Test;

class HierarchyBuilderTest {
  @Test
  void testBuildHierarchyFromCsv() {
    // Test CSV file with sample data
    String filename = "src/test/resources/test.csv";

    // Test building hierarchy from CSV
    Employee ceo = HierarchyBuilder.buildHierarchyFromCsv(filename);

    // Verify CEO object is not null
    assertNotNull(ceo);

    // Verify CEO's details
    assertEquals(1, ceo.getId());
    assertEquals("John", ceo.getFirstName());
    assertEquals("Doe", ceo.getLastName());
    assertEquals(100000, ceo.getSalary());
    assertNull(ceo.getManager()); // CEO has no manager

    // Verify CEO's direct report(s)
    assertEquals(2, ceo.getSubordinates().size()); // Assuming 2 direct reports

    // Assuming the first direct report of CEO
    Employee directReport1 = ceo.getSubordinates().get(0);
    assertEquals(2, directReport1.getId());
    assertEquals("Alice", directReport1.getFirstName());
    assertEquals("Smith", directReport1.getLastName());
    assertEquals(80000, directReport1.getSalary());
    assertSame(ceo, directReport1.getManager()); // Direct report's manager is CEO

    // Assuming the second direct report of CEO
    Employee directReport2 = ceo.getSubordinates().get(1);
    assertEquals(3, directReport2.getId());
    assertEquals("Bob", directReport2.getFirstName());
    assertEquals("Johnson", directReport2.getLastName());
    assertEquals(75000, directReport2.getSalary());
    assertSame(ceo, directReport2.getManager()); // Direct report's manager is CEO
  }
}
