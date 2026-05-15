package controller;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;
import service.EmployeeService;
import view.EmployeeView;

public class EmployeeController {
    private final EmployeeView view;
    private final EmployeeService service;

    public EmployeeController(EmployeeView view, EmployeeService service) {
        this.view = view;
        this.service = service;
    }

    public void create() {
       EmployeeCreateRequest request = view.createEmployee();
        EmployeeResponse response = service.createEmployee(request);
        view.displayEmployeeResponse(response,"Created Employee");
    }
    public void update(){

    }
    public void getAll(){
        try {
            view.displayTableEmployee(
                    service.getAllEmployees()
            );
        }catch (EmployeeException e){
            System.out.println(e.getMessage());
        }
    }
    public void getById(){
        try {
            Long id = view.inputId();
            EmployeeResponse employeeResponse = service.findById(id);
            view.displayEmployeeResponse(employeeResponse,"Employee Detail");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public void delete(){

    }
    public void start(){
        while (true){
            Integer opt = view.showMenuAndGetOption();
            switch (opt){
                case 1->create();
                case 2->update();
                case 3->getAll();
                case 4-> getById();
                case 5 -> delete();
                case 0 -> {
                    System.out.println("Exiting...!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Option!");
            }
        }
    }
}
