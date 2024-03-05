package com.bms.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City extends BaseModel {
    @OneToMany(mappedBy = "cities")
    private List<Theatre> theatres;
    private String name;
}