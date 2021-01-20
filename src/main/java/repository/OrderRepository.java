package repository;

import model.Employee;
import model.Order;
import model.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import java.time.LocalDateTime;

public class OrderRepository {

    public void createOrder(Employee employee, Table table){

        System.out.println("Cfar deshironi?");
        Order order = new Order();
        order.setCreatedOn(LocalDateTime.now());
        order.setIsDeleted(false);
        order.setCreatedBy(employee.getId());
        order.setEmployee(employee);
        order.setTable(table);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }
}
