package repository;

import model.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;


public class TableRepository {
    private final ScannerExt scannerExt;

    public TableRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }

    public void createTable(int createdBy){

        System.out.println("vendos emrin identikativ te tavolines se re");
        String name = this.scannerExt.scanField();
        System.out.println("vendos sa vende ka tavolina");
        int numberOfSeats = this.scannerExt.scanNumberField();
        Table table = new Table();
        table.setCreatedBy(createdBy);
        table.setCreatedOn(LocalDateTime.now());
        table.setIsDeleted(false);
        table.setName(name);
        table.setNumberOfSeats(numberOfSeats);
        table.setOccupied(false);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(table);
        transaction.commit();
        session.close();
    }
    public Table findTable(){

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Zgjidh tavolinen");
        String name = this.scannerExt.scanField();
        Query query = session.createQuery("select t from Table t where t.isDeleted= false and t.name=:name");

        query.setParameter("name", name);
        List<Table> tables = query.getResultList();
        if(!tables.isEmpty()){
            Table table = tables.get(0);
            table.setOccupied(true);
            session.update(table);
            transaction.commit();
            session.close();
            return  table;
        }else {
            System.out.println("tavolina nuk u gjend, provo perseri ");
            session.close();
           return findTable();
        }

    }
}
