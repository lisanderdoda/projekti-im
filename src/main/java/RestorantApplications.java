import org.hibernate.Session;
import util.HibernateUtils;

import javax.persistence.Query;

public class RestorantApplications {

    public static void main(String[] args) {

        Session session=HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("from Employee ");
    }
}
