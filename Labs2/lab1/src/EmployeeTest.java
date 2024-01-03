import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmployeeTest {

    @Test
    public void testValidEmployee() {
        try {
            Employee employee = new Employee("John", "Doe", 5000.0);
            assertEquals("John", employee.getName());
            assertEquals("Doe", employee.getSurname());
            assertEquals(5000.0, employee.getSalary(), 0.01); // Допускаємо невеликі різниці через округлення
        } catch (FieldLengthLimitException | IncorrectSalaryException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    public void testNegativeSalary() {
        try {
            Employee employee = new Employee("Alice", "Smith", -1000.0);
            fail("IncorrectSalaryException should be thrown");
        } catch (IncorrectSalaryException e) {
            assertEquals("Salary cannot be negative.", e.getMessage());
        } catch (FieldLengthLimitException e) {
            fail("FieldLengthLimitException should not be thrown");
        }
    }

    @Test
    public void testMaxLengthName() {
        try {
            Employee employee = new Employee("VeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongName", "Johnson", 7500.0);
            fail("FieldLengthLimitException should be thrown");
        } catch (FieldLengthLimitException e) {
            assertEquals("Name exceeds the maximum length of 50 characters.", e.getMessage());
        } catch (IncorrectSalaryException e) {
            fail("IncorrectSalaryException should not be thrown");
        }
    }
}
