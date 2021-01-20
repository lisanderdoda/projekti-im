package repository;

import model.Employee;
import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.Query;
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
}
