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

public class OrderRepository extends AbstractRepository<Order>{
    public OrderRepository() {
        this.aClass=Order.class;
    }


}
