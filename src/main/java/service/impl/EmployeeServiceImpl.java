package service.impl;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import dto.EmployeeUpdateRequest;
import exceptions.EmployeeException;
import mapper.EmployeeMapper;
import model.Employee;
import respository.EmployeeRepository;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

//    private final EmployeeRepository repository;
//    private final EmployeeMapper mapper;
//
//    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
//        this.repository = repository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public EmployeeResponse createEmployee(EmployeeCreateRequest request) throws EmployeeException {
//
//        Employee employee = mapper.fromEmployeeCreateRequestToEmployee(request);
//
//        repository.save(employee);
//
//        return mapper.toEmployeeResponse(employee);
//    }
//
//    @Override
//    public List<EmployeeResponse> getAllEmployees() throws EmployeeException {
//
//        if(repository.finalAll().isEmpty()){
//            throw new EmployeeException("No Data Yet!");
//        }
//        return repository.finalAll()
//                .stream()
//                .map(mapper::toEmployeeResponse)
//                .toList();
//
//    }
//
//    @Override
//    public EmployeeResponse findById(Long id) {
//
//        Employee employee = repository.finalAll()
//                .stream()
//                .filter(emp -> emp.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Id not found!"));
//
//        return mapper.toEmployeeResponse(employee);
//    }
//
//    @Override
//    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request) {
//
//        Employee employee = repository.finalAll()
//                .stream()
//                .filter(e -> e.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Employee not found!"));
//
//        employee.setFirstName(request.firstName());
//        employee.setLastName(request.lastName());
//        employee.setSalary(request.salary());
//        employee.setHireDate(request.hireDate());
//
//        return mapper.toEmployeeResponse(employee);
//    }
//
//    @Override
//    public void deleteEmployee(Long request) throws EmployeeException {
//
//        Employee employee = repository.finalAll()
//                .stream()
//                .filter(emp -> emp.getId().equals(request))
//                .findFirst()
//                .orElseThrow(() -> new EmployeeException("Employee not found!"));
//
//        repository.finalAll().remove(employee);
//    }


    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) throws EmployeeException {

        Employee employee = mapper.fromEmployeeCreateRequestToEmployee(request);

        Employee emp = repository.save(employee);

        return mapper.toEmployeeResponse(emp);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees()
            throws EmployeeException {

        if (repository.findAll().isEmpty()) {
            throw new EmployeeException("No data yet.");
        }

        return repository.findAll()
                .stream()
                .map(mapper::toEmployeeResponse)
                .toList();
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request) throws EmployeeException {
        Employee employee = repository.findAll()
                .stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new EmployeeException("Employee Not Found")
                );

        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setSalary(request.salary());
        employee.setHireDate(request.hireDate());

        Employee updatedEmployee = repository.update(employee);

        return mapper.toEmployeeResponse(updatedEmployee);
    }


    @Override
    public void deleteEmployee(Long id) throws EmployeeException {
        Employee employee = repository.findAll()
                .stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new EmployeeException("Employee Not Found")
                );

        repository.delete(id);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) throws EmployeeException {
        return repository.findAll().stream()
                .filter(emp -> emp.getId().equals(id))
                .map(mapper::toEmployeeResponse)
                .findFirst()
                .orElseThrow(
                        () -> new EmployeeException("Employee Not Found")
                );
    }


}
