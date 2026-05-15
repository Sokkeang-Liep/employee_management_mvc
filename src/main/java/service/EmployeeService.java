package service;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;

import java.util.List;

public interface EmployeeService {

    //CRUD
    EmployeeResponse createEmployee (EmployeeCreateRequest requests) throws EmployeeException;

    // ot use direct class te that why use DTO
    List<EmployeeResponse> getAllEmployees() throws EmployeeException ;

    EmployeeResponse findById(Long id)  throws EmployeeException ;
}
