package com.hongik.fe_sit_ival_server.seat.dto;

import com.hongik.fe_sit_ival_server.seat.domain.Seat;

public record SeatFindResponse(String TeamName, Long horizon, Long vertical) {
    public static SeatFindResponse from(Seat seat) {
        return new SeatFindResponse(seat.getTeam().getName(), seat.getHorizon(), seat.getVertical());
    }
}
