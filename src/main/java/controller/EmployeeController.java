package controller;

import model.Employee;
import repository.EmployeeRepository;
import util.ScannerExt;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class EmployeeController {
    private final ScannerExt scannerExt;

    private EmployeeRepository employeeRepository;

    private static Employee currentEmployee;

    public EmployeeController(ScannerExt scannerExt){
        this.scannerExt = scannerExt;
        this.employeeRepository = new EmployeeRepository();
    }

    public static Employee getCurrentEmployee(){
        return currentEmployee;
    }

    public void login(){
        boolean successfulLogin = true;
        while(successfulLogin){
            System.out.println("Enter username or 0 to go back:");

            String username = this.scannerExt.scanField();

            if(username.equals("0")){
                break;
            }

            System.out.println("Enter password:");

            String password = this.scannerExt.scanField();

            Employee employee = this.employeeRepository.login(username, password);
            if(Objects.isNull(employee)){
                System.out.println("Kredenciale te pasakta. Provo perseri");
            } else {
                currentEmployee = employee;
                successfulLogin = false;
                if(employee.getRole().equals("ADMIN")){
                    System.out.println("Paneli i administratorit!");
                    showAdminMenu();
                } else {
                    System.out.println("Paneli i operatorit!");
                    showOperatorMenu();
                }
            }
        }
    }

    public void showAdminMenu(){
        boolean logout = true;
        while(logout){
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Menaxho Stafin!");
            System.out.println("2.Menaxho Tavolinat!");
            System.out.println("3.Logout!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1,2,3));

            switch (choise){
                case 1: showManageStaff(); break;
                case 2: {
                    TableController tableController = new TableController(scannerExt);
                    tableController.showMainMenu();
                    break;
                }
                case 3: logout = false; break;
                default: break;
            }
        }
    }

    public void showOperatorMenu(){
        System.out.println("Zgjidhni nje nga opsionet me poshte!");
        boolean logout = true;
        while(logout){
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Porosite e mia!");
            System.out.println("2.Porosi e re!");
            System.out.println("3.Logout!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1,2,3));
            OrderController orderController = new OrderController(scannerExt);
            switch (choise){
                case 1: orderController.showMyOrders(); break;
                case 2: orderController.addOrder(); break;
                case 3: logout = false; break;
                default: break;
            }
        }
    }

    public void showManageStaff(){
        boolean goBack = true;
        while(goBack){
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Shto punonjes!");
            System.out.println("2.Listo punonjesit!");
            System.out.println("3.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1,2,3));

            switch (choise){
                case 1: addEmployee(); break;
                case 2: listEmployees(); break;
                case 3: goBack = false; break;
                default: break;
            }
        }
    }

    public void listEmployees(){
        System.out.println("Lista e punonjeseve...!");
        employeeRepository.listEmployees().forEach(System.out::println);
        System.out.println("Zgjidhni nje nga punonjesit nese doni te operoni ose 0 per te shkruar mbrapa!");
    }

    public void addEmployee(){
        System.out.println("Vendosni emerin e punonjesit te ri ");
        String name = this.scannerExt.scanField();
        System.out.println("Vendosni mbiemrin");
        String lastname = this.scannerExt.scanField();
        System.out.println("vendosni pzicionin qe do kete");
        String role = this.scannerExt.scanField();
        System.out.println("Te vendosi username:");
        String username = this.scannerExt.scanField();
        System.out.println("Te vendosi passwordin");
        String password = this.scannerExt.scanField();
        System.out.println("dita e lindjes(shembull: 2000-01-20");
        LocalDate localDate = this.scannerExt.scanDateField();

        Employee employee = new Employee();
        employee.setRole(role);
        employee.setFirstName(name);
        employee.setLastName(lastname);
        employee.setCreatedBy(getCurrentEmployee().getId());
        employee.setCreatedOn(LocalDate.now());
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setDeleted(false);
        employee.setDateOfBirth(localDate);
        this.employeeRepository.addEmployee(employee);


    }

    public void editEmployee(){
        System.out.println("zgjidhni punonjesin qe doni te fshini");
        System.out.println("Vendosni emrin:");
        String firstname = scannerExt.scanField();
        System.out.println("Vendosni emrin:");
        String lastname = scannerExt.scanField();
        employeeRepository.removeEmployee(firstname, lastname);
    }

    public void removeEmployee(){

    }
}
