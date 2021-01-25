package repository;


import model.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

public class PaymentRepository {


    public void addPaymentMethod(Payment payment){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(payment);
        transaction.commit();
        session.close();
    }
    public void editPaymentMethod(Payment payment){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(payment);
        transaction.commit();
        session.close();
    }
    public void removePaymentMethod(Payment payment){}
}
