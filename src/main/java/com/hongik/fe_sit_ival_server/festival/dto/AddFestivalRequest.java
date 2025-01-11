package com.hongik.fe_sit_ival_server.festival.dto;

import java.time.LocalDateTime;

public record AddFestivalRequest(Long id, String name, Long organizerId,
                                 LocalDateTime begin, LocalDateTime end,
                                 String location, String description) {
}
