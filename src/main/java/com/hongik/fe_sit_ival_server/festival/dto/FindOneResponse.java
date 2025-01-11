package com.hongik.fe_sit_ival_server.festival.dto;

import java.time.LocalDateTime;

public record FindOneResponse(String name, String organizer, LocalDateTime begin, LocalDateTime end,
                              String location, String description) {
}
