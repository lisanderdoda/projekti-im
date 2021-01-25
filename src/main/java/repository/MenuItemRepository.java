package repository;

import model.MenuItem;
import org.hibernate.Session;
import util.HibernateUtils;
import javax.persistence.Query;
import java.util.List;


public class MenuItemRepository {

   public void addMenuItem(MenuItem menuItem){}
   public void editMenuItem(MenuItem menuItem){}
   public void removeMenuItem(MenuItem menuItem){}
   public List<MenuItem> showMenu(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query1 = session.createQuery("from MenuItem m where m.isDeleted=false ");
        List<MenuItem> menuItems = query1.getResultList();
        session.close();
        return menuItems;
    }
}
