package com.hongik.fe_sit_ival_server.team.domain;

import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "festival_id")
    private Festival festival;

    private String name;

    private Integer headCount;

    private String address;

    @Builder(access = AccessLevel.PRIVATE)
    private Team(Festival festival, String name, Integer headCount, String address) {
        this.festival = festival;
        this.name = name;
        this.headCount = headCount;
        this.address = address;
    }

    public static Team createTeam(Festival festival, String name, Integer headCount, String address) {
        return Team.builder()
                .festival(festival)
                .name(name)
                .headCount(headCount)
                .address(address)
                .build();
    }

    public void updateTeamInfo(String name, Integer headCount, String address) {
        this.name = name;
        this.headCount = headCount;
        this.address = address;
    }
}
