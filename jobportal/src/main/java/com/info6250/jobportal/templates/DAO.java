package com.info6250.jobportal.templates;

import com.info6250.jobportal.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DAO {
    private Configuration con = new Configuration().configure().addPackage("com.info6250.jobportal");
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
    private SessionFactory sf = con.buildSessionFactory(reg);

    protected Session getNewSession() {
        return sf.openSession();
    }

}
