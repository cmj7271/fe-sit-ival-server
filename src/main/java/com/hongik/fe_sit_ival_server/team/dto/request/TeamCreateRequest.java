package com.hongik.fe_sit_ival_server.team.dto.request;

public record TeamCreateRequest(
        Long festivalId,
        String name,
        Integer headCount,
        String address
) {
}
