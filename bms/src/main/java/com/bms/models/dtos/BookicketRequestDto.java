package com.bms.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookicketRequestDto {
    private List<Long> seatIds;
    private Long userId;
    private Long showId;
}
