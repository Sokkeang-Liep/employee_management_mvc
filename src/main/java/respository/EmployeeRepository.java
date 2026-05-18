package respository;

import config.DbConnection;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

//     private final EmployeeDb employeeDb;
//
//    //tor tea ke bos data mean employeeDb ban EmployeeRepository work
//    public EmployeeRepository(EmployeeDb employeeDb){
//        this.employeeDb = employeeDb;
//    }
//
//    public void save(Employee employee){
//        //because data return jea list srab
//        employeeDb.data().add(employee);
//    }
//
//    public List<Employee> finalAll(){
//        return employeeDb.data();
//    }
//
//    //check mean id ng ot using stream api method
//    public boolean exitById(Long id){
//        return employeeDb.data()
//                .stream()
//                .anyMatch(emp ->emp.getId().equals(id));
//    }



//    public List<Employee> findAll() {
//        List<Employee> emps = new ArrayList<>();
//
//        String sql = "select * from employees";
//
//       try( Connection conn = DbConnection.getConnection();
//       PreparedStatement preparedStatement = conn.prepareStatement(sql);
//       ResultSet resultSet = preparedStatement.executeQuery()){
//
//           while (resultSet.next()){
//               Employee singleRecord = new Employee();
//               singleRecord.setId(resultSet.getLong("id"));
//               singleRecord.setFirstName(resultSet.getString("first_name"));
//               singleRecord.setLastName(resultSet.getString("last_name"));
//               singleRecord.setSalary(resultSet.getDouble("salary"));
//               singleRecord.setHireDate(
//                       resultSet.getDate("hire_date").toLocalDate()
//               );
//               return emps;
//           }
//
//       } catch (SQLException e){
//           System.out.println("Error find all.");
//
//       }
//       return null;
//    }

    public List<Employee> findAll() {

        List<Employee> emps = new ArrayList<>();

        String sql = "SELECT * FROM public.employees";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                Employee singleRecord = new Employee();

                singleRecord.setId(resultSet.getLong("id"));
                singleRecord.setFirstName(resultSet.getString("first_name"));
                singleRecord.setLastName(resultSet.getString("last_name"));
                singleRecord.setSalary(resultSet.getDouble("salary"));
                singleRecord.setHireDate(
                        resultSet.getDate("hire_date").toLocalDate()
                );

                emps.add(singleRecord);
            }

        } catch (SQLException e) {

            System.out.println("Error find all.");
            e.printStackTrace();
        }

        return emps;
    }

    public Employee save(Employee employee) {

        String sql = """
                insert into employees (first_name, last_name, salary, hire_date)
                values (?, ?, ?, ?) returning *;
                """;
        Employee singleRecord = new Employee();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pts = conn.prepareStatement(sql)) {
            pts.setString(1, employee.getFirstName());
            pts.setString(2, employee.getLastName());
            pts.setDouble(3, employee.getSalary());
            pts.setDate(4, Date.valueOf(employee.getHireDate()));

            try (ResultSet rs = pts.executeQuery()) {
                if (rs.next()) {
                    singleRecord.setId(rs.getLong("id"));
                    singleRecord.setFirstName(rs.getString("first_name"));
                    singleRecord.setLastName(rs.getString("last_name"));
                    singleRecord.setSalary(rs.getDouble("salary"));
                    singleRecord.setHireDate(
                            rs.getDate("hire_date").toLocalDate()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error insert data");
        }

        return singleRecord;
    }

    public Employee update(Employee employee) {

        String sql = """
            UPDATE employees
            SET first_name = ?,
                last_name = ?,
                salary = ?,
                hire_date = ?
            WHERE id = ?
            """;

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setDouble(3, employee.getSalary());
            ps.setDate(4, java.sql.Date.valueOf(employee.getHireDate()));
            ps.setLong(5, employee.getId());

            ps.executeUpdate();
            return employee;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {

        String sql = "DELETE FROM employees WHERE id = ?";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
