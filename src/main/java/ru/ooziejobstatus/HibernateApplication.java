package ru.ooziejobstatus;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.ooziejobstatus.entities.Report;
import ru.ooziejobstatus.models.ReportApi;


public class HibernateApplication {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Report rep = new Report();
        rep.setReportPath("pr2");

        session.save(rep);
        transaction.commit();


        session.close();
        sessionFactory.close();
    }
}
