import util.EmployeeUtils;
import util.ScannerExt;

import java.util.Scanner;

public class RestorantApplications {



    public static void main(String[] args) {
        ScannerExt scannerExt = new ScannerExt(new Scanner(System.in));


        EmployeeUtils employeeUtils = new EmployeeUtils(scannerExt);
        // vendos nr per cilen kategori te logohesh

       employeeUtils.login();

    }
}
