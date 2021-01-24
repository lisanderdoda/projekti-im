import util.EmployeeUtils;
import util.ScannerExt;

import java.util.Scanner;

public class RestorantApplications {


    public static void main(String[] args) {
        ScannerExt scannerExt = new ScannerExt(new Scanner(System.in));

        EmployeeUtils employeeUtils = new EmployeeUtils(scannerExt);

        employeeUtils.login();

        scannerExt.close();
        System.exit(0);

    }
}
