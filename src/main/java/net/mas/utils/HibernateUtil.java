package net.mas.utils;

import net.mas.entities.Client;
import net.mas.entities.Moderator;
import net.mas.entities.Verifier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        if(factory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        return factory;
    }

    public static void addSeedData() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client client1 = new Client("Yehor", "Voiko", "Warsaw");
        Client client2 = new Client("Ala", "Kotowa", "Warsaw");
        Client client3 = new Client("Ola", "Psowa", "Katowice");

        Moderator programmer1 = new Moderator("Jan", "Kowalski", 15f, (short) 10);
        Verifier verifier1 = new Verifier("Slava", "Marlow", 24f, 2000);

        session.save(client1);
        session.save(client2);
        session.save(client3);
        session.save(programmer1);
        session.save(verifier1);
        System.out.println("SAVED!");
        transaction.commit();
        session.close();
    }
}
