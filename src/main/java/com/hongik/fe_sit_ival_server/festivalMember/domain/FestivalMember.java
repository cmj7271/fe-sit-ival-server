package com.hongik.fe_sit_ival_server.festivalMember.domain;

import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import com.hongik.fe_sit_ival_server.global.exception.CustomException;
import com.hongik.fe_sit_ival_server.global.exception.ErrorCode;
import com.hongik.fe_sit_ival_server.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FestivalMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "festival_id")
    private Festival festival;

    private Boolean isParticipate = false;

    //== domain logic ==//

    /**
     * 체크인 표시를 합니다. </br>
     * 이미 체크인된 상태에서 접근시, <string>400 BAD REQUEST</string> 를 반환합니다.
     */
    public void checkIn() {
        if(isParticipate) {
            throw new CustomException(ErrorCode.METHOD_ILLEGAL_ACCESS);
        }
        this.isParticipate = true;
    }
}
