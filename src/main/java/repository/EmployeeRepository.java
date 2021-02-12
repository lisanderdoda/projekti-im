package repository;

import model.Category;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import javax.persistence.Query;
import java.util.List;


public class EmployeeRepository extends AbstractRepository<Employee>{

    public EmployeeRepository() {
        this.aClass=Employee.class;
    }

    public Employee login(String username, String password){
        Session session = HibernateUtils.getSessionFactory().openSession();

        org.hibernate.query.Query query = session.createQuery("from Employee e where e.username = :usersname " +
                "and e.password = :password");

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





    public boolean checkEmployeeUsername(String username) {

        boolean check = true;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Employee e where e.username=:username");
        query.setParameter("username", username);
        List<Employee> employees = query.getResultList();
        session.close();
        if (employees.isEmpty()) {
            check = false;
        }
        return check;
    }


}

