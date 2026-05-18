package service;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import dto.EmployeeUpdateRequest;
import exceptions.EmployeeException;

import java.util.List;

public interface EmployeeService {

    //create
    EmployeeResponse createEmployee (EmployeeCreateRequest requests) throws EmployeeException;

    // getALl (ot use direct class te that why use DTO)
    List<EmployeeResponse> getAllEmployees() throws EmployeeException ;

    //search by id
    EmployeeResponse findById(Long id)  throws EmployeeException ;

    //update by id
    EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request)
            throws EmployeeException;

    //delete by id
    void deleteEmployee(Long request) throws EmployeeException;

    EmployeeResponse getEmployeeById(Long id);
}
