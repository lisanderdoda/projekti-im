package repository;

import model.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class PaymentRepository {

    public void createPaymentType(Integer createdBy) {
        System.out.println("Vendosni tipin e pageses qe do te shtoni");
        Scanner scanner = new Scanner(System.in);
        String paymantName = scanner.nextLine();
        Payment payment = new Payment();
        payment.setPaymentType(paymantName);
        payment.setCreatedBy(createdBy);
        payment.setCreatedOn(LocalDateTime.now());
        payment.setIsDeleted(false);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(payment);
        transaction.commit();
        session.close();

    }

    public void deletepaymentType() {
        System.out.println("Vendosni tipin e pageses qe doni te mos egzistoje me:");
        Scanner scanner = new Scanner(System.in);
        String paymantName = scanner.nextLine();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select p from Payment p where p.paymentType=:paymentName " +
                "and p.isDeleted= false");
        query.setParameter("paymentName", paymantName);
        List<Payment> payments = query.getResultList();
        if (payments.isEmpty()) {
            System.out.println("tipi i pageses nuk u gjet provo perseri");
            deletepaymentType();
        } else {
            Payment payment = payments.get(0);
            payment.setIsDeleted(true);

        }
        transaction.commit();
        session.close();
    }
}
