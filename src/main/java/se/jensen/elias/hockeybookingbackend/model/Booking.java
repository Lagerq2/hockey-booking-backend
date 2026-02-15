package se.jensen.elias.hockeybookingbackend.model;

import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private String phoneNumber;
    private boolean paymentStatus;
    private String ticketCode;

    public Booking() {
    }

    public Booking(Match match, Seat seat, String phoneNumber, boolean paymentStatus, String ticketCode) {
        this.seat = seat;
        this.phoneNumber = phoneNumber;
        this.paymentStatus = paymentStatus;
        this.ticketCode = ticketCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }


}
