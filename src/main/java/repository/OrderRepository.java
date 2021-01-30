package repository;

import model.Employee;
import model.Order;
import model.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRepository {

    public void addOrder(Order order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    public Order selctOrderToPay() {
        return null;
    }

    public void editOrder(Order order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    public void removeOrder() {
    }

    public  List<Object[]> showMyOpenOrders() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select t.name, m.name, m.unitPrice, oi.quantity" +
                " from MenuItem m join m.ordersItems oi join oi.order " +
                "o join o.table t where o.paidOn is null");
        List<Object[]> orderTable = query.getResultList();
        return orderTable;
    }
}
