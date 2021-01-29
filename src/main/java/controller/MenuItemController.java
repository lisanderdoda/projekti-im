package controller;

import model.Category;
import model.Employee;
import model.MenuItem;
import repository.MenuItemRepository;
import util.ScannerExt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItemController {
    private final ScannerExt scannerExt;
    private MenuItemRepository menuItemRepository;
    private Employee employee;

    public MenuItemController(ScannerExt scannerExt, Employee employee) {
        this.scannerExt = scannerExt;
        this.menuItemRepository= new MenuItemRepository();
        this.employee=employee;

    }

    public void showMenuItem() {
        boolean goBack = true;
        while(goBack){
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Shto menu te re!");
            System.out.println("2.Listo menun !");
            System.out.println("3.fshi menu !");
            System.out.println("4.Back!");
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1,2,3));
            switch (choise){
                case 1: addMenuItem(); break;
                case 2: listMenuItem(); break;  //per tu ndertuar menuja
                case 3: removeMenuItem(); break;
                case 4: goBack = false; break;
                default: break;
            }
        }
    }

    private void removeMenuItem() {
        List<MenuItem> menuItemList = this.menuItemRepository.listMenuItems();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        System.out.println("Zgjidh Menu qe do te hiqet:");
        for (MenuItem c : menuItemList) {
            System.out.println(count + "." + c.getName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        MenuItem menuItem = menuItemList.get(choise - 1);
        menuItem.setIsDeleted(true);
        menuItem.setModifiedOn(LocalDateTime.now());
        menuItem.setModifiedBy(employee.getId());
        this.menuItemRepository.editMenuItem(menuItem);
    }

    private void listMenuItem() {
        menuItemRepository.listMenuItems();
    }

    private void addMenuItem() {
        boolean check = true;
        String menuItemName = "";
        while (check) {
            System.out.println("Vendos emrin e Menu:");
            menuItemName = scannerExt.scanField();
            check = menuItemRepository.checkMenuItem(menuItemName);
            if (check) {
                System.out.println("Menu e zgjedhur ekziston, nuk eshte e mundur te rikrijohet");
            }

        }
        MenuItem menuItem = new MenuItem();
        menuItem.setIsDeleted(false);
        menuItem.setCreatedOn(LocalDateTime.now());
        System.out.println("Vendos cmimin qe do te kete");
        double unitPrice = scannerExt.scanDoubleField();
        menuItem.setUnitPrice(unitPrice);
        CategoryController categoryController = new CategoryController(scannerExt, employee);
        Category category =categoryController.selectCategory();
        menuItem.setCategory(category);
        menuItem.setCreatedBy(employee.getId());
        menuItem.setName(menuItemName);
        System.out.println("vendos pershkrimin");
        String description = scannerExt.scanField();
        menuItem.setDescription(description);
        menuItemRepository.addMenuItem(menuItem);
    }
}
