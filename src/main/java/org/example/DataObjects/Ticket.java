package org.example.DataObjects;

import lombok.Data;
import org.example.DataTypes.SeatType;
import org.example.DataTypes.Station;

import java.time.LocalDate;
@Data
public class Ticket {
    private LocalDate departDate;
    private Station from;
    private Station to;
    private SeatType seatType;
    private int ticketAmount;

    public Ticket(LocalDate departDate, Station from, Station to, SeatType seatType, int ticketAmount) {
        this.departDate = departDate;
        this.from = from;
        this.to = to;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }
}