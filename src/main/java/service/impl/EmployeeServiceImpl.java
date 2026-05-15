package service.impl;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;
import mapper.EmployeeMapper;
import model.Employee;
import respository.EmployeeRepository;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) throws EmployeeException {

        Employee employee = mapper.fromEmployeeCreateRequestToEmployee(request);

        repository.save(employee);

        return mapper.toEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() throws EmployeeException {

        if(repository.finalAll().isEmpty()){
            throw new EmployeeException("No Data Yet!");
        }
        return repository.finalAll()
                .stream()
                .map(mapper::toEmployeeResponse)
                .toList();

    }

    @Override
    public EmployeeResponse findById(Long id) {
        return repository.finalAll().stream().map(mapper::toEmployeeResponse).findFirst().orElseThrow(()-> new RuntimeException("Id not found!"));
    }


}
