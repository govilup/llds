package com.govil.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@Table(name = "users") //in postgres user is a reserved keyword to avoid that add @Table annotation.
public class User extends BaseModel {

    private String name;
    private String phoneNumber;
    private String hashedPassword;

}
