package se.jensen.elias.hockeybookingbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int seatNumber;

    @OneToMany(mappedBy = "seat")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    public Seat() {
    }

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getSeatId() {
        return id;
    }

    public void setSeatId(Long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatchSeats(Match match) {
        this.match = match;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> booking) {
        this.bookings = bookings;
    }


}
