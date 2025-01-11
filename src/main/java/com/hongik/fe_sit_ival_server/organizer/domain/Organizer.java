package com.hongik.fe_sit_ival_server.organizer.domain;

import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Organizer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Lob
    private String description;

    @Builder(access = AccessLevel.PRIVATE)
    private Organizer(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Organizer createMockOrganizer() {
        return Organizer.builder()
                .name("admin")
                .description("asdf")
                .build();
    }
}
