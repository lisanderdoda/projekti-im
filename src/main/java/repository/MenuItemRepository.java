package repository;

import model.Category;
import model.MenuItem;
import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.Query;
import java.util.List;



public class MenuItemRepository extends AbstractRepository<MenuItem> {
    public MenuItemRepository() {
        this.aClass = MenuItem.class;

    }

    public boolean checkMenuItem(String menuItemName) {
        boolean check = true;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from MenuItem m where m.name=:menuItemName");
        query.setParameter("menuItemName", menuItemName);
        List<MenuItem> menuItemList = query.getResultList();
        session.close();
        if (menuItemList.isEmpty()) {
            check = false;
        }
        return check;
    }

    public List<MenuItem> listByCategory(Category category) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        return session.createQuery("from MenuItem m join m.category c where c.id=" + category.getId()).getResultList();

    }
}
