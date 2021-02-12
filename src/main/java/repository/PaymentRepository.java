package repository;


import model.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

public class PaymentRepository extends AbstractRepository<Payment> {
    public PaymentRepository() {
        this.aClass = Payment.class;
    }
}

