package com.hongik.fe_sit_ival_server.festival.controller;

import com.hongik.fe_sit_ival_server.festival.dto.AddFestivalRequest;
import com.hongik.fe_sit_ival_server.festival.application.FestivalService;
import com.hongik.fe_sit_ival_server.festival.dto.FindFestivalResponse;
import com.hongik.fe_sit_ival_server.festival.dto.FindOneResponse;
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
    public ResponseEntity<List<FindFestivalResponse>> findFestival() {
        List<FindFestivalResponse> all = festivalService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/festival/{id}")
    public ResponseEntity<FindOneResponse> findFestival(@PathVariable Long id) {
        FindOneResponse festival = festivalService.findById(id);
        return new ResponseEntity<>(festival, HttpStatus.OK);
    }

    @PostMapping("festival")
    public ResponseEntity<Long> addFestival(@RequestBody AddFestivalRequest addFestivalRequest) {
        Long id = festivalService.createFestival(addFestivalRequest);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PatchMapping("/festival/{id}")
    public ResponseEntity<Long> updateFestival(@PathVariable Long id,
                                               @RequestBody AddFestivalRequest addFestivalRequest) {
        Long updatedId = festivalService.updateFestival(id, addFestivalRequest);
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @DeleteMapping("/festival/{id}")
    public ResponseEntity<Void> deleteFestival(@PathVariable Long id) {
        festivalService.deleteFestival(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/festival/{id}/size")
//    public ResponseEntity<FestivalSizeDTO> findFestivalSize(@PathVariable String id) {
//        Long parsedId = Long.parseLong(id);
//        FestivalSizeDTO dto = festivalService.findFestivalSizeDTO(parsedId);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//    @PostMapping("/festival/{id}/size")
//    public ResponseEntity<Long> updateFestivalSize(@PathVariable String id,
//                                                   @RequestBody FestivalSizeDTO festivalSizeDTO) {
//        Long parsedId = Long.parseLong(id);
//        Long updatedId = festivalService.setFestivalSize(parsedId, festivalSizeDTO);
//        return new ResponseEntity<>(updatedId, HttpStatus.OK);
//    }
}
