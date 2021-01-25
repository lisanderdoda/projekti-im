package repository;

import model.OrdersItems;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;


public class OrderItemRepository {
   public void addOrderItem(OrdersItems ordersItems){
      Session session= HibernateUtils.getSessionFactory().openSession();
      Transaction transaction=session.beginTransaction();
      session.save(ordersItems);
      session.close();
   }
   public void editOrderItem(OrdersItems ordersItems){
      Session session=HibernateUtils.getSessionFactory().openSession();
      Transaction transaction=session.beginTransaction();
      session.update(ordersItems);
      session.close();
   }
   public void removeOrderItem(OrdersItems ordersItems){

   }

}
