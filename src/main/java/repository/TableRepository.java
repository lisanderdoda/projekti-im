package repository;

import model.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import javax.persistence.Query;
import java.util.List;

public class TableRepository extends AbstractRepository<Table> {
    public TableRepository() {
        this.aClass=Table.class;
    }
}
