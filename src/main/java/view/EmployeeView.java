package view;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeView {

    private final Scanner scanner = new Scanner(System.in);

    public EmployeeCreateRequest createEmployee() {
        System.out.println("=== [[ Employee Creation ]] ===");

        String firstName = getStringInput(scanner,"[+] Input First Name : ");
        String lastName = getStringInput(scanner,"[+] Input Last Name : ");
        Double salary = getDoubleInput(scanner,"[+] Input Salary : ");
//        System.out.print("[+] Input Hire Date(yyyy-MM-dd): ");
//        String hireDate = scanner.nextLine();
//
//        String[] parts = hireDate.split("-");
//        int year = Integer.parseInt(parts[0]);
//        int month = Integer.parseInt(parts[1]);
//        int dayOfMonth = Integer.parseInt(parts[2]);

//        DateTimeFormatter hireDateFmt = DateTimeFormatter
//                .ofPattern("dd-MM-yyyy");
        LocalDate hire =  getDateTimeInput(scanner,"[+] Input Hire Date(yyyy-MM-dd): ");

        return new EmployeeCreateRequest(
                firstName, lastName, salary, hire
        );
    }

    public void displayEmployeeResponse(EmployeeResponse response, String context){
        Table table = new Table(
                3, BorderStyle.CLASSIC
        );
        table.addCell(context,3);
        table.addCell("ID");
        table.addCell(response.id().toString(),2);
        table.addCell("First Name");
        table.addCell(response.firstName(),2);
        table.addCell("Last Name");
        table.addCell(response.lastName(),2);
        table.addCell("Salary");
        table.addCell(response.salary().toString(),2);
        table.addCell("Hire Date");
        table.addCell(response.hireDate().toString(),2);

        System.out.println(table.render());
    }

    public void displayTableEmployee(List<EmployeeResponse> responses) {
        Table table = new Table(5, BorderStyle.CLASSIC);
        String[] columns = {"ID", "First Name", "Last Name", "Salary", "Hire Date"};
        for (String column : columns) {
            table.addCell(column);
        }
        responses.forEach(
                user -> {
                    table.addCell(user.id().toString());
                    table.addCell(user.firstName());
                    table.addCell(user.lastName());
                    table.addCell(user.salary().toString());
                    table.addCell(user.hireDate().toString());
                }
        );
        System.out.println(table.render());
    }

    public Long inputId(){
        while (true){
            System.out.print("[+] Input Id : ");
            try {
                return Long.parseLong(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Invalid Id format. Please Input Number!");
            }
        }
    }


    public Integer showMenuAndGetOption(){
        System.out.println("""
                ============= Employee Management =============
                1) Create Employee 
                2) Update Employee
                3) Get All mployees
                4) Get Employee By Id
                5) Delete Employee 
                ===============================================
                """);
        System.out.print("[+] Choose an option (1-5) : ");
        return  Integer.parseInt(scanner.nextLine());
    }

    public String getStringInput(Scanner sc , String context){
        while (true){
            System.out.print(context);
            String input = sc.nextLine();
            if(input.isBlank() || !input.matches("[A-Za-z]{2,}")){
                System.out.println("Invalid Input. Try Again.");
            }else {
                return input;
            }
        }
    }
    public Double getDoubleInput(Scanner scanner, String context){
        while (true){
            System.out.print(context);
            try {
                double salary = Double.parseDouble(scanner.nextLine());
                if(salary<0){
                    System.out.println("Salary cannot be negative");
                    continue;
                }
                return salary;
            }catch (NumberFormatException e){
                System.out.printf("Invalid Input. Try Again.");
            }
        }
    }
    private LocalDate getDateTimeInput(Scanner sc, String context) {
        while (true) {
            System.out.print(context);
            String input = sc.nextLine();
            if (!input.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
                System.out.println("Invalid Format for Date (yyyy-MM-dd). Try Again.");
            } else {
                LocalDate result = LocalDate.parse(input);
                if (result.isAfter(LocalDate.now())) {
                    System.out.println("Cannot hire employee from the future");
                } else {
                    return result;
                }
            }
        }
    }
}
