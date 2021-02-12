package controller;

import model.Employee;
import model.Table;
import repository.TableRepository;
import util.ScannerExt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableController {

    private final ScannerExt scannerExt;
    private TableRepository tableRepository;

    public TableController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        tableRepository = new TableRepository();
    }

    public void showMainMenu() {
        boolean logout = true;

        while (logout) {
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Shto tavoline te re!");
            System.out.println("2.Elemino nje tavoline!");
            System.out.println("3.Modifiko nje tavoline!");
            System.out.println("4.Logout!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4));
            switch (choise) {
                case 1:
                    addTable();
                    break;
                case 2:
                    removeTable();
                    break;
                case 3:
                    editTable();
                case 4:
                    logout = false;
                    break;
                default:
                    break;
            }
        }

    }

    private void editTable() {
    }

    private void removeTable() {

        List<Table> tableList = tableRepository.list();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        System.out.println("Zgjidh tavolinen qe do te hiqet:");
        for (Table t : tableList) {
            System.out.println(count + "." + t.getName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        Table table = tableList.get(choise - 1);
        table.setIsDeleted(true);
        table.setLastModifiedBy(EmployeeController.getCurrentEmployee().getId());
        table.setLastModifiedOn(LocalDateTime.now());
        tableRepository.update(table);

    }

    private void addTable() {
        TableRepository tableRepository = new TableRepository();
        List<Table> tables = tableRepository.list();
        Table table = new Table();
        boolean ok = true;

        List<Table> tables1 = new ArrayList<>();
        while (ok) {
            System.out.println("Vendos emrin e tavolines se re:");
            String name = scannerExt.scanField();
            tables1 = tables.stream().filter(x -> x.getName().equals(name))
                    .collect(Collectors.
                            toList());
            if (tables1.isEmpty()) {
                table.setName(name);
                ok = false;
            } else {
                System.out.println("Ky emertim nuk eshte i disponueshem!");
            }
        }

        System.out.println("Vendos sa vende do kete:");
        Integer numberOfSeats = scannerExt.scanNumberField();

        table.setCreatedBy(EmployeeController.getCurrentEmployee().getId());
        table.setCreatedOn(LocalDateTime.now());
        table.setIsDeleted(false);

        table.setNumberOfSeats(numberOfSeats);
        table.setOccupied(false);

        tableRepository.save(table);

    }

    public Table selctTable() {
        TableRepository tableRepository = new TableRepository();
        List<Table> tableList = tableRepository.list();
        int count = 1;
        List<Integer> integerList = new ArrayList<>();

        System.out.println("Zgjidh tavolinen ku po meret porosia");
        for (Table t : tableList) {
            System.out.println(count + "." + t.getName());
            integerList.add(count);
            count++;
        }
        int choise = scannerExt.scanRestrictedFieldNumber(integerList);
        Table table = tableList.get(choise - 1);
        return table;
    }
}
