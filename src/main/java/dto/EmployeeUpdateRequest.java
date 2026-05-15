package dto;


import java.time.LocalDate;


public record EmployeeUpdateRequest(

        String firstName,
        String lastName,
        Double salary,
        LocalDate hireDate
) {
}

