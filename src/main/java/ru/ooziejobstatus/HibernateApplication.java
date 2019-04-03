package ru.ooziejobstatus;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import ru.ooziejobstatus.entities.Report;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


public class HibernateApplication {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Report rep = new Report();
        rep.setId(1l);
        rep.setReportPath("pr2");

        session.save(rep);
        transaction.commit();


        session.close();
        sessionFactory.close();
    }
}
