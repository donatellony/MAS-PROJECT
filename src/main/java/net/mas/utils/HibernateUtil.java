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

        byte[] client1pic = readFile("src/main/resources/img/client1img.png");
        byte[] client3doc = readFile("src/main/resources/img/client3document.pdf");

        Picture pictureOfClient1 = new PictureBeforeVerification(client1pic, client1);
        Picture pictureOfClient2 = new PictureBeforeVerification(client1pic, client2);
        Picture pictureOfClient3 = new PictureBeforeVerification(client1pic, client3);

        Document document3 = new DocumentBeforeVerification(client3doc, client3);

        client1.getPictures().add(pictureOfClient1);
        client2.getPictures().add(pictureOfClient2);
        client3.getPictures().add(pictureOfClient3);

        client3.getDocuments().add(document3);

        Moderator moderator1 = new Moderator("Jan", "Kowalski", 15f, (short) 10);
        Verifier verifier1 = new Verifier("Slava", "Marlow", 24f, 2000);
        Verifier verifier2 = new Verifier("Van", "Darkholme", 30f, 300);
        Verifier verifier3 = new Verifier("Jana", "Kowalska", 15f, 20);

        VerificationOfPicture picVer1 = new VerificationOfPicture(LocalDate.now(), verifier1, pictureOfClient1);
        VerificationOfDocument docVer3 = new VerificationOfDocument(LocalDate.now(), verifier2, document3);
        VerificationOfPicture picVer3 = new VerificationOfPicture(LocalDate.now(), verifier2, pictureOfClient3);

        document3.setVerification(docVer3);
        verifier2.getVerifications().add(docVer3);

        pictureOfClient3.setVerification(picVer3);
        verifier2.getVerifications().add(picVer3);
        pictureOfClient1.setVerification(picVer1);
        verifier1.getVerifications().add(picVer1);

        session.save(verifier3);
        session.save(client2);
        session.save(moderator1);
        session.save(verifier1);
        session.save(verifier2);
        session.save(picVer1);
        session.save(picVer3);
        session.save(docVer3);
        session.save(client3);
        session.save(document3);
        session.save(client1);
        session.save(pictureOfClient1);
        session.save(pictureOfClient2);
        session.save(pictureOfClient3);

        System.out.println("SAVED!");
        transaction.commit();
        session.close();
    }

    private static byte[] readFile(String path){
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(data);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
