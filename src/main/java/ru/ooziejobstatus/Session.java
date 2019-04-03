package ru.ooziejobstatus;

public class Session {
    private static volatile Session instance;

    public static Session getInstance() {
        Session localInstance = instance;
        if (localInstance == null) {
            synchronized (Session.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Session();
                }
            }
        }
        return localInstance;
    }
}
