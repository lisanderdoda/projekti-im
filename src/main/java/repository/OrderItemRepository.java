package repository;

import model.MenuItem;
import model.Order;
import model.OrdersItems;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class OrderItemRepository {

    public void createOrderItem(Order order, MenuItem menuItem, Integer createdBy) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        System.out.println("Sa deshironi?");
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();

        OrdersItems ordersItem = new OrdersItems();
        ordersItem.setCreatedBy(createdBy);
        ordersItem.setOrder(order);
        ordersItem.setMenuItem(menuItem);
        ordersItem.setCreatedOn(LocalDate.now());
        ordersItem.setDeleted(false);
        ordersItem.setPrice(quantity * menuItem.getUnitPrice());
        Transaction transaction = session.beginTransaction();
        session.save(ordersItem);
        transaction.commit();
        session.close();

    }
}
