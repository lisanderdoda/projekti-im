package util;

import model.Category;
import model.Employee;
import repository.CategoryRepository;
import repository.EmployeeRepository;
import repository.MenuItemRepository;

import java.util.Scanner;

public class EmployeeUtils {

    public void login() {
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
            if (employee.getRole().equalsIgnoreCase("administrator")) {
              showMenuAdmin();
            } else if (employee.getRole().equalsIgnoreCase("waiter")) {

              showMenuWaiter();
            }else if(employee.getRole().equalsIgnoreCase("chef")){
                showMenuChef();
            }
        }
    }
    public void showMenuAdmin(){
        System.out.println("Pershendetje shoku administrator");


    }

    public void showMenuWaiter(){

    }
    public void showMenuChef(){
        System.out.println("Pershendetje\n vendosni numrin ne baze te veprimit qe deshironi\n 1- per te krijuar " +
                "nje kategori produktesh te re\n 2- per te krijuar nje menu produkti te ri\n 3-per te fshire nje menu " +
                "produkti");
        Scanner scanner = new Scanner(System.in);
        int numri = scanner.nextInt();
        switch (numri){
            case 1:
                int a=0;  // do zevendesohet pasi te behet klasa employee repository me Id e employeet te loguar
                CategoryRepository categoryRepository = new CategoryRepository();
                categoryRepository.createCategory(a);
                break;
            case 2:
                int b = 0; // e njejta gje si me siper
                CategoryRepository categoryRepository1= new CategoryRepository();
                Category category = categoryRepository1.findByName();
                MenuItemRepository menuItemRepository = new MenuItemRepository();
                menuItemRepository.createMenuItem(category, b);
                break;
            case 3:
                MenuItemRepository menuItemRepository1 = new MenuItemRepository();
                menuItemRepository1.deleteMenuItem();
            default:
                System.out.println("Numri qe vendoset nuk eshte i vlefshem");
                showMenuChef();
        }
    }


}
