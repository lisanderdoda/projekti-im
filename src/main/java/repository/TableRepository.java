package repository;

import model.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import javax.persistence.Query;
import java.util.List;

public class TableRepository {
    public void addTable(Table table){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(table);
        transaction.commit();
        session.close();
    }
    public void editTable(Table table){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(table);
        transaction.commit();
        session.close();
    }
    public void removeTable(Table table){}
    public Table findTable(String tableName){return null;}

    public List<Table> tableList(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Table t where t.isDeleted=false");
        List<Table> tableList = query.getResultList();
        session.close();
        return tableList;
    }
    public List<Table> tableListAll(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Table");
        List<Table> tableList = query.getResultList();
        session.close();
        return tableList;
    }
}
