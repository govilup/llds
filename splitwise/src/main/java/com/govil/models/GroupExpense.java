package com.govil.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * This is a mapping class, use of composition by extending BaseModel instead of inheritance.
 * We can create a mapping class when we have a lot of null checks, this will also resolve N + 1 problem
 * Usually mapping class is created for m:m relation.
 */

@Getter
@Setter
@Entity
public class GroupExpense extends BaseModel {

    @ManyToOne
    private Group group;

    @OneToOne
    private Expense expense;

}
