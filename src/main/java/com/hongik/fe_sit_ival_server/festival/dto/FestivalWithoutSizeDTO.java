package com.hongik.fe_sit_ival_server.festival.dto;

import com.hongik.fe_sit_ival_server.organizer.domain.Organizer;
import java.time.LocalDateTime;

public record FestivalWithoutSizeDTO(
        Long id,
        String name,
        Organizer organizer,
        LocalDateTime begin,
        LocalDateTime end,
        String location,
        String description) {}
