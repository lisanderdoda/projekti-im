package util;

import model.Employee;
import repository.EmployeeRepository;
import repository.ManagerRepository;
import repository.WaiterRepository;
import java.util.Scanner;

public class EmployeeUtils {

    public void login(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Vendosni username");
        String user = scan.nextLine();
        System.out.println("Vendosni password");
        String pass = scan.nextLine();

        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = employeeRepository.login(user, pass);

        if (employee == null) {
            System.out.println("bad credentials");
            this.login();
        } else {
            if(employee.getRole().equalsIgnoreCase("administrator")){
                ManagerRepository managerRepository = new ManagerRepository();
                managerRepository.showMenuAdmin();
            }else if(employee.getRole().equalsIgnoreCase("waiter")){
                WaiterRepository waiterRepository = new WaiterRepository();
                waiterRepository.showMenuWaiter();
            }
        }
    }


}
