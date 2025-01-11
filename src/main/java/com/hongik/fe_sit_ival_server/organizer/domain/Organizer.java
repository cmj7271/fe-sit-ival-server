package com.hongik.fe_sit_ival_server.organizer.domain;

import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organizer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "organizer")
    private List<Festival> festivals = new ArrayList<>();

    private String name;
    @Lob
    private String description;
}
