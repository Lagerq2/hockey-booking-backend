package se.jensen.elias.hockeybookingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.elias.hockeybookingbackend.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
