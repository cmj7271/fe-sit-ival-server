package com.hongik.fe_sit_ival_server.seat.application;

import com.hongik.fe_sit_ival_server.festival.dao.FestivalRepository;
import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import com.hongik.fe_sit_ival_server.seat.dao.SeatRepository;
import com.hongik.fe_sit_ival_server.seat.domain.Seat;
import com.hongik.fe_sit_ival_server.seat.dto.Coordinate;
import com.hongik.fe_sit_ival_server.seat.dto.SeatFindResponse;
import com.hongik.fe_sit_ival_server.team.dao.TeamRepository;
import com.hongik.fe_sit_ival_server.team.domain.Team;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
    final SeatRepository seatRepository;
    final TeamRepository teamRepository;
    final FestivalRepository festivalRepository;

    public List<SeatFindResponse> findAllByTeam(Long festivalId) {
        List<Seat> seats = seatRepository.findByFestivalIdAndIsBookedTrue(festivalId);
        return seats.stream().map(SeatFindResponse::from).collect(Collectors.toList());
    }

    public void bookSeat(Long festivalId, Long teamId, List<Coordinate> coordinates) throws IllegalArgumentException {
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new);
        Festival festival = festivalRepository.findById(festivalId).orElseThrow(IllegalArgumentException::new);

        coordinates.forEach(coordinate -> {
            List<Seat> seats = seatRepository.findAllByHorizonAndVertical(coordinate.horizon(), coordinate.vertical());
            if (!seats.isEmpty()) {
                Seat seat = seats.getFirst();
                if (seat.getIsBooked()) {
                    throw new IllegalArgumentException("이미 예약된 좌석입니다.");
                }
            }
            Seat newSeat = Seat.createSeat(festival, team, coordinate.horizon(), coordinate.vertical());
            newSeat.updateBooked(true);
            seatRepository.save(newSeat);
        });
    }
}
