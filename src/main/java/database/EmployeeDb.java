package database;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDb {

    private final List<Employee> employees;

    //pel ke create employeeDb ban mean array list to store data
    public EmployeeDb(){
        employees = new ArrayList<>();
    }
    //pel jg ban data yk employee.data
    public List<Employee> data(){
        return employees;
    }
}
