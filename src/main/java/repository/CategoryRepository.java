package repository;

import model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;


public class CategoryRepository {

    public void addCategory(Category category) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
    }

    public void editCategory(Category category) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(category);
        transaction.commit();
        session.close();
    }

    public void removeCategory() {
    }

    public Category findByName(String name) {
        return null;
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

    public List<Category> listCategoryes() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from Category c where c.isDeleted=false");
        List<Category> categoryList = query.getResultList();
        session.close();
        return categoryList;
    }
}

