package com.hongik.fe_sit_ival_server.seat.domain;

import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import com.hongik.fe_sit_ival_server.team.domain.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "festival_id")
    private Festival festival;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private Long horizon;
    private Long vertical;
    private Boolean isBooked = false;

    @Builder(access = AccessLevel.PRIVATE)
    private Seat(Festival festival, Team team, Long horizon, Long vertical, Boolean isBooked) {
        this.festival = festival;
        this.team = team;
        this.horizon = horizon;
        this.vertical = vertical;
        this.isBooked = isBooked;
    }

    public static Seat createSeat(Festival festival, Team team, Long horizon, Long vertical) {
        return Seat.builder()
                .festival(festival)
                .team(team)
                .horizon(horizon)
                .vertical(vertical)
                .isBooked(false)
                .build();
    }

    public void updateBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }
}
