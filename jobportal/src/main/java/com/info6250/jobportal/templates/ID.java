package com.info6250.jobportal.templates;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ID implements Serializable {
    String id = UUID.randomUUID().toString();

    @Override
    public String toString() {
        return id;
    }
}
