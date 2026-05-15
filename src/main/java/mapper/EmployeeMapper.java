package mapper;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import model.Employee;

public class EmployeeMapper {

    public Employee fromEmployeeCreateRequestToEmployee(EmployeeCreateRequest requests){
        return new Employee(
                requests.firstName(),
                requests.lastName(),
                requests.salary(),
                requests.hireDate()
        );
    }

    public EmployeeResponse toEmployeeResponse(Employee employee){
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getHireDate()
        );
    }

}
