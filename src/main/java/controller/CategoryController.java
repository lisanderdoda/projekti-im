package controller;

import model.Category;
import model.Employee;
import repository.CategoryRepository;
import util.ScannerExt;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryController {
    private final ScannerExt scannerExt;
    private CategoryRepository categoryRepository;
    private Employee currentEmployee;

    public CategoryController(ScannerExt scannerExt, Employee currentEmployee) {
        this.scannerExt = scannerExt;
        this.categoryRepository = new CategoryRepository();
        this.currentEmployee = currentEmployee;
    }

    public void showCategoryMenu() {
        boolean logout = true;
        while (logout) {
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Shto kategori te re!");
            System.out.println("2.Elemino kategori!");
            System.out.println("3.Logout!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            switch (choise) {
                case 1:
                    addCategory();
                    break;

                case 2:
                    removeCategory();

                    break;

                case 3:
                    logout = false;
                    break;
                default:
                    break;
            }
        }

    }

    private void removeCategory() {
        List<Category> categoryList = this.categoryRepository.listCategoryes();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        System.out.println("Zgjidh kategorine qe do te hiqet:");
        for (Category c : categoryList) {
            System.out.println(count + "." + c.getName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        Category category = categoryList.get(choise - 1);
        category.setIsDeleted(true);
        category.setModifiedBy(currentEmployee.getId());
        category.setModifiedOn(LocalDateTime.now());
        this.categoryRepository.editCategory(category);
    }

    public void addCategory() {
        boolean check = true;
        String categoryName = "";
        while (check) {
            System.out.println("Vendos emrin e kategorise:");
            categoryName = scannerExt.scanField();
            check = categoryRepository.checkCategory(categoryName);
            if (check) {
                System.out.println("Kategoria e zgjedhur ekziston");
            }

        }
        Category category = new Category();
        category.setName(categoryName);
        category.setIsDeleted(false);
        category.setCreatedOn(LocalDateTime.now());
        category.setCreatedBy(currentEmployee.getId());
        categoryRepository.addCategory(category);
    }


    public Category selectCategory() {
        List<Category> categoryList = this.categoryRepository.listCategoryes();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        System.out.println("Zgjidh kategorine ku do te vendoset");
        for (Category c : categoryList) {
            System.out.println(count + "." + c.getName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        Category category = categoryList.get(choise - 1);

        return category;
    }
}
