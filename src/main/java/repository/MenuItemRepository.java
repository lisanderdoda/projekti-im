package repository;

import model.MenuItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import javax.persistence.Query;
import java.util.List;


public class MenuItemRepository {

   public void addMenuItem(MenuItem menuItem){
       Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction transaction = session.beginTransaction();
       session.save(menuItem);
       transaction.commit();
       session.close();
   }
   public void editMenuItem(MenuItem menuItem){
       Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction transaction = session.beginTransaction();
       session.update(menuItem);
       transaction.commit();
       session.close();
   }
   public void removeMenuItem(MenuItem menuItem){}
   public List<MenuItem> showMenu(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query1 = session.createQuery("from MenuItem m where m.isDeleted=false ");
        List<MenuItem> menuItems = query1.getResultList();
        session.close();
        return menuItems;
    }
}
