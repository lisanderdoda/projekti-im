package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeRepository {

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vendosni emerin e punonjesit te ri ");
        String name = scanner.nextLine();
        System.out.println("Vendosni mbiemrin");
        String lastname = scanner.nextLine();
        System.out.println("vendosni pzicionin qe do kete");
        String role = scanner.nextLine();
        System.out.println("Te vendosi username:");
        String username = scanner.nextLine();
        System.out.println("Te vendosi passwordin");
        String password = scanner.nextLine();
        System.out.println("dita e lindjes(shembull: 2000-01-20");
        String dateFormat = "yyyy-MM-dd";
        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormat).parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("formati i dates i gabuar");
            e.printStackTrace();

        }
        Employee employee = new Employee();
        employee.setRole(role);
        employee.setFirstName(name);
        employee.setLastName(lastname);
        employee.setCreatedBy(createBy);
        employee.setCreatedOn(LocalDate.now());
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setDeleted(false);
        employee.setDateOfBirth(date);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
    }
    public void deleteEmployee() {
        System.out.println("zgjidhni punonjesin qe doni te fshini");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from Employee e where e.firstName=:firstName and e.isDeleted=false ");
        List<Employee> employees = query.getResultList();
        if (employees.isEmpty()) {
            System.out.println("punonjesit nuk ekziston");
            session.close();
            deleteEmployee();
        } else {
            Transaction transaction = session.beginTransaction();
            session.update(employees.get(0));
            transaction.commit();
            session.close();
        }
    }
    public Employee findByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("vendosni emrin e punonjesit ");
        String name = scanner.nextLine();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from  Employee e where e.firstName=:firstName " +
                "and e.isDeleted=false ");
        query.setParameter("firstName", name);
        List<Employee> employees = query.getResultList();
        if (employees.isEmpty()) {
            System.out.println("punonjesi nuk ekziston ");
            session.close();
            findByName();
        }
        Employee employee = employees.get(0);
        session.close();
        return employee;
    }
}
