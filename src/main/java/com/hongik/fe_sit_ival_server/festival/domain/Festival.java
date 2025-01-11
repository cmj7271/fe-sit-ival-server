package com.hongik.fe_sit_ival_server.festival.domain;

import com.hongik.fe_sit_ival_server.organizer.domain.Organizer;
import com.hongik.fe_sit_ival_server.seat.domain.Seat;
import com.hongik.fe_sit_ival_server.team.domain.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Festival {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @OneToMany(mappedBy = "festival")
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "festival")
    private List<Team> teams;

    private String location;
    @Lob
    private String description;
    private Integer seatHorizon;
    private Integer seatVertical;
    private LocalDateTime recruitBegin;
    private LocalDateTime recruitEnd;
    private LocalDateTime begin;
    private LocalDateTime end;
}
