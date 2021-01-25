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
    private final ScannerExt scannerExt;

    public OrderRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }

    public Order createOrder(Employee employee, Table table){

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
        return order;
    }
    public Order selectOrderOpened(){
        System.out.println("Per cilen tavoline do kryhet pagesa?");
        String tableName = scannerExt.scanField();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select o from Order o join o.table t where o.paidOn is null and t.name=:name");
        query.setParameter("name", tableName);
        List<Order> orders = query.getResultList();
        if(orders.isEmpty()){
            System.out.println("Tavolina nuk ekziston ose pagesa eshte kryer zgjidh\n" +
                    "1-per te dal ose 2-per te vendosur nje tavoline tjeter");
            int choise = scannerExt.scanNumberField();
             switch (choise){

                 case 2:
                     session.close();
                     return selectOrderOpened();
                default:
                    session.close();
                    break;
             }
        }
        Order order = orders.get(0);
        session.close();
        return order;

    }
    public void updateOrder(Order order){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        order.setPaidOn(LocalDateTime.now());
        session.update(order);
        transaction.commit();
        session.close();
    }
}
