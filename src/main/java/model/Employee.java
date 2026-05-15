package model;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Employee {
//increase id auto
    private static Long nextId = 1L;
//    private static DateTimeFormatter hireDateFmt = DateTimeFormatter
//            .ofPattern("E-dd-MM-yyyy");
    @NonNull
    private Long id;
    private String firstName;
    private String lastName;
    private Double salary;
    private LocalDate hireDate;
    //              no id in this param because in increase auto
    public Employee(String firstName, String lastName, Double salary, LocalDate hireDate) {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hireDate = hireDate;
    }

}

