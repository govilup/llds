package com.govil.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Map;

@Entity
@Setter
@Getter
public class Expense extends BaseModel {
    //expense - user
    //1       - 1
    //m - 1
    //m:1 cardinality
    @ManyToOne
    private User createdBy;
    private String description;
    private Currency currency;
    private Long amount;

    @ElementCollection //expense_user_paid_by_mapping behind the scene
    private Map<User, Long> paidBy;

    @ElementCollection
    private Map<User, Long> owedBy;

}
