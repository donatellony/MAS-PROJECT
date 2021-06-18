package net.mas.repositories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.mas.entities.Worker;
import net.mas.services.WorkerService;
import net.mas.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WorkerRepository implements WorkerService {
    private static SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public WorkerRepository(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Worker> getWorkers() {
        session = sessionFactory.openSession();
        try{
            String hql = "From Worker";

            return session.createQuery(hql).list();
        } finally {
            session.close();
        }
    }
}
