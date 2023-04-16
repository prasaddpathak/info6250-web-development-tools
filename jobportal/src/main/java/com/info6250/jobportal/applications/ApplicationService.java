package com.info6250.jobportal.applications;

import com.info6250.jobportal.templates.DAO;
import com.info6250.jobportal.templates.ID;
import com.info6250.jobportal.templates.Service;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@org.springframework.stereotype.Service
public class ApplicationService extends DAO implements Service<Application> {

    @Override
    public List<Application> getAll() {
        Session s = getNewSession();
        Query query = s.createQuery("SELECT a FROM Application a");
        List<Application> allApplications = query.list();
        s.close();
        return allApplications;
    }

    public Application getById(String id) {return null;}

    public List<Application> getForUser(String id) {
        Session s = getNewSession();
        Query query = s.createQuery("SELECT a FROM Application a WHERE a.user_id = '" + id +"'");
//        Query query = s.createQuery("SELECT a FROM Application a left join Opening o on a.opening_id = o.id");
        List<Application> allApplications = query.list();
        s.close();
        return allApplications;
    }

    @Override
    public String save(Application newApplication) throws ConstraintViolationException {
        Session s = getNewSession();
        Transaction tx = s.beginTransaction();
        ApplicationId aid = (ApplicationId) s.save(newApplication);
        String newId = aid.toString();
        tx.commit();
        s.close();
        return newId;
    }

    public String updateForUser(Application updatedApplication) {
        Session s = getNewSession();
        Transaction tx = s.beginTransaction();
        System.out.println(updatedApplication);
        s.saveOrUpdate(updatedApplication);
        tx.commit();
        s.close();
        return "Success";
    }
}
