package com.info6250.jobportal.openings;

import com.info6250.jobportal.templates.DAO;
import com.info6250.jobportal.templates.ID;
import com.info6250.jobportal.templates.Service;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

@org.springframework.stereotype.Service
public class OpeningService extends DAO implements Service<Opening> {

    @Override
    public List<Opening> getAll() {
        Session s = getNewSession();
        Query query = s.createQuery("SELECT e FROM Opening e");
        List<Opening> allOpenings = query.list();
        s.close();
        return allOpenings;
    }

    @Override
    public Opening getById(String id) {
        Session s = getNewSession();
        Opening u = (Opening) s.get(Opening.class, id);
        if (u == null) {
            throw new RuntimeException("Opening Not Found");
        }
        return u;
    }

    public List<Opening> getForUser(String userid) {
        Session s = getNewSession();
        Query query = s.createQuery("SELECT e FROM Opening e where e.employer_id = '" + userid + "'");
        List<Opening> allOpenings = query.list();
        s.close();
        return allOpenings;
    }

    @Override
    public String save(Opening newOpening) throws ConstraintViolationException {
        ID id = new ID();
        newOpening.setId(id.toString());
        Session s = getNewSession();
        Transaction tx = s.beginTransaction();
        String newId = (String) s.save(newOpening);
        tx.commit();
        s.close();
        return newId;
    }
}
