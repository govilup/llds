package com.bms.service;

import com.bms.exceptions.InvalidArgumentsException;
import com.bms.exceptions.SeatNotAvailableException;
import com.bms.mappers.TicketDataModelMapper;
import com.bms.models.entities.*;
import com.bms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketDataModelMapper dataModelMapper;

    public Ticket bookTicket(List<Long> seatIds, Long showId, Long userId) throws InvalidArgumentsException, SeatNotAvailableException {
        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);
        Optional<Show> showOptional = showRepository.findById(showId);

        if (showOptional.isEmpty()) {
            throw new InvalidArgumentsException(
                    "Show by: " + showId + " doesn't exist."
            );
        }

        // Lock will be taken on the show seats
        List<ShowSeat> showSeats = getAndLockShowSeats(seats, showOptional);

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new InvalidArgumentsException("User with id: " + userId + " doesn't exist.");
        }

        Ticket ticket = buildTicket(seats, showOptional, userOptional);

        return (Ticket) ticketRepository.save(ticket);
        //TODO: instead of returning Ticket (entity) we can return DTO by making use of modelMapper.
    }

    private Ticket buildTicket(List<Seat> seats, Optional<Show> showOptional, Optional<User> userOptional) {
        return Ticket.builder()
                .bookedBy(userOptional.get()) //this could throw NPE better check isPresent
                .status(TicketStatus.PROCESSING)
                .show(showOptional.get())
                .seats(seats)
                .amount(0)
                .timeOfBookng(new Date())
                .build();
    }


    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public List<ShowSeat> getAndLockShowSeats(List<Seat> seats, Optional<Show> showOptional) throws SeatNotAvailableException {
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats, showOptional.get());

        for (ShowSeat showSeat: showSeats) {
            if (!(showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE) || (
                    showSeat.getStatus().equals(ShowSeatStatus.LOCKED)))) { // && new Date( - showSeat.getLockedAt())))) {
                throw new SeatNotAvailableException();
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        for (ShowSeat showSeat: showSeats) {
            showSeat.setStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }


        return showSeats;
    }

    // commit;
    // Lock will be removed
}
