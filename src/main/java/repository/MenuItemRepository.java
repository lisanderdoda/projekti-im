package repository;


import model.Category;
import model.MenuItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuItemRepository {

    private final ScannerExt scannerExt;

    public MenuItemRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }


    public void createMenuItem(Category category, Integer createdBy) {
        System.out.println("Vendos emrin e produktit");
        String name = this.scannerExt.scanField();
        System.out.println("Vendos pershkrimin");
        String description = this.scannerExt.scanField();
        System.out.println("Vendos cmimin sa do te kushtoj");
        double price = this.scannerExt.scanDoubleField();

        Session session = HibernateUtils.getSessionFactory().openSession();


        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setUnitPrice(price);
        menuItem.setCreatedBy(createdBy);
        menuItem.setCreatedOn(LocalDateTime.now());
        menuItem.setIsDeleted(false);
        menuItem.setCategory(category);
        Transaction transaction = session.beginTransaction();
        session.save(menuItem);
        transaction.commit();
        session.close();
    }
    public void deleteMenuItem(){

        MenuItem menuItem= findByName();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        menuItem.setIsDeleted(true);
        session.update(menuItem);
        transaction.commit();
        session.close();
        System.out.println("menuja u fshi");

    }
    public MenuItem findByName() {

        System.out.println("Vendos emrin e produktit qe do shtohet ne porosi");
        String name = this.scannerExt.scanField();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select m from MenuItem m where m.name=:name and m.isDeleted=false ");
        query.setParameter("name", name);
        List<MenuItem> menuItems = query.getResultList();
        session.close();
        if (!menuItems.isEmpty()) {
            MenuItem menuItem = menuItems.get(0);
            return menuItem;
        }
        System.out.println("Produkti qe vendoset nuk eshte i sakte");
        return findByName();
    }
    public void showMenu(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query1 = session.createQuery("from MenuItem m where m.isDeleted=false ");
        List<MenuItem> menuItems = query1.getResultList();
        menuItems.forEach(System.out::println);
        session.close();
    }
}
