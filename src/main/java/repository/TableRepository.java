package repository;

import model.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

public class TableRepository {
    public void addPaymentMethod(Table table){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(table);
        transaction.commit();
        session.close();
    }
    public void editPaymentMethod(Table table){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(table);
        transaction.commit();
        session.close();
    }
    public void removePaymentMethod(Table table){}
    public Table findTable(String tableName){return null;}
}
