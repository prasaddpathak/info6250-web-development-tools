package com.info6250.jobportal.templates;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface Controller<T> {
    List<T> get();
    ResponseEntity create(T t);
    ResponseEntity getOne(String s);
}
