package dto;

import java.time.LocalDate;

public record EmployeeCreateRequest(
        String firstName,
        String lastName,
        Double salary,
        LocalDate hireDate
) {
}
