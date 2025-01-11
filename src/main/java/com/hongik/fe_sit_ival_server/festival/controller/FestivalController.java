package com.hongik.fe_sit_ival_server.festival.controller;

import com.hongik.fe_sit_ival_server.festival.dto.FestivalSizeDTO;
import com.hongik.fe_sit_ival_server.festival.dto.FestivalWithoutSizeDTO;
import com.hongik.fe_sit_ival_server.festival.application.FestivalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FestivalController {
    final FestivalService festivalService;

    @GetMapping("/festival")
    public ResponseEntity<List<FestivalWithoutSizeDTO>> findFestival() {
        List<FestivalWithoutSizeDTO> all = festivalService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("festival")
    public ResponseEntity<Long> addFestival(@RequestBody FestivalWithoutSizeDTO festivalWithoutSizeDTO) {
        Long id = festivalService.createFestival(festivalWithoutSizeDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PatchMapping("/festival/{id}")
    public ResponseEntity<Long> updateFestival(@PathVariable String id,
                                               @RequestBody FestivalWithoutSizeDTO festivalWithoutSizeDTO) {
        Long parsedId = Long.parseLong(id);
        Long updatedId = festivalService.updateFestival(parsedId, festivalWithoutSizeDTO);
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @DeleteMapping("/festival/{id}")
    public ResponseEntity<Void> deleteFestival(@PathVariable String id) {
        Long parsedId = Long.parseLong(id);
        festivalService.deleteFestival(parsedId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/festival/{id}/size")
    public ResponseEntity<FestivalSizeDTO> findFestivalSize(@PathVariable String id) {
        Long parsedId = Long.parseLong(id);
        FestivalSizeDTO dto = festivalService.findFestivalSizeDTO(parsedId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/festival/{id}/size")
    public ResponseEntity<Long> updateFestivalSize(@PathVariable String id,
                                                   @RequestBody FestivalSizeDTO festivalSizeDTO) {
        Long parsedId = Long.parseLong(id);
        Long updatedId = festivalService.setFestivalSize(parsedId, festivalSizeDTO);
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }
}
