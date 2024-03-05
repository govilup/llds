package com.bms.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ticket")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseModel {
    private int amount;
    private Date timeOfBookng;

    @ManyToMany
    private List<Seat> seats;

    @ManyToOne
    private User bookedBy;

    @ManyToOne
    private Show show;

    @OneToMany(mappedBy = "ticket")
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status;
}