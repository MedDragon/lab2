import org.junit.Test;
import static org.junit.Assert.*;


public class Main {
    public static void main(String[] args) {

        try {
            Employee employee1 = new Employee("Johnw", "Doe", 5000.0);
            employee1.getEmployeeInfo();
        } catch (FieldLengthLimitException | IncorrectSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

class FieldLengthLimitException extends Exception {
    public FieldLengthLimitException(String fieldName, int maxLength) {
        super(fieldName + " exceeds the maximum length of " + maxLength + " characters.");
    }
}

class IncorrectSalaryException extends Exception {
    public IncorrectSalaryException() {
        super("Salary cannot be negative.");
    }
}

class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private String surname;
    private double salary;

    public Employee(String name, String surname, double salary) throws FieldLengthLimitException, IncorrectSalaryException {
        setId();
        setName(name);
        setSurname(surname);
        setSalary(salary);
    }

    private void setId() {
        id = nextId;
        nextId++;
    }

    private void checkFieldLength(String fieldValue, String fieldName, int maxLength) throws FieldLengthLimitException {
        if (fieldValue.length() > maxLength) {
            throw new FieldLengthLimitException(fieldName, maxLength);
        }
    }

    public void setName(String name) throws FieldLengthLimitException {
        checkFieldLength(name, "Name", 50);
        this.name = name;
    }

    public void setSurname(String surname) throws FieldLengthLimitException {
        checkFieldLength(surname, "Surname", 50);
        this.surname = surname;
    }

    public void setSalary(double salary) throws IncorrectSalaryException {
        if (salary < 0) {
            throw new IncorrectSalaryException();
        }
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

    public void getEmployeeInfo() {
        System.out.println("Employee ID: " + getId());
        System.out.println("Employee name: " + getName());
        System.out.println("Employee surname: " + getSurname());
        System.out.println("Employee salary: " + getSalary());
    }
}