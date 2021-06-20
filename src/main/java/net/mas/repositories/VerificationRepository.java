package net.mas.repositories;

import net.mas.entities.Client;
import net.mas.entities.Verification;
import net.mas.entities.Verifier;
import net.mas.services.IVerificationService;
import net.mas.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class VerificationRepository implements IVerificationService {
    private static SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public VerificationRepository(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Verifier> getVerifiersWithVerification() {
        session = sessionFactory.openSession();
        try{
            String hql = "From Verifier where verifications.size > 0";
            return session.createQuery(hql).list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Client> getClientsWithVerification() {
        session = sessionFactory.openSession();
        try{
            String hql = "select c from Client c left join Picture p on c.clientId = p.owner.clientId" +
                    " left join Document d on c.clientId = d.owner.clientId" +
                    " where d.verification IS NOT NULL OR p.verification IS NOT NULL";
            return session.createQuery(hql).list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Verification> getVerifications() {
        session = sessionFactory.openSession();
        try{
            String hql = "From Verification";

            return session.createQuery(hql).list();
        } finally {
            session.close();
        }
    }
}
