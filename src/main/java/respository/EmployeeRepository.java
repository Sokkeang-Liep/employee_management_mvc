package respository;

import database.EmployeeDb;
import model.Employee;

import java.util.List;

public class EmployeeRepository {
    private final EmployeeDb employeeDb;

    //tor tea ke bos data mean employeeDb ban EmployeeRepository work
    public EmployeeRepository(EmployeeDb employeeDb){
        this.employeeDb = employeeDb;
    }

    public void save(Employee employee){
        //because data return jea list srab
        employeeDb.data().add(employee);
    }

    public List<Employee> finalAll(){
        return employeeDb.data();
    }

    //check mean id ng ot using stream api method
    public boolean exitById(Long id){
        return employeeDb.data()
                .stream()
                .anyMatch(emp ->emp.getId().equals(id));
    }
}
