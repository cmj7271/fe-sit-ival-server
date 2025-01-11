package com.hongik.fe_sit_ival_server.festival.dto;

import java.time.LocalDateTime;

public record FindFestivalResponse(Long id, String name, String organizer,
                                   LocalDateTime Begin, LocalDateTime End,
                                   String location, String description) {
}
