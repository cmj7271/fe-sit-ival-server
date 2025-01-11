package com.hongik.fe_sit_ival_server.festival.domain;

import com.hongik.fe_sit_ival_server.partition.domain.Partition;
import com.hongik.fe_sit_ival_server.seat.domain.Seat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Festival {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "festival")
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "festival")
    private List<Partition> partitions = new ArrayList<>();

    private String name;
    private LocalDate startDate;
    private LocalTime startTime;
    private Integer rowNumber;
    private Integer columnNumber;
}
