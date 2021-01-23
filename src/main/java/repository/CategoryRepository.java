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

    private final ScannerExt scannerExt;

    public CategoryRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }

    public void createCategory(Integer createdBy) {

        System.out.println("vendos emrin e kategorise se re");
        String name = this.scannerExt.scanField();
        Category category = new Category();
        category.setName(name);
        category.setCreatedBy(createdBy);
        category.setCreatedOn(LocalDateTime.now());
        category.setIsDeleted(false);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
        System.out.println("kategoria u shtua me sukses");
    }

    public void deleteCategory() {


        System.out.println("zgjidhni kategorine qe deshiron te fshihet");

        String name = this.scannerExt.scanField();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Category c where c.name=:name and c.isDeleted=false");
        List<Category> categories = query.getResultList();
        if (categories.isEmpty()) {
            System.out.println("kategoria nuk egziston");
            deleteCategory();
        } else {
            Transaction transaction = session.beginTransaction();
            session.update(categories.get(0));
            transaction.commit();
            session.close();
        }
    }

    public Category findByName() {

        System.out.println("Vendos emrin e kategorise ku deshiron te shtosh produktin");
        String name = this.scannerExt.scanField();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Category c where c.name=:name and c.isDeleted=false");
        query.setParameter("name", name);
        List<Category> categories = query.getResultList();
        if (categories.isEmpty()) {
            System.out.println("Kategoria nuk egziston");
            session.close();
            findByName();
        }
        Category category = categories.get(0);
        session.close();

        return category;
    }
}

