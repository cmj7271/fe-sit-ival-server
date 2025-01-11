package com.hongik.fe_sit_ival_server.team.api;

import com.hongik.fe_sit_ival_server.team.application.TeamService;
import com.hongik.fe_sit_ival_server.team.dto.request.TeamCreateRequest;
import com.hongik.fe_sit_ival_server.team.dto.request.TeamUpdateRequest;
import com.hongik.fe_sit_ival_server.team.dto.response.TeamFindResponse;
import com.hongik.fe_sit_ival_server.team.dto.response.TeamSeatFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Long> createTeam(@RequestBody TeamCreateRequest request) {
        return ResponseEntity.ok(teamService.createTeam(request));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamFindResponse> findTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamService.findTeam(teamId));
    }

    @PatchMapping("/{teamId}")
    public ResponseEntity<Long> updateTeam(@PathVariable Long teamId, @RequestBody TeamUpdateRequest request) {
        return ResponseEntity.ok(teamService.updateTeamInfo(teamId, request));
    }

    @PatchMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{teamId}/seat")
//    public ResponseEntity<TeamSeatFindResponse> findTeamSeat(@PathVariable Long teamId) {
//        return ResponseEntity.ok(teamService.findTeamSeat(teamId));
//    }
}
