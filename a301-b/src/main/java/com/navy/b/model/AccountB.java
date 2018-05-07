package com.navy.b.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

import com.navy.common.model.BaseEntity;


@Entity
public class AccountB extends BaseEntity{
    @Id
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    private String id;
    private String name;
    @Column(unique = true)
    private String cell;
    private String password;

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCell() {
        return this.cell;
    }
    public void setCell(String cell) {
        this.cell = cell;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
