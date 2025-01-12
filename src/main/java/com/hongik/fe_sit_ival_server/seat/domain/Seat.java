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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
