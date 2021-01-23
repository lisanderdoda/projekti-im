package util;

import model.*;
import repository.*;

import java.util.Scanner;

public class EmployeeUtils {
    private final ScannerExt scannerExt;

    public EmployeeUtils(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }


    public void login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Vendosni username");
        String user = scan.nextLine();
        System.out.println("Vendosni password");
        String pass = scan.nextLine();

        EmployeeRepository employeeRepository = new EmployeeRepository(this.scannerExt);
        Employee employee = employeeRepository.login(user, pass);

        if (employee == null) {
            System.out.println("bad credentials");
            this.login();
        } else {
            if (employee.getRole().equalsIgnoreCase("administrator")) {
                showMenuAdmin(employee);
            } else if (employee.getRole().equalsIgnoreCase("kamarier")) {
                showMenuWaiter(employee);
            } else if (employee.getRole().equalsIgnoreCase("chef")) {
                showMenuChef(employee);
            }
        }
    }

    public void showMenuAdmin(Employee employee) {
        System.out.println("Pershendetje " + employee.getFirstName());
        System.out.println("Zgjidhni numrin cfar veprimi deshironi te kryeni\n 1- Shtoni nje punonjes te ri\n " +
                "2- Shkarkoni nje punonjes ekzistent\n 3- Shiko listen e punonjesve\n 4- gjej nje punonjes me emer x\n" +
                " 5- Shto nje tavoline tjeter ne restorant\n 6- Redukto tavolinen me emertim x ne restorant\n" +
                " 0- Dil");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        EmployeeRepository employeeRepository = new EmployeeRepository(this.scannerExt);
        TableRepository tableRepository = new TableRepository(scannerExt);
        switch (number) {
            case 1:

                employeeRepository.createEmployee(employee.getId());
                showMenuAdmin(employee);

                break;
            case 2:

                employeeRepository.deleteEmployee();
                break;
            case 3:
                //metoda per tu ndertuar ne employeeRepository
                break;
            case 4:
                employeeRepository.findByName();
                showMenuAdmin(employee);
                break;
            case 5:
                tableRepository.createTable(employee.getId());
                showMenuAdmin(employee);
                break;
            case 6:
                //metoda per tu krijuar ne TableRepository
                break;
            case 0:
                break;
            default:
                System.out.println("Numri i vendosur nuk eshte i vlefshem");


        }

    }

    public void showMenuWaiter(Employee employee) {
        System.out.println("Zgjidh veprimin:\n1- Merr porosi\n2-Merr pagesen\n3-shto dicka ne nje porosi te hapur\n4-dil");
        int choise = scannerExt.scanNumberField();
        switch (choise) {

            case 1:
                System.out.println("kamarier " + employee.getFirstName() + " zjgidh tavolinen ku po merret porosia");
                TableRepository tableRepository = new TableRepository(scannerExt);
                Table table = tableRepository.findTable();
                //krijojme order
                OrderRepository orderRepository = new OrderRepository(scannerExt);
                Order order = orderRepository.createOrder(employee, table);
                // krijojme nje orderitem per cdo item qe do porositet ne te njejtin order
                MenuItemRepository menuItemRepository = new MenuItemRepository(scannerExt);
                OrderItemRepository orderItemRepository = new OrderItemRepository(scannerExt);
                boolean a = true;
                while (a) {
                    MenuItem menuItem = menuItemRepository.findByName();
                    orderItemRepository.createOrderItem(order, menuItem, employee.getId());
                    System.out.println("shtyp \n1 per te vazhduar porosine \n2 nese mjafton me kaq");
                    Integer number = scannerExt.scanNumberField();
                    if (number == 1) {
                        System.out.println("zgjidh produkt qe do i shtohet porosise");
                    } else {
                        a = false;
                        System.out.println("Porosia u mbyll");
                    }
                }
                break;
            case 2:
                System.out.println("Per cilen tavoline do kryhet pagesa?");
                int tableName = scannerExt.scanNumberField();
                OrderRepository orderRepository1 = new OrderRepository(scannerExt);
                Order order1 = orderRepository1.selectOrderOpened(tableName);
                System.out.println(order1);
                OrderItemRepository orderItemRepository1 = new OrderItemRepository(scannerExt);
                orderItemRepository1.totalFromSameOrder(order1, tableName);
                orderRepository1.updateOrder(order1);
                // do shtohet pjesa qe

                break;


        }


    }

    public void showMenuChef(Employee employee) {
        System.out.println("Pershendetje\n vendosni numrin ne baze te veprimit qe deshironi\n 1- per te krijuar " +
                "nje kategori produktesh te re\n 2- per te krijuar nje menu produkti te ri\n 3- per te fshire nje menu " +
                "produkti\n 0- dil");

        int numri = scannerExt.scanNumberField();
        switch (numri) {
            case 1:
                int a = 0;  // do zevendesohet pasi te behet klasa employee repository me Id e employeet te loguar
                CategoryRepository categoryRepository = new CategoryRepository(scannerExt);
                categoryRepository.createCategory(a);
                showMenuChef(employee);
                break;
            case 2:
                int b = employee.getId(); // e njejta gje si me siper
                CategoryRepository categoryRepository1 = new CategoryRepository(scannerExt);
                Category category = categoryRepository1.findByName();
                MenuItemRepository menuItemRepository = new MenuItemRepository(scannerExt);
                menuItemRepository.createMenuItem(category, b);
                showMenuChef(employee);
                break;
            case 3:
                MenuItemRepository menuItemRepository1 = new MenuItemRepository(scannerExt);
                menuItemRepository1.deleteMenuItem();
            case 0:
                break;
            default:
                System.out.println("Numri qe vendoset nuk eshte i vlefshem");
                showMenuChef(employee);
        }
    }


}
