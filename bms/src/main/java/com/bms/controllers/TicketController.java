package com.bms.controllers;

import com.bms.exceptions.InvalidArgumentsException;
import com.bms.exceptions.SeatNotAvailableException;
import com.bms.models.dtos.BookTicketResponseDto;
import com.bms.models.dtos.BookicketRequestDto;
import com.bms.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    public ResponseEntity<BookTicketResponseDto> bookTicket(BookicketRequestDto request) {
        BookTicketResponseDto response = new BookTicketResponseDto();

        try {
            ticketService.bookTicket(request.getSeatIds(),
                    request.getShowId(), request.getUserId());

        } catch (InvalidArgumentsException | SeatNotAvailableException e) {
            response.setStatus("FAILURE");
            response.setMessage("Something is wrong");
        }
        return ResponseEntity.ok(response);
    }
}
