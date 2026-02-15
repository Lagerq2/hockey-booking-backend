package se.jensen.elias.hockeybookingbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

;

@Entity
public class Match {
    @Id
    @GeneratedValue
    private Long id;
    private String match;
    private LocalDateTime matchDateTime;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    public Match() {
    }

    public Match(String match, LocalDateTime matchDateTime) {
        this.match = match;
        this.matchDateTime = matchDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }
}
