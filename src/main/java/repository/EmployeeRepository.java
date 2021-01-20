package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeRepository {

    public void createEmployee(Integer createBy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vendosni emerin e punonjesit te ri ");
        String name = scanner.nextLine();
        Employee employee = new Employee();
        employee.setFirstName(name);
        employee.setCreatedBy(createBy);
        employee.setCreatedOn(LocalDate.now());
        employee.setDeleted(false);
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
            deleteEmployee();
        } else {
            Transaction transaction = session.beginTransaction();
            session.update(employees.get(0));
            transaction.commit();
            session.close();
        }
    }
    private void findByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("vendosni emrin e punonjesit ");
        String name = scanner.nextLine();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from  Employee e where e.firstName=:firstName and e.isDeleted=false ");
        query.setParameter("firstName", name);
        List<Employee> employees = query.getResultList();
        if (employees.isEmpty()) {
            System.out.println("punonjesi nuk ekziston ");
            findByName();
        }

    }
}
