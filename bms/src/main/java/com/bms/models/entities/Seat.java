package com.bms.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Seat extends BaseModel {

    private String seatNumber;

    @Column(name = "rows")
    private int row;

    @Column(name = "column")
    private int col;

    @ManyToOne
    private SeatType seatType;
}
