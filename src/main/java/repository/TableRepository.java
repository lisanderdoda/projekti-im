package repository;

import model.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TableRepository {

    public void createTable(int createdBy){
        Scanner scanner = new Scanner(System.in);
        System.out.println("vendos emrin identikativ te tavolines se re");
        String name = scanner.nextLine();
        System.out.println("vendos sa vende ka tavolina");
        int numberOfSeats = scanner.nextInt();
        Table table = new Table();
        table.setCreatedBy(createdBy);
        table.setCreatedOn(LocalDateTime.now());
        table.setIsDeleted(false);
        table.setName(name);
        table.setNumberOfSeats(numberOfSeats);
        table.setState("e lire");
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(table);
        transaction.commit();
        session.close();
    }
    public Table findTable(){
        Scanner scanner = new Scanner(System.in);
        Session session = HibernateUtils.getSessionFactory().openSession();
        System.out.println("ne cilen tavoline po merr porosine?");
        String name = scanner.nextLine();
        Query query = session.createQuery("select t from Table t where t.isDeleted= false and t.name=:name");
        query.setParameter("name", name);
        List<Table> tables = query.getResultList();
        if(tables.isEmpty()){
            System.out.println("tavolina nuk u gjend, provo perseri ");
            session.close();
            findTable();
        }
        Table table = tables.get(0);
        session.close();
        return  table;
    }
}
