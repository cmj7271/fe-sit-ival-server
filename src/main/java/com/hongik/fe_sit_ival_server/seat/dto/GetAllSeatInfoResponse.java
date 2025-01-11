package com.hongik.fe_sit_ival_server.seat.dto;

public record GetAllSeatInfoResponse(Long id, String TeamName, Long horizon, Long vertical, Boolean isBooked) {}
