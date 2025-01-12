package com.hongik.fe_sit_ival_server.team.application;

import com.hongik.fe_sit_ival_server.festival.dao.FestivalRepository;
import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import com.hongik.fe_sit_ival_server.team.dao.TeamRepository;
import com.hongik.fe_sit_ival_server.team.domain.Team;
import com.hongik.fe_sit_ival_server.team.dto.request.TeamCreateRequest;
import com.hongik.fe_sit_ival_server.team.dto.request.TeamUpdateRequest;
import com.hongik.fe_sit_ival_server.team.dto.response.TeamFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final FestivalRepository festivalRepository;
    private final TeamRepository teamRepository;

    public Long createTeam(TeamCreateRequest request) {
        Festival festival = festivalRepository
                .findById(request.festivalId())
                .orElseThrow(() -> new RuntimeException("customException"));

        Team team = Team.createTeam(festival, request.name(), request.headCount(), request.address());
        Team savedTeam = teamRepository.save(team);

        return savedTeam.getId();
    }

    public TeamFindResponse findTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("customException"));

        return TeamFindResponse.from(team);
    }

    public Long updateTeamInfo(Long teamId, TeamUpdateRequest request) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("customException"));

        team.updateTeamInfo(request.name(), request.headCount(), request.address());

        return team.getId();
    }

    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("customException"));

        teamRepository.delete(team);
    }

    //    public TeamSeatFindResponse findTeamSeat(Long teamId) {
    //        Team team = teamRepository.findById(teamId)
    //                .orElseThrow(() -> new RuntimeException("customException"));
    //    }
}
