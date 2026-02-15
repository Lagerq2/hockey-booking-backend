package se.jensen.elias.hockeybookingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.elias.hockeybookingbackend.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBySeat_Match_Id(Long matchId);

    boolean existsBySeat_Match_IdAndSeat_Id(Long matchId, Long seatId);

}
