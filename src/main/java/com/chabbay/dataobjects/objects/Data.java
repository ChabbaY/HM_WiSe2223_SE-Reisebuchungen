package com.chabbay.dataobjects.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * definition of an entity
 *
 * @author Linus Englert
 */
@Entity
public class Data {
    protected @Id @GeneratedValue Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}