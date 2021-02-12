package repository;

import model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;


public class CategoryRepository extends AbstractRepository<Category>{
    public CategoryRepository() {
        this.aClass=Category.class;
    }


    public boolean checkCategory(String categoryName) {
        boolean check = true;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Category c where c.name=:categoryName");
        query.setParameter("categoryName", categoryName);
        List<Category> categoryList = query.getResultList();
        session.close();
        if (categoryList.isEmpty()) {
            check = false;
        }
        return check;
    }

}

