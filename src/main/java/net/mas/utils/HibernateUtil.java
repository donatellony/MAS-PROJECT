package net.mas.utils;

import net.mas.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;

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

        byte[] client1pic = readImg("src/main/resources/img/client1img.png");

        Picture pictureOfClient1 = new PictureBeforeVerification(client1pic, client1);
        client1.getPictures().add(pictureOfClient1);

        Moderator moderator1 = new Moderator("Jan", "Kowalski", 15f, (short) 10);
        Verifier verifier1 = new Verifier("Slava", "Marlow", 24f, 2000);

        VerificationOfPicture picVer1 = new VerificationOfPicture(LocalDate.now(), verifier1, pictureOfClient1);
        pictureOfClient1.setVerification(picVer1);
        verifier1.getVerifications().add(picVer1);

        session.save(client2);
        session.save(client3);
        session.save(moderator1);
        session.save(verifier1);
        session.save(picVer1);
        session.save(client1);
        session.save(pictureOfClient1);

        System.out.println("SAVED!");
        transaction.commit();
        session.close();
    }

    private static byte[] readImg(String path){
        File file = new File(path);
        byte[] imageData = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageData;
    }
}
