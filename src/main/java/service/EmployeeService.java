package service;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import dto.EmployeeUpdateRequest;
import exceptions.EmployeeException;

import java.util.List;

public interface EmployeeService {


    EmployeeResponse createEmployee (EmployeeCreateRequest requests) throws EmployeeException;


    List<EmployeeResponse> getAllEmployees() throws EmployeeException ;


    EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request)
            throws EmployeeException;

    void deleteEmployee(Long request) throws EmployeeException;

    EmployeeResponse getEmployeeById(Long id);
}
