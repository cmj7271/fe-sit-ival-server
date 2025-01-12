package com.hongik.fe_sit_ival_server.seat.controller;

import com.hongik.fe_sit_ival_server.seat.application.SeatService;
import com.hongik.fe_sit_ival_server.seat.dto.BookSeatRequest;
import com.hongik.fe_sit_ival_server.seat.dto.SeatFindResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/festival")
@RequiredArgsConstructor
public class SeatController {
    final SeatService seatService;

    @PostMapping("/{id}/seat")
    public ResponseEntity<Void> bookSeat(
            @PathVariable("id") Long festivalId, @RequestBody BookSeatRequest bookSeatRequest) {
        try {
            seatService.bookSeat(festivalId, bookSeatRequest.teamId(), bookSeatRequest.coordinates());
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/seat")
    public ResponseEntity<List<SeatFindResponse>> getAllSeat(@PathVariable("id") Long festivalId) {
        List<SeatFindResponse> seatInfos = seatService.findAllByTeam(festivalId);
        return new ResponseEntity<>(seatInfos, HttpStatus.OK);
    }
}
