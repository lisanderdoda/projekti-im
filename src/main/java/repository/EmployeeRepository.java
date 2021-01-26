package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import javax.persistence.Query;
import java.util.List;


public class EmployeeRepository {

    public Employee login(String username, String password){
        Session session = HibernateUtils.getSessionFactory().openSession();

        org.hibernate.query.Query query = session.createQuery("from Employee e where e.username = :usersname and e.password = :password");

        query.setParameter("usersname", username);

        query.setParameter("password", password);

        Employee employee = null;

        List<Employee> employees = query.getResultList();

        if(!employees.isEmpty()){
            employee = employees.get(0);
        }

        session.close();

        return employee;
    }

    public void addEmployee(Employee employee) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }
    public void editEmployee(Employee employee){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employee);
        transaction.commit();
        session.close();
    }
    public void removeEmployee(String firstname, String lastname) {}

    public List<Employee> listEmployees(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Employee e where e.isDeleted=false");
        List<Employee> employeeList = query.getResultList();
        session.close();
        return employeeList;
    }
    // show employye deleted to
    public List<Employee> listEmployeesAll(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Employee");
        List<Employee> employeeList = query.getResultList();
        session.close();
        return employeeList;
    }
}

