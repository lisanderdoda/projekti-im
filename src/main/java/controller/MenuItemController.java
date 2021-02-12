package controller;

import model.Category;
import model.Employee;
import model.MenuItem;
import model.Order;
import repository.MenuItemRepository;
import util.ScannerExt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItemController {
    private final ScannerExt scannerExt;
    private MenuItemRepository menuItemRepository;

    public MenuItemController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.menuItemRepository = new MenuItemRepository();
    }

    public void showMenuItem() {
        boolean goBack = true;
        while (goBack) {
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Shto Menu te re!");
            System.out.println("2.Listo Menu !");
            System.out.println("3.Back!");
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            switch (choise) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    CategoryController categoryController = new CategoryController(scannerExt);
                    categoryController.showCategoryMenu();
                    listMenuItem();
                    break;  //per tu ndertuar menuja
                case 3:
                    goBack = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void removeMenuItem() {
        List<MenuItem> menuItemList = this.menuItemRepository.list();
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
        menuItem.setLastModifiedOn(LocalDateTime.now());
        menuItem.setLastModifiedBy(EmployeeController.getCurrentEmployee().getId());
        this.menuItemRepository.update(menuItem);
    }

    private void listMenuItem() {
        menuItemRepository.list();
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
        CategoryController categoryController = new CategoryController(scannerExt);
        Category category = categoryController.selectCategory();
        menuItem.setCategory(category);
        menuItem.setCreatedBy(EmployeeController.getCurrentEmployee().getId());
        menuItem.setName(menuItemName);
        System.out.println("vendos pershkrimin");
        String description = scannerExt.scanField();
        menuItem.setDescription(description);
        menuItemRepository.save(menuItem);
    }

    public void selectMenuItem(Order order) {
        List<MenuItem> menuItemList = this.menuItemRepository.list();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        for (MenuItem c : menuItemList) {
            System.out.println(count + "." + c.getName());
            integerList.add(count);
            count++;
        }
        boolean ok = true;
        while (ok) {
            System.out.println("Zgjidh produktin");
            int choise = scannerExt.scanRestrictedFieldNumber(integerList);
            OrderItemController orderItemController = new OrderItemController(scannerExt);
            orderItemController.addOrderItem(menuItemList.get(choise), order);
            System.out.println("Zgjidh 1-per te vazhguar ose 2-per te perfunduar prosine");
            Integer select = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2));
            if (select == 2) {
                ok = false;
            }

        }

    }

    public MenuItem selectMenuByCategory(Category category) {
        System.out.println("Zgjidh Menu");
        List<MenuItem> menuFromSameCategory = menuItemRepository.listByCategory(category);
        List<Integer> integerList = new ArrayList<>();
        int count = 1;
        for (MenuItem c : menuFromSameCategory) {
            System.out.println(count + "." + c.getName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        MenuItem menuItem = menuFromSameCategory.get(choise - 1);
        return menuItem;
    }

    public void delete(MenuItem menuItem) {
        menuItemRepository.delete(menuItem);
    }

    public void update(MenuItem menuItem) {
    }
}
