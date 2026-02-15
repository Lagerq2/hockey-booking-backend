package se.jensen.elias.hockeybookingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.elias.hockeybookingbackend.model.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByMatchId(Long matchId);

    Seat findBySeatNumber(int seatNumber);
}
