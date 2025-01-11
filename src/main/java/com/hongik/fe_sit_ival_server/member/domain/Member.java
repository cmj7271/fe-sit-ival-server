package com.hongik.fe_sit_ival_server.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String team;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String name, String phoneNumber, String team) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.team = team;
    }

    public static Member createMember(String name, String phoneNumber, String team) {
        return Member.builder().name(name).phoneNumber(phoneNumber).team(team).build();
    }
}
