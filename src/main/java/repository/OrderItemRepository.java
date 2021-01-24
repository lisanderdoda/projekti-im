package repository;

import model.MenuItem;
import model.Order;
import model.OrdersItems;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class OrderItemRepository {
    private final ScannerExt scannerExt;

    public OrderItemRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }

    public void createOrderItem(Order order, MenuItem menuItem, Integer createdBy) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        System.out.println("Sa deshironi?");


        int quantity = this.scannerExt.scanNumberField();

        OrdersItems ordersItem = new OrdersItems();
        ordersItem.setCreatedBy(createdBy);
        ordersItem.setOrder(order);
        ordersItem.setQuantity(quantity);
        ordersItem.setMenuItem(menuItem);
        ordersItem.setCreatedOn(LocalDate.now());
        ordersItem.setDeleted(false);
        ordersItem.setPrice(quantity * menuItem.getUnitPrice());
        Transaction transaction = session.beginTransaction();
        session.save(ordersItem);
        transaction.commit();
        session.close();

    }
    public void totalFromSameOrder (Order order,String tableName){
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            Query query = session.createQuery("select i from OrdersItems i join i.order o join o.table t " +
                    "where o.id = :orderId and t.name= :tableName");
            query.setParameter("orderId", order.getOrderId());
            query.setParameter("tableName",tableName);
            List<OrdersItems> doubleList = query.getResultList();
            if(doubleList.isEmpty()){
                System.out.println("shiko me para");
            }
            double sum= 0;
            for(OrdersItems o: doubleList){
                sum=sum + o.getPrice();
            }
            System.out.println("Totali per tu paguar: "+ sum);


            session.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
