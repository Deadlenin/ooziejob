package ru.ooziejobstatus;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {
    private static volatile SessionFactory instance;

    public static SessionFactory getInstance() {
        SessionFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (SessionFactoryBuilder.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = new Configuration()
                            .configure().buildSessionFactory();
                }
            }
        }
        return localInstance;
    }
}
