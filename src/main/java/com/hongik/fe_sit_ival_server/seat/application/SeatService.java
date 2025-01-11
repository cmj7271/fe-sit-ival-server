package com.hongik.fe_sit_ival_server.seat.application;

import com.hongik.fe_sit_ival_server.festival.dao.FestivalRepository;
import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import com.hongik.fe_sit_ival_server.seat.dao.SeatRepository;
import com.hongik.fe_sit_ival_server.seat.domain.Seat;
import com.hongik.fe_sit_ival_server.seat.dto.Coordinate;
import com.hongik.fe_sit_ival_server.seat.dto.GetAllSeatInfoResponse;
import com.hongik.fe_sit_ival_server.team.dao.TeamRepository;
import com.hongik.fe_sit_ival_server.team.domain.Team;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
    final SeatRepository seatRepository;
    final TeamRepository teamRepository;
    final FestivalRepository festivalRepository;

    public List<GetAllSeatInfoResponse> findAllByTeam(Long teamId) {
        List<Seat> seats = seatRepository.findAllByTeam(teamId);
        return seats.stream().map(
                seat -> new GetAllSeatInfoResponse(seat.getId(), seat.getTeam().getName(),
                        seat.getHorizon(), seat.getVertical(), seat.getIsBooked())
        ).toList();
    }

    public void bookSeat(Long festivalId,Long teamId, List<Coordinate> coordinates) throws IllegalArgumentException {
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new);
        Festival festival = festivalRepository.findById(festivalId).orElseThrow(IllegalArgumentException::new);

        coordinates.forEach(coordinate -> {
            List<Seat> seats = seatRepository.findAllByHorizonAndVertical(coordinate.horizon(),
                    coordinate.vertical());
            if(seats.size() != 1) {
                throw new IllegalArgumentException("해당하는 좌석이 1개가 아닙니다.");
            }
            Seat seat = seats.getFirst();
            if(seat.getIsBooked()) {
                throw new IllegalArgumentException("이미 예약된 좌석입니다.");
            }

            Seat updatedSeat = Seat.builder()
                    .id(seat.getId())
                    .horizon(seat.getHorizon())
                    .vertical(seat.getVertical())
                    .festival(festival)
                    .team(team)
                    .isBooked(true)
                    .build();
            seatRepository.save(updatedSeat);
        });
    }
}
