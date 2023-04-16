package com.info6250.jobportal.templates;

import com.info6250.jobportal.users.User;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface Service<T> {
    List<T> getAll();
    T getById(String id);
    String save(T t) throws ConstraintViolationException;
}
