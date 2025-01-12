package com.hongik.fe_sit_ival_server.team.dto.response;

import com.hongik.fe_sit_ival_server.team.domain.Team;

public record TeamFindResponse(Long teamId, String festivalName, String teamName, Integer headCount, String address) {
    public static TeamFindResponse from(Team team) {
        return new TeamFindResponse(
                team.getId(), team.getFestival().getName(), team.getName(), team.getHeadCount(), team.getAddress());
    }
}
