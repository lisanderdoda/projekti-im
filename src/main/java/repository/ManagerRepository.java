package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

public class ManagerRepository{

    public void showMenuAdmin(){
        System.out.println("Pershendetje shoku administrator");
    }

    public void save(Employee employee) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }
}
