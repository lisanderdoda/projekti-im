package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeRepository {
    private final ScannerExt scannerExt;

    public EmployeeRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }

    public Employee login(String user, String pass) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("select e from Employee e where e.username=:user and e.password=:pass");
        query.setParameter("user", user);
        query.setParameter("pass", pass);
        List<Employee> employees = query.getResultList();

        Employee employee = null;

        if (!employees.isEmpty()) {
            return employees.get(0);
        }

        session.close();

        return employee;
    }

    public void createEmployee(Integer createBy) {

        System.out.println("Vendosni emerin e punonjesit te ri ");
        String name = this.scannerExt.scanField();
        System.out.println("Vendosni mbiemrin");
        String lastname = this.scannerExt.scanField();
        System.out.println("vendosni pzicionin qe do kete");
        String role = this.scannerExt.scanField();
        System.out.println("Te vendosi username:");
        String username = this.scannerExt.scanField();
        System.out.println("Te vendosi passwordin");
        String password = this.scannerExt.scanField();
        System.out.println("dita e lindjes(shembull: 2000-01-20");
        LocalDate localDate = this.scannerExt.scanDateField();

        Employee employee = new Employee();
        employee.setRole(role);
        employee.setFirstName(name);
        employee.setLastName(lastname);
        employee.setCreatedBy(createBy);
        employee.setCreatedOn(LocalDate.now());
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setDeleted(false);
        employee.setDateOfBirth(localDate);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        System.out.println("Punonjesi u shtua");
    }
    public void deleteEmployee() {
        System.out.println("zgjidhni punonjesin qe doni te fshini");

        String name = this.scannerExt.scanField();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from Employee e where e.firstName=:firstName and e.isDeleted=false ");
        List<Employee> employees = query.getResultList();
        if (employees.isEmpty()) {
            System.out.println("punonjesit nuk ekziston");
            session.close();
        } else {
            Transaction transaction = session.beginTransaction();
            session.update(employees.get(0));
            transaction.commit();
            session.close();
        }
    }
    public Employee findByName() {

        System.out.println("vendosni emrin e punonjesit ");
        String name = this.scannerExt.scanField();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from  Employee e where e.firstName=:firstName " +
                "and e.isDeleted=false ");
        query.setParameter("firstName", name);
        List<Employee> employees = query.getResultList();
        if (employees.isEmpty()) {
            System.out.println("punonjesi nuk ekziston ");
            session.close();
            return findByName();
        }
        Employee employee = employees.get(0);
        session.close();
        System.out.println(employee);
        return employee;
    }
}
