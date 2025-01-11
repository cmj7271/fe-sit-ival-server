package com.hongik.fe_sit_ival_server.seat.dao;

import com.hongik.fe_sit_ival_server.seat.domain.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.team.id = :teamId")
    List<Seat> findAllByTeam(@Param("teamId") Long teamId);
}
