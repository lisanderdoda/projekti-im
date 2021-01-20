package repository;


import model.Category;
import model.MenuItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuItemRepository {

    public void createMenuItem(Category category, Integer createdBy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vendos emrin e produktit");
        String name = scanner.nextLine();
        System.out.println("Vendos pershkrimin");
        String description = scanner.nextLine();
        System.out.println("Vendos cmimin sa do te kushtoj");
        double price = scanner.nextDouble();
        System.out.println("Vendos emrin e kategorise qe do i perkasi");
        String cname= scanner.nextLine();

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Vendos emrin e menus");
        String name = sc.nextLine();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select m from MenuItem m where m.name=:name and m.isDeleted=false ");
        query.setParameter("name", name);
        List<MenuItem> menuItems = query.getResultList();
        session.close();
        if (menuItems.isEmpty()) {
            System.out.println("Kategoria nuk egziston");
            session.close();
            findByName();
        }
        MenuItem menuItem = menuItems.get(0);
        session.close();
        return menuItem;
    }
    public void showMenu(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query1 = session.createQuery("from MenuItem m where m.isDeleted=false ");
        List<MenuItem> menuItems = query1.getResultList();
        menuItems.forEach(System.out::println);
    }
}
