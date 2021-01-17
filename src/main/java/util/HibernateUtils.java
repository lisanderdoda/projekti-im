package util;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

        private static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory(){

            if(sessionFactory == null){
                try {
                    Configuration configuration = new Configuration();
                    configuration.addAnnotatedClass(Category.class);
                    configuration.addAnnotatedClass(Employee.class);
                    configuration.addAnnotatedClass(MenuItem.class);
                    configuration.addAnnotatedClass(Order.class);
                    configuration.addAnnotatedClass(OrdersItems.class);
                    configuration.addAnnotatedClass(Payment.class);
                    configuration.addAnnotatedClass(Table.class);



                    ServiceRegistry serviceRegistry = new
                            StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("ja ke fut kot");
                }

            }
            return  sessionFactory;
        }

    }


