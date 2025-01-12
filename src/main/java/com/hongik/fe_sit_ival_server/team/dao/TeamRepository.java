package com.hongik.fe_sit_ival_server.team.dao;

import com.hongik.fe_sit_ival_server.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {}
