package com.govil.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends BaseModel {

    private String name;

    @ManyToOne
    private User admin;

    //group - user
    //1 - m
    //m - 1
    //m:m
    @ManyToMany
    private List<User> members;
}
