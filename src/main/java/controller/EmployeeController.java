package controller;

import model.Employee;
import model.Order;
import model.Table;
import repository.EmployeeRepository;
import util.ScannerExt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
                } else if(employee.getRole().equals("kamarier")){
                    System.out.println("Paneli i operatorit!");
                    showOperatorMenu();
                }else if(employee.getRole().equals("chef")){
                    System.out.println("Paneli i chef!");
                    showChefMenu();
                }
            }
        }
    }

    private void showChefMenu() {
        boolean logout = true;
        while (logout) {
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Menaxho Menu!");
            System.out.println("2.Menaxho Kategori!");
            System.out.println("3.Logout!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            switch (choise){
                case 1:
                    MenuItemController menuItemController = new MenuItemController(scannerExt, getCurrentEmployee());
                    menuItemController.showMenuItem();
                break;
                case 2: {
                    CategoryController categoryController = new CategoryController(scannerExt, getCurrentEmployee());
                    categoryController.showCategoryMenu();
                    break;
                }
                case 3: logout = false; break;
                default: break;
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
                    TableController tableController = new TableController(scannerExt,getCurrentEmployee());
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
            OrderController orderController = new OrderController(scannerExt,getCurrentEmployee());
            switch (choise){
                case 1: orderController.showMyOrders(); break;
                case 2: Order order  = orderController.addOrder();
                MenuItemController menuItemController = new MenuItemController(scannerExt, currentEmployee);
                menuItemController.selectMenuItem(order);
                break;
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
            System.out.println("3.Elemino punonjes!");
            System.out.println("4.Modifiko punonjes!");
            System.out.println("5.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1,2,3,4,5));

            switch (choise){
                case 1: addEmployee(); break;
                case 2: listEmployees(); break;
                case 3: removeEmployee(); break;
                case 4: editEmployee(); break;
                case 5: goBack = false; break;
                default: break;
            }
        }
    }

    public void listEmployees(){
        System.out.println("Lista e punonjeseve...!");
        employeeRepository.listEmployees().forEach(System.out::println);
        System.out.println("Zgjidhni nje nga punonjesit nese doni te operoni!");
    }

    public void addEmployee(){
        Employee employee = new Employee();
        System.out.println("Vendosni emerin e punonjesit te ri ");
        String name = this.scannerExt.scanField();
        System.out.println("Vendosni mbiemrin");
        String lastname = this.scannerExt.scanField();
        System.out.println("vendosni pzicionin qe do kete");
        String role = this.scannerExt.scanField();
        List<Employee> employeeList = this.employeeRepository.listEmployeesAll();
        boolean ok = true;
        boolean check = true;
        String username = "";
        while (check) {
            System.out.println("Vendos username:");
            username = scannerExt.scanField();
            check = employeeRepository.checkEmployeeUsername(username);
            if (check) {
                System.out.println("Username i padisponueshem");
            }

        }
        System.out.println("Te vendosi passwordin");
        String password = this.scannerExt.scanField();
        System.out.println("dita e lindjes(shembull: 20-01-2000");
        LocalDate localDate = this.scannerExt.scanDateField();
        employee.setUsername(username);
        employee.setRole(role);
        employee.setFirstName(name);
        employee.setLastName(lastname);
        employee.setCreatedBy(getCurrentEmployee().getId());
        employee.setCreatedOn(LocalDate.now());
        employee.setPassword(password);
        employee.setDeleted(false);
        employee.setDateOfBirth(localDate);
        this.employeeRepository.addEmployee(employee);

    }

    public void editEmployee(){

        List<Employee> employeeList = this.employeeRepository.listEmployees();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        System.out.println("Zgjidh punonjesin qe do te ndryshoj rol:");
        for (Employee e : employeeList) {
            System.out.println(count + "." + e.getFirstName()+" "+e.getLastName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        Employee employee = employeeList.get(choise - 1);
        System.out.println("Vendos rolin e ri!");
        String role = scannerExt.scanField();
        employee.setRole(role);
        this.employeeRepository.editEmployee(employee);
    }

    public void removeEmployee(){
      List<Employee> employeeList = this.employeeRepository.listEmployees();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        System.out.println("Zgjidh punonjesin qe do te hiqet:");
        for (Employee e : employeeList) {
            System.out.println(count + "." + e.getFirstName()+" "+e.getLastName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        Employee employee = employeeList.get(choise - 1);
        employee.setDeleted(true);
        employee.setModifiedBy(getCurrentEmployee().getId());
        employee.setModifiedOn(LocalDate.now());
        this.employeeRepository.editEmployee(employee);
    }
}
