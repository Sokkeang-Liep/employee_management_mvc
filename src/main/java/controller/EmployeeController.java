package controller;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import dto.EmployeeUpdateRequest;
import exceptions.EmployeeException;
import service.EmployeeService;
import view.EmployeeView;

import java.util.List;

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

        view.displayEmployeeResponse(response, "Created Employee");

    }

    public void update() {}

    public void getAll() {

        List<EmployeeResponse> responseList = service.getAllEmployees();

        view.displayTableEmployee(responseList);

    }

    public void getById() {
        Long id = view.inputId();

        EmployeeResponse response = service.getEmployeeById(id);

        view.displayEmployeeResponse(response, "Employee Details");
    }

    public void delete() {}

    public void start() {
        while (true) {
            int option = view.showMenuAndGetOption();
            if (option == 0) {
                System.out.println("!! Exiting...");
                System.exit(0);
            }
            switch (option) {
                case 1 -> create();
                case 2 -> update();
                case 3 -> getAll();
                case 4 -> {
                    try {
                        getById();
                    } catch (EmployeeException e) {
                        System.out.println("Info: " + e.getMessage());
                    }
                }
                case 5 -> delete();
                default -> System.out.println("! Invalid Option.");
            }
        }
    }

}
