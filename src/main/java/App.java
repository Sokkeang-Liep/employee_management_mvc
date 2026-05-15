import controller.EmployeeController;
import database.EmployeeDb;
import mapper.EmployeeMapper;
import respository.EmployeeRepository;
import service.EmployeeService;
import service.impl.EmployeeServiceImpl;
import view.EmployeeView;

public class App {
    public static void main(String[] args) {
        EmployeeView view = new EmployeeView();
        EmployeeDb db = new EmployeeDb();
        EmployeeMapper mapper = new EmployeeMapper();
        EmployeeRepository repository = new EmployeeRepository(db);
        EmployeeService service = new EmployeeServiceImpl(repository, mapper);

        EmployeeController controller = new EmployeeController(view, service);

//        controller.create();
        controller.start();
    }
}
