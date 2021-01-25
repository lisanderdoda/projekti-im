import controller.EmployeeController;
import util.HibernateUtils;
import util.ScannerExt;
import java.util.Arrays;
import java.util.Scanner;

public class RestorantApplications {


    public static void main(String[] args) {

        HibernateUtils.getSessionFactory();

        ScannerExt scannerExt = new ScannerExt(new Scanner(System.in));

        boolean quit = true;

        while(quit){
            System.out.println("Mireserdhet ne aplikacionin tim");
            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Login");
            System.out.println("0.Quit");

            Integer choice = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0,1));

            switch (choice){
                case 0:
                    quit = false;
                    break;
                case 1:
                    EmployeeController employeeController = new EmployeeController(scannerExt);
                    employeeController.login();
                    break;
                default: break;
            }
        }

        scannerExt.close();
        System.exit(0);

    }
}
