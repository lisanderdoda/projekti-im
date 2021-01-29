package repository;

import model.Category;
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

    public List<MenuItem> listMenuItems() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from MenuItem m where m.isDeleted=false");
        List<MenuItem> menuItemList = query.getResultList();
        session.close();
        return menuItemList;
    }

    public boolean checkMenuItem(String menuItemName) {
        boolean check = true;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from MenuItem m where m.name=:menuItemName");
        query.setParameter("menuItemName", menuItemName);
        List<MenuItem> menuItemList = query.getResultList();
        session.close();
        if(menuItemList.isEmpty()){
            check = false;
        }
        return check;
    }
}
