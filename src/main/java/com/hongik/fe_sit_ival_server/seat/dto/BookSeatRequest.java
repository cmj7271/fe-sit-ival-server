package com.hongik.fe_sit_ival_server.seat.dto;

import java.util.List;

public record BookSeatRequest(Long teamId, List<Coordinate> coordinates) {}
