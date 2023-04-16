package com.info6250.jobportal.users;

import com.info6250.jobportal.templates.DAO;
import com.info6250.jobportal.templates.ID;
import com.info6250.jobportal.templates.Service;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Base64;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService extends DAO implements Service<User>  {

    @Override
    public List<User> getAll() {
        Session s = getNewSession();
        Query query = s.createQuery("SELECT e FROM User e");
        List<User> allUsers = query.list();
        s.close();
        return allUsers;
    }

    @Override
    public User getById(String userid) {
        Session s = getNewSession();
        Query query = s.createQuery("FROM User u WHERE u.id = '" + userid + "'");
        List<User> u = query.list();
//        User u = (User) s.get(User.class, id);
        if (u.size() == 0) {
            throw new RuntimeException("User Not Found");
        }
        return u.get(0);
    }

    @Override
    public String save(User newUser) throws ConstraintViolationException {
        ID id = new ID();
        newUser.setId(id.toString());
        Session s = getNewSession();
        Transaction tx = s.beginTransaction();
        String newId = (String) s.save(newUser);
        tx.commit();
        s.close();
        return newId;
    }

    public User getAuth(String basicToken) {
        String encodedToken = basicToken.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(encodedToken);
        String decodedToken = new String(decodedBytes);
        String email = decodedToken.split(":")[0];
        String pass = decodedToken.split(":")[1];

        Session s = getNewSession();
        Query query = s.createQuery("FROM User u WHERE u.email_id = '" + email + "'");
        List<User> u = query.list();
        if (u.size() == 0) {
            throw new RuntimeException("User Not Found");
        }
        if (! u.get(0).getPassword().equals(pass)) {
            throw new RuntimeException("Incorrect Password");
        }
        return u.get(0);
    }

}
